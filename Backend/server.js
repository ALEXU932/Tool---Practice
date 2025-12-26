const express = require("express");
const cors = require("cors");
const path = require("path");

const app = express();

// Middleware
app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Serve static files
app.use("/uploads", express.static(path.join(__dirname, "uploads")));
app.use("/assets", express.static(path.join(__dirname, "../Assets")));

// Routes
app.use("/api/auth", require("./routes/authRoutes"));
app.use("/api/products", require("./routes/productRoutes"));
app.use("/api/orders", require("./routes/orderRoutes"));
app.use("/api/admin", require("./routes/adminRoutes"));
app.use("/api/user", require("./routes/userRoutes"));

// Serve frontend
app.use("/admin", express.static(path.join(__dirname, "../Frontend/admin")));
app.use("/user", express.static(path.join(__dirname, "../Frontend/user")));
app.use("/css", express.static(path.join(__dirname, "../Frontend/css")));
app.use("/js", express.static(path.join(__dirname, "../Frontend/js")));

// Default route
app.get("/", (req, res) => {
  res.redirect("/user");
});

// Error handling
app.use((err, req, res, next) => {
  console.error(err.stack);
  res.status(500).json({ error: "Something went wrong!" });
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});