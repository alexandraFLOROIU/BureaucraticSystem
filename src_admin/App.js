import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AdminPage from './AdminPage';

function App() {
  return (
    <Router>
      <Routes>
        {/* Definește ruta principală către HomePage */}
        <Route path="/" element={<AdminPage />} />
        
      </Routes>
    </Router>
  );
}

export default App;
