const db = require("../config/db");

exports.login = (req, res) => {
  const { email, password } = req.body;

  db.query(
    "SELECT * FROM users WHERE email=? AND password=?",
    [email, password],
    (err, result) => {
      if (result.length === 0)
        return res.status(401).json({ message: "Invalid credentials" });

      const user = result[0];
      res.json({
        role: user.role,
        userId: user.id
      });
    }
  );
};
