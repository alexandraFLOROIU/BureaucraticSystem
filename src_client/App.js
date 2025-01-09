import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './HomePage';
import RegisterPage from './RegisterPage';
import LoginPage from './LoginPage';
import DocumentPage from './DocumentPage';

function App() {
  return (
    <Router>
      <Routes>
        {/* Definește ruta principală către HomePage */}
        <Route path="/" element={<HomePage />} />
        
        {/* Definește rutele pentru LoginPage și RegisterPage */}
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/document" element={<DocumentPage />} />
      </Routes>
    </Router>
  );
}

export default App;
