const db = require("../config/db");

exports.createOrder = (req, res) => {
  const { user_id, total } = req.body;
  db.query(
    "INSERT INTO orders VALUES (NULL,?,?, 'Pending', NOW())",
    [user_id, total],
    () => res.send("Order placed")
  );
};

exports.getOrders = (req, res) => {
  db.query("SELECT * FROM orders", (err, rows) => {
    res.json(rows);
  });
};
