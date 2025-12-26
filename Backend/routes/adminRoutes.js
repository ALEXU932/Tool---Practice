const express = require("express");
const router = express.Router();
const db = require("../config/db");
const multer = require("multer");
const path = require("path");
const fs = require("fs");

// Create uploads directory if not exists
const uploadDir = path.join(__dirname, "../uploads/products");
if (!fs.existsSync(uploadDir)) {
  fs.mkdirSync(uploadDir, { recursive: true });
}

// Configure multer
const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, uploadDir);
  },
  filename: function (req, file, cb) {
    const uniqueSuffix = Date.now() + "-" + Math.round(Math.random() * 1e9);
    cb(null, "product-" + uniqueSuffix + path.extname(file.originalname));
  }
});

const upload = multer({
  storage: storage,
  limits: { fileSize: 5 * 1024 * 1024 },
  fileFilter: (req, file, cb) => {
    const filetypes = /jpeg|jpg|png|gif|webp/;
    const mimetype = filetypes.test(file.mimetype);
    const extname = filetypes.test(path.extname(file.originalname).toLowerCase());
    
    if (mimetype && extname) {
      return cb(null, true);
    }
    cb(new Error("Only image files are allowed"));
  }
});

// Middleware to check admin
const isAdmin = (req, res, next) => {
  // In production, verify JWT token
  const userRole = req.headers["x-user-role"] || req.session?.user?.role;
  
  if (userRole !== "admin") {
    return res.status(403).json({ error: "Admin access required" });
  }
  next();
};

router.use(isAdmin);

// Get dashboard stats
router.get("/dashboard", (req, res) => {
  const stats = {};
  
  const queries = [
    { key: "totalProducts", sql: "SELECT COUNT(*) as count FROM products" },
    { key: "totalOrders", sql: "SELECT COUNT(*) as count FROM orders" },
    { key: "totalUsers", sql: "SELECT COUNT(*) as count FROM users WHERE role = 'user'" },
    { key: "revenue", sql: "SELECT COALESCE(SUM(total_amount), 0) as revenue FROM orders WHERE status != 'cancelled'" },
    { key: "lowStock", sql: "SELECT COUNT(*) as count FROM products WHERE stock < 10 AND stock > 0" },
    { key: "outOfStock", sql: "SELECT COUNT(*) as count FROM products WHERE stock = 0" }
  ];
  
  let completed = 0;
  
  queries.forEach(query => {
    db.query(query.sql, (err, result) => {
      if (err) {
        console.error(err);
        stats[query.key] = 0;
      } else {
        stats[query.key] = result[0].count || result[0].revenue || 0;
      }
      
      completed++;
      if (completed === queries.length) {
        res.json(stats);
      }
    });
  });
});

// Get all products for admin
router.get("/products", (req, res) => {
  const query = `
    SELECT p.*, c.name as category_name, b.name as brand_name 
    FROM products p
    LEFT JOIN categories c ON p.category_id = c.id
    LEFT JOIN brands b ON p.brand_id = b.id
    ORDER BY p.created_at DESC
  `;
  
  db.query(query, (err, results) => {
    if (err) {
      console.error(err);
      return res.status(500).json({ error: "Database error" });
    }
    res.json(results);
  });
});

// Add new product
router.post("/products", upload.array("images", 5), (req, res) => {
  const {
    name, description, price, category_id, brand_id,
    stock, sku, sale_price, weight, is_featured
  } = req.body;
  
  // Validate required fields
  if (!name || !price || !category_id || !sku) {
    return res.status(400).json({ error: "Missing required fields" });
  }
  
  // Generate slug
  const slug = name.toLowerCase()
    .replace(/[^a-z0-9]+/g, "-")
    .replace(/(^-|-$)/g, "");
  
  // Handle main image
  let mainImage = null;
  if (req.files && req.files.length > 0) {
    mainImage = `/uploads/products/${req.files[0].filename}`;
  }
  
  const query = `
    INSERT INTO products (
      name, slug, description, price, category_id, brand_id,
      stock, sku, sale_price, weight, is_featured, image
    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
  `;
  
  const values = [
    name, slug, description || "", parseFloat(price), 
    parseInt(category_id), brand_id ? parseInt(brand_id) : null,
    parseInt(stock) || 0, sku,
    sale_price ? parseFloat(sale_price) : null,
    weight ? parseFloat(weight) : null,
    is_featured === "true" ? 1 : 0,
    mainImage
  ];
  
  db.query(query, values, (err, result) => {
    if (err) {
      console.error(err);
      return res.status(500).json({ error: "Failed to add product" });
    }
    
    const productId = result.insertId;
    
    // Save additional images
    if (req.files && req.files.length > 1) {
      const imageQueries = [];
      
      req.files.slice(1).forEach((file, index) => {
        const imageUrl = `/uploads/products/${file.filename}`;
        const imageQuery = `
          INSERT INTO product_images (product_id, image_url, alt_text, is_primary)
          VALUES (?, ?, ?, ?)
        `;
        imageQueries.push({ sql: imageQuery, values: [productId, imageUrl, name, 0] });
      });
      
      // Execute all image insertions
      let completed = 0;
      imageQueries.forEach(imgQuery => {
        db.query(imgQuery.sql, imgQuery.values, (err) => {
          if (err) console.error("Failed to save additional image:", err);
          completed++;
        });
      });
    }
    
    res.json({
      success: true,
      message: "Product added successfully",
      productId: productId
    });
  });
});

