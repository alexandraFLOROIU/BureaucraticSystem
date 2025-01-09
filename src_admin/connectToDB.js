const mysql = require('mysql2');

const db = mysql.createConnection({
  host: 'localhost',
  user: 'root', // utilizatorul tău de MySQL (default în XAMPP este "root")
  password: '', // parola (de obicei goală în XAMPP)
  database: 'beaureaticsystem', // Numele bazei tale de date
});

db.connect((err) => {
  if (err) {
    console.error('Could not connect to MySQL:', err);
  } else {
    console.log('Connected to MySQL');
  }
});
