const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const mysql = require('mysql2');

const app = express();
app.use(bodyParser.json());
app.use(cors());

// Configurarea conexiunii la baza de date MySQL
const db = mysql.createConnection({
  host: 'localhost',       // Adresa serverului MySQL
  user: 'root',            // Numele de utilizator MySQL
  password: '', // Parola MySQL
  database: 'beaureaticsystem' // Numele bazei de date
});

// Conectarea la MySQL
db.connect((err) => {
  if (err) {
    console.error('Eroare la conectarea la MySQL:', err);
  } else {
    console.log('Conectat la MySQL');
  }
});

// Ruta pentru înregistrare
app.post('/register', (req, res) => {
  const { username } = req.body;

  // Adaugă utilizatorul în baza de date
  db.query('INSERT INTO clients (name) VALUES (?)', [username], (err, result) => {
    if (err) {
      console.error('Eroare la adăugarea utilizatorului:', err);
      return res.status(500).send({ message: 'Registration failed' });
    }
    res.status(201).send({ message: 'User registered' });
  });
});

// Ruta pentru login
app.post('/login', (req, res) => {
  const { username } = req.body;

  // Verifică dacă utilizatorul există în baza de date
  db.query('SELECT * FROM client WHERE name = ?', [username], (err, results) => {
    if (err) {
      console.error('Eroare la verificarea utilizatorului:', err);
      return res.status(500).send({ message: 'Internal server error' });
    }

    if (results.length > 0) {
      return res.status(200).send({ message: 'Login successful' });
    } else {
      return res.status(401).send({ message: 'Invalid username' });
    }
  });
});

// Creează document
app.post('/document', async (req, res) => {
  const { name } = req.body;

  try {
    // Salvează documentul în baza de date
    db.query('INSERT INTO document_type (name) VALUES (?)', [name], (err, result) => {
      if (err) {
        console.error('Eroare la adăugarea documentului:', err);
        return res.status(500).send({ message: 'Failed to request document' });
      }
      res.status(201).send({ message: 'Document requested' });
    });
  } catch (error) {
    res.status(500).send({ message: 'Failed to request document', error });
  }
});

// Listă documente
app.get('/documents', (req, res) => {
  db.query('SELECT * FROM document_type', (err, results) => {
    if (err) {
      console.error('Eroare la obținerea documentelor:', err);
      return res.status(500).send({ message: 'Failed to fetch documents' });
    }
    res.status(200).send(results);
  });
});

// Șterge document
app.delete('/document/:name', (req, res) => {
  const { name } = req.params;

  db.query('DELETE FROM document_type WHERE name = ?', [name], (err, result) => {
    if (err) {
      console.error('Eroare la ștergerea documentului:', err);
      return res.status(500).send({ message: 'Failed to delete document' });
    }

    if (result.affectedRows === 0) {
      return res.status(404).send({ message: 'Document not found' });
    }

    res.status(200).send({ message: 'Document deleted' });
  });
});

// Pornirea serverului
app.listen(3001, () => {
  console.log('Backend server running on http://localhost:7443');
});