// Update product
router.put("/products/:id", upload.array("images", 5), (req, res) => {
  const productId = req.params.id;
  const updateData = req.body;
  
  // Build update query
  const updates = [];
  const values = [];
  
  const allowedFields = [
    "name", "description", "price", "category_id", "brand_id",
    "stock", "sku", "sale_price", "weight", "is_featured", "is_active"
  ];
  
  allowedFields.forEach(field => {
    if (updateData[field] !== undefined) {
      updates.push(`${field} = ?`);
      
      if (field === "price" || field === "sale_price" || field === "weight") {
        values.push(parseFloat(updateData[field]));
      } else if (field === "category_id" || field === "brand_id" || field === "stock") {
        values.push(parseInt(updateData[field]));
      } else if (field === "is_featured" || field === "is_active") {
        values.push(updateData[field] === "true" ? 1 : 0);
      } else {
        values.push(updateData[field]);
      }
    }
  });
  
  // Handle main image update
  if (req.files && req.files.length > 0) {
    updates.push("image = ?");
    values.push(`/uploads/products/${req.files[0].filename}`);
  }
  
  if (updates.length === 0) {
    return res.status(400).json({ error: "No fields to update" });
  }
  
  values.push(productId);
  
  const query = `UPDATE products SET ${updates.join(", ")} WHERE id = ?`;
  
  db.query(query, values, (err, result) => {
    if (err) {
      console.error(err);
      return res.status(500).json({ error: "Failed to update product" });
    }
    
    res.json({
      success: true,
      message: "Product updated successfully",
      affectedRows: result.affectedRows
    });
  });
});

// Delete product
router.delete("/products/:id", (req, res) => {
  const productId = req.params.id;
  
  // First get product images to delete files
  db.query("SELECT image FROM products WHERE id = ?", [productId], (err, results) => {
    if (err) {
      console.error(err);
      return res.status(500).json({ error: "Database error" });
    }
    
    // Delete product (CASCADE will handle related images)
    db.query("DELETE FROM products WHERE id = ?", [productId], (err, result) => {
      if (err) {
        console.error(err);
        return res.status(500).json({ error: "Failed to delete product" });
      }
      
      res.json({
        success: true,
        message: "Product deleted successfully"
      });
    });
  });
});

// Get categories for dropdown
router.get("/categories", (req, res) => {
  db.query("SELECT id, name FROM categories WHERE is_active = 1 ORDER BY name", (err, results) => {
    if (err) {
      console.error(err);
      return res.status(500).json({ error: "Database error" });
    }
    res.json(results);
  });
});

// Get brands for dropdown
router.get("/brands", (req, res) => {
  db.query("SELECT id, name FROM brands WHERE is_active = 1 ORDER BY name", (err, results) => {
    if (err) {
      console.error(err);
      return res.status(500).json({ error: "Database error" });
    }
    res.json(results);
  });
});

// Get all orders
router.get("/orders", (req, res) => {
  const query = `
    SELECT o.*, u.username, u.email 
    FROM orders o
    JOIN users u ON o.user_id = u.id
    ORDER BY o.created_at DESC
  `;
  
  db.query(query, (err, results) => {
    if (err) {
      console.error(err);
      return res.status(500).json({ error: "Database error" });
    }
    res.json(results);
  });
});

// Update order status
router.put("/orders/:id/status", (req, res) => {
  const orderId = req.params.id;
  const { status } = req.body;
  
  if (!status) {
    return res.status(400).json({ error: "Status is required" });
  }
  
  db.query(
    "UPDATE orders SET status = ? WHERE id = ?",
    [status, orderId],
    (err, result) => {
      if (err) {
        console.error(err);
        return res.status(500).json({ error: "Failed to update order" });
      }
      res.json({ success: true, message: "Order status updated" });
    }
  );
});

module.exports = router;