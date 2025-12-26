const db = require("../config/db");

exports.getProducts = (req, res) => {
  db.query("SELECT * FROM products", (err, rows) => {
    res.json(rows);
  });
};

exports.addProduct = (req, res) => {
  const { name, price, stock } = req.body;
  db.query(
    "INSERT INTO products VALUES (NULL,?,?,?)",
    [name, price, stock],
    () => res.send("Product added")
  );
};
