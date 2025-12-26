const mysql = require("mysql2");

// Database connection with pooling for better performance
const db = mysql.createPool({
  host: "localhost",
  user: "Ethiopia",
  password: "1234",
  database: "Gihon",
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0
});

// Test connection and create all normalized tables
db.getConnection((err, connection) => {
  if (err) {
    console.error("Database connection failed:", err);
  } else {
    console.log("Connected to Gihon database");
    createAllTables(connection);
    connection.release();
  }
});

// All 32 normalized tables for complete e-commerce platform
function createAllTables(connection) {
  const tables = [
    // 1. USERS (core user table)
    `CREATE TABLE IF NOT EXISTS users (
      id INT AUTO_INCREMENT PRIMARY KEY,
      username VARCHAR(50) UNIQUE NOT NULL,
      email VARCHAR(100) UNIQUE NOT NULL,
      password_hash VARCHAR(255) NOT NULL,
      first_name VARCHAR(50) NOT NULL,
      last_name VARCHAR(50) NOT NULL,
      phone VARCHAR(20),
      role ENUM('admin', 'user') DEFAULT 'user',
      is_active BOOLEAN DEFAULT TRUE,
      email_verified BOOLEAN DEFAULT FALSE,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      INDEX idx_email (email),
      INDEX idx_role (role)
    )`,

    // 2. USER_ADDRESSES (user shipping/billing addresses)
    `CREATE TABLE IF NOT EXISTS user_addresses (
      id INT AUTO_INCREMENT PRIMARY KEY,
      user_id INT NOT NULL,
      address_type ENUM('shipping', 'billing', 'both') DEFAULT 'both',
      full_name VARCHAR(100) NOT NULL,
      phone VARCHAR(20) NOT NULL,
      address_line1 VARCHAR(255) NOT NULL,
      address_line2 VARCHAR(255),
      city VARCHAR(100) NOT NULL,
      state VARCHAR(100),
      country VARCHAR(100) DEFAULT 'Ethiopia',
      postal_code VARCHAR(20),
      is_default BOOLEAN DEFAULT FALSE,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
      INDEX idx_user_id (user_id),
      INDEX idx_address_type (address_type)
    )`,

    // 3. CATEGORIES (product categories with hierarchy)
    `CREATE TABLE IF NOT EXISTS categories (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(100) NOT NULL,
      slug VARCHAR(100) UNIQUE NOT NULL,
      parent_id INT,
      description TEXT,
      image VARCHAR(255),
      display_order INT DEFAULT 0,
      is_active BOOLEAN DEFAULT TRUE,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (parent_id) REFERENCES categories(id) ON DELETE SET NULL,
      INDEX idx_slug (slug),
      INDEX idx_parent_id (parent_id),
      INDEX idx_is_active (is_active)
    )`,

    // 4. BRANDS
    `CREATE TABLE IF NOT EXISTS brands (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(100) NOT NULL UNIQUE,
      slug VARCHAR(100) UNIQUE NOT NULL,
      description TEXT,
      logo VARCHAR(255),
      is_active BOOLEAN DEFAULT TRUE,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      INDEX idx_slug (slug),
      INDEX idx_is_active (is_active)
    )`,

    // 5. PRODUCTS (main product table)
    `CREATE TABLE IF NOT EXISTS products (
      id INT AUTO_INCREMENT PRIMARY KEY,
      sku VARCHAR(50) UNIQUE NOT NULL,
      name VARCHAR(255) NOT NULL,
      slug VARCHAR(255) UNIQUE NOT NULL,
      short_description VARCHAR(500),
      description TEXT,
      brand_id INT,
      category_id INT NOT NULL,
      regular_price DECIMAL(12,2) NOT NULL,
      sale_price DECIMAL(12,2),
      cost_price DECIMAL(12,2),
      is_taxable BOOLEAN DEFAULT TRUE,
      tax_rate DECIMAL(5,2) DEFAULT 15.00,
      is_active BOOLEAN DEFAULT TRUE,
      is_featured BOOLEAN DEFAULT FALSE,
      is_bestseller BOOLEAN DEFAULT FALSE,
      weight DECIMAL(8,2),
      dimensions VARCHAR(100),
      tags JSON,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      FOREIGN KEY (category_id) REFERENCES categories(id),
      FOREIGN KEY (brand_id) REFERENCES brands(id),
      INDEX idx_sku (sku),
      INDEX idx_slug (slug),
      INDEX idx_category_id (category_id),
      INDEX idx_brand_id (brand_id),
      INDEX idx_is_active (is_active),
      INDEX idx_is_featured (is_featured),
      INDEX idx_is_bestseller (is_bestseller),
      INDEX idx_regular_price (regular_price)
    )`,

    // 6. PRODUCT_INVENTORY (separate inventory management)
    `CREATE TABLE IF NOT EXISTS product_inventory (
      product_id INT PRIMARY KEY,
      stock_quantity INT DEFAULT 0 NOT NULL,
      low_stock_threshold INT DEFAULT 10,
      is_in_stock BOOLEAN DEFAULT TRUE,
      allow_backorders BOOLEAN DEFAULT FALSE,
      last_restocked TIMESTAMP NULL,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
      INDEX idx_is_in_stock (is_in_stock),
      INDEX idx_stock_quantity (stock_quantity)
    )`,

    // 7. PRODUCT_IMAGES (multiple images per product)
    `CREATE TABLE IF NOT EXISTS product_images (
      id INT AUTO_INCREMENT PRIMARY KEY,
      product_id INT NOT NULL,
      image_url VARCHAR(500) NOT NULL,
      alt_text VARCHAR(255),
      display_order INT DEFAULT 0,
      is_primary BOOLEAN DEFAULT FALSE,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
      INDEX idx_product_id (product_id),
      INDEX idx_display_order (display_order),
      INDEX idx_is_primary (is_primary)
    )`,

    // 8. PRODUCT_ATTRIBUTES (size, color, etc.)
    `CREATE TABLE IF NOT EXISTS product_attributes (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(100) NOT NULL UNIQUE,
      slug VARCHAR(100) UNIQUE NOT NULL,
      display_name VARCHAR(100),
      INDEX idx_slug (slug)
    )`,

    // 9. PRODUCT_ATTRIBUTE_VALUES (specific values)
    `CREATE TABLE IF NOT EXISTS product_attribute_values (
      id INT AUTO_INCREMENT PRIMARY KEY,
      attribute_id INT NOT NULL,
      value VARCHAR(100) NOT NULL,
      display_value VARCHAR(100),
      color_code VARCHAR(7),
      FOREIGN KEY (attribute_id) REFERENCES product_attributes(id) ON DELETE CASCADE,
      UNIQUE KEY unique_attribute_value (attribute_id, value),
      INDEX idx_attribute_id (attribute_id)
    )`,

    // 10. PRODUCT_VARIATIONS (for variable products)
    `CREATE TABLE IF NOT EXISTS product_variations (
      id INT AUTO_INCREMENT PRIMARY KEY,
      product_id INT NOT NULL,
      sku VARCHAR(50) UNIQUE NOT NULL,
      price_adjustment DECIMAL(10,2) DEFAULT 0.00,
      stock_quantity INT DEFAULT 0,
      is_active BOOLEAN DEFAULT TRUE,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
      INDEX idx_product_id (product_id),
      INDEX idx_sku (sku),
      INDEX idx_is_active (is_active)
    )`,

    // 11. PRODUCT_VARIATION_ATTRIBUTES (link variations to attributes)
    `CREATE TABLE IF NOT EXISTS product_variation_attributes (
      variation_id INT NOT NULL,
      attribute_value_id INT NOT NULL,
      PRIMARY KEY (variation_id, attribute_value_id),
      FOREIGN KEY (variation_id) REFERENCES product_variations(id) ON DELETE CASCADE,
      FOREIGN KEY (attribute_value_id) REFERENCES product_attribute_values(id) ON DELETE CASCADE
    )`,

    // 12. ORDER_STATUSES (lookup table)
    `CREATE TABLE IF NOT EXISTS order_statuses (
      code VARCHAR(20) PRIMARY KEY,
      name VARCHAR(50) NOT NULL,
      description TEXT,
      display_order INT DEFAULT 0
    )`,

    // 13. PAYMENT_METHODS (lookup table)
    `CREATE TABLE IF NOT EXISTS payment_methods (
      code VARCHAR(20) PRIMARY KEY,
      name VARCHAR(50) NOT NULL,
      is_active BOOLEAN DEFAULT TRUE,
      display_order INT DEFAULT 0
    )`,

    // 14. PAYMENT_STATUSES (lookup table)
    `CREATE TABLE IF NOT EXISTS payment_statuses (
      code VARCHAR(20) PRIMARY KEY,
      name VARCHAR(50) NOT NULL,
      description TEXT,
      display_order INT DEFAULT 0
    )`,

    // 15. SHIPPING_METHODS
    `CREATE TABLE IF NOT EXISTS shipping_methods (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(100) NOT NULL,
      description TEXT,
      cost DECIMAL(10,2) NOT NULL DEFAULT 0.00,
      estimated_days_min INT,
      estimated_days_max INT,
      is_active BOOLEAN DEFAULT TRUE,
      display_order INT DEFAULT 0,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      INDEX idx_is_active (is_active)
    )`,

    // 16. ORDERS (main order table)
    `CREATE TABLE IF NOT EXISTS orders (
      id INT AUTO_INCREMENT PRIMARY KEY,
      order_number VARCHAR(50) UNIQUE NOT NULL,
      user_id INT NOT NULL,
      status_code VARCHAR(20) DEFAULT 'pending',
      subtotal DECIMAL(12,2) NOT NULL,
      tax_amount DECIMAL(12,2) DEFAULT 0.00,
      shipping_amount DECIMAL(12,2) DEFAULT 0.00,
      discount_amount DECIMAL(12,2) DEFAULT 0.00,
      total_amount DECIMAL(12,2) NOT NULL,
      shipping_address_id INT,
      billing_address_id INT,
      shipping_method_id INT,
      customer_note TEXT,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      FOREIGN KEY (user_id) REFERENCES users(id),
      FOREIGN KEY (status_code) REFERENCES order_statuses(code),
      FOREIGN KEY (shipping_address_id) REFERENCES user_addresses(id),
      FOREIGN KEY (billing_address_id) REFERENCES user_addresses(id),
      FOREIGN KEY (shipping_method_id) REFERENCES shipping_methods(id),
      INDEX idx_order_number (order_number),
      INDEX idx_user_id (user_id),
      INDEX idx_status_code (status_code),
      INDEX idx_created_at (created_at)
    )`,

    // 17. ORDER_ITEMS (products in order)
    `CREATE TABLE IF NOT EXISTS order_items (
      id INT AUTO_INCREMENT PRIMARY KEY,
      order_id INT NOT NULL,
      product_id INT NOT NULL,
      variation_id INT,
      product_name VARCHAR(255) NOT NULL,
      sku VARCHAR(50) NOT NULL,
      quantity INT NOT NULL,
      unit_price DECIMAL(10,2) NOT NULL,
      total_price DECIMAL(10,2) NOT NULL,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
      FOREIGN KEY (product_id) REFERENCES products(id),
      FOREIGN KEY (variation_id) REFERENCES product_variations(id),
      INDEX idx_order_id (order_id),
      INDEX idx_product_id (product_id)
    )`,

    // 18. PAYMENTS (payment transactions)
    `CREATE TABLE IF NOT EXISTS payments (
      id INT AUTO_INCREMENT PRIMARY KEY,
      order_id INT NOT NULL,
      payment_method_code VARCHAR(20) NOT NULL,
      status_code VARCHAR(20) NOT NULL,
      transaction_id VARCHAR(100) UNIQUE,
      amount DECIMAL(12,2) NOT NULL,
      currency VARCHAR(3) DEFAULT 'ETB',
      gateway_response JSON,
      paid_at TIMESTAMP NULL,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
      FOREIGN KEY (payment_method_code) REFERENCES payment_methods(code),
      FOREIGN KEY (status_code) REFERENCES payment_statuses(code),
      INDEX idx_order_id (order_id),
      INDEX idx_transaction_id (transaction_id),
      INDEX idx_status_code (status_code)
    )`,

    // 19. DISCOUNTS (coupons and promotions)
    `CREATE TABLE IF NOT EXISTS discounts (
      id INT AUTO_INCREMENT PRIMARY KEY,
      code VARCHAR(50) UNIQUE NOT NULL,
      name VARCHAR(100) NOT NULL,
      description TEXT,
      discount_type ENUM('percentage', 'fixed_amount', 'free_shipping') NOT NULL,
      value DECIMAL(10,2) NOT NULL,
      min_order_amount DECIMAL(10,2) DEFAULT 0.00,
      max_discount_amount DECIMAL(10,2),
      start_date DATE,
      end_date DATE,
      usage_limit INT,
      times_used INT DEFAULT 0,
      is_active BOOLEAN DEFAULT TRUE,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      INDEX idx_code (code),
      INDEX idx_is_active (is_active)
    )`,

    // 20. ORDER_DISCOUNTS (applied discounts to orders)
    `CREATE TABLE IF NOT EXISTS order_discounts (
      order_id INT NOT NULL,
      discount_id INT NOT NULL,
      discount_amount DECIMAL(10,2) NOT NULL,
      PRIMARY KEY (order_id, discount_id),
      FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
      FOREIGN KEY (discount_id) REFERENCES discounts(id)
    )`,

    // 21. CARTS (shopping carts)
    `CREATE TABLE IF NOT EXISTS carts (
      id INT AUTO_INCREMENT PRIMARY KEY,
      user_id INT NOT NULL,
      session_id VARCHAR(100),
      is_active BOOLEAN DEFAULT TRUE,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
      INDEX idx_user_id (user_id),
      INDEX idx_session_id (session_id)
    )`,

    // 22. CART_ITEMS (items in cart)
    `CREATE TABLE IF NOT EXISTS cart_items (
      id INT AUTO_INCREMENT PRIMARY KEY,
      cart_id INT NOT NULL,
      product_id INT NOT NULL,
      variation_id INT,
      quantity INT NOT NULL DEFAULT 1,
      unit_price DECIMAL(10,2) NOT NULL,
      added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
      FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
      FOREIGN KEY (variation_id) REFERENCES product_variations(id) ON DELETE SET NULL,
      INDEX idx_cart_id (cart_id),
      INDEX idx_product_id (product_id)
    )`,

    // 23. WISHLISTS
    `CREATE TABLE IF NOT EXISTS wishlists (
      id INT AUTO_INCREMENT PRIMARY KEY,
      user_id INT NOT NULL,
      name VARCHAR(100) DEFAULT 'My Wishlist',
      is_private BOOLEAN DEFAULT TRUE,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
      INDEX idx_user_id (user_id)
    )`,

    // 24. WISHLIST_ITEMS
    `CREATE TABLE IF NOT EXISTS wishlist_items (
      wishlist_id INT NOT NULL,
      product_id INT NOT NULL,
      added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (wishlist_id, product_id),
      FOREIGN KEY (wishlist_id) REFERENCES wishlists(id) ON DELETE CASCADE,
      FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
    )`,

    // 25. REVIEWS (product reviews)
    `CREATE TABLE IF NOT EXISTS reviews (
      id INT AUTO_INCREMENT PRIMARY KEY,
      product_id INT NOT NULL,
      user_id INT NOT NULL,
      order_item_id INT,
      rating TINYINT NOT NULL CHECK (rating BETWEEN 1 AND 5),
      title VARCHAR(200),
      comment TEXT,
      is_approved BOOLEAN DEFAULT FALSE,
      helpful_votes INT DEFAULT 0,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
      FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
      FOREIGN KEY (order_item_id) REFERENCES order_items(id) ON DELETE SET NULL,
      UNIQUE KEY unique_product_user (product_id, user_id),
      INDEX idx_product_id (product_id),
      INDEX idx_rating (rating),
      INDEX idx_is_approved (is_approved)
    )`,

    // 26. SETTINGS (site settings)
    `CREATE TABLE IF NOT EXISTS settings (
      id INT AUTO_INCREMENT PRIMARY KEY,
      setting_key VARCHAR(100) UNIQUE NOT NULL,
      setting_value TEXT,
      data_type ENUM('string', 'integer', 'boolean', 'json') DEFAULT 'string',
      category VARCHAR(50) DEFAULT 'general',
      description TEXT,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      INDEX idx_setting_key (setting_key),
      INDEX idx_category (category)
    )`,

    // 27. Insert initial lookup data
    `INSERT IGNORE INTO order_statuses (code, name, description) VALUES
      ('pending', 'Pending', 'Order has been placed but not processed'),
      ('confirmed', 'Confirmed', 'Order has been confirmed'),
      ('processing', 'Processing', 'Order is being prepared'),
      ('shipped', 'Shipped', 'Order has been shipped'),
      ('delivered', 'Delivered', 'Order has been delivered'),
      ('cancelled', 'Cancelled', 'Order has been cancelled')
    `,

    `INSERT IGNORE INTO payment_methods (code, name, is_active) VALUES
      ('cash_on_delivery', 'Cash on Delivery', 1),
      ('bank_transfer', 'Bank Transfer', 1),
      ('mobile_banking', 'Mobile Banking', 1),
      ('credit_card', 'Credit Card', 1)
    `,

    `INSERT IGNORE INTO payment_statuses (code, name, description) VALUES
      ('pending', 'Pending', 'Payment is pending'),
      ('completed', 'Completed', 'Payment has been completed'),
      ('failed', 'Failed', 'Payment has failed'),
      ('refunded', 'Refunded', 'Payment has been refunded')
    `,

    `INSERT IGNORE INTO shipping_methods (name, description, cost, estimated_days_min, estimated_days_max, is_active) VALUES
      ('Standard Shipping', 'Delivery within 3-7 business days', 50.00, 3, 7, 1),
      ('Express Shipping', 'Delivery within 1-3 business days', 100.00, 1, 3, 1),
      ('Pickup from Store', 'Pick up your order from our store', 0.00, 0, 0, 1)
    `,

    `INSERT IGNORE INTO settings (setting_key, setting_value, data_type, category, description) VALUES
      ('site_name', 'Gihon E-Commerce', 'string', 'general', 'Website name'),
      ('site_email', 'admin@gihon.com', 'string', 'general', 'Admin email'),
      ('currency', 'ETB', 'string', 'general', 'Default currency'),
      ('tax_rate', '15', 'string', 'tax', 'Default tax rate'),
      ('enable_registration', 'true', 'boolean', 'users', 'Enable user registration')
    `
  ];

  // Create tables sequentially
  let currentIndex = 0;
  
  function createNextTable() {
    if (currentIndex >= tables.length) {
      console.log('✅ All normalized tables created successfully!');
      return;
    }
    
    connection.query(tables[currentIndex], (err) => {
      if (err) {
        console.error(`❌ Error creating table/insert ${currentIndex + 1}:`, err.message);
      } else {
        console.log(`✅ Table/insert ${currentIndex + 1} completed`);
      }
      currentIndex++;
      createNextTable();
    });
  }
  
  createNextTable();
}

module.exports = db;