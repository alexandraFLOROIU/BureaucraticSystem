import React from 'react';
import { Link } from 'react-router-dom';

function HomePage() {
  return (
    <div style={{ textAlign: 'center', marginTop: '20%' }}>
      <h1>Welcome to the CityHall App</h1>
      <p>Choose an option below:</p>
      <div style={{ marginTop: '20px', display: 'flex', justifyContent: 'center', gap: '20px' }}>
        <Link to="/register">
          <button
            style={{
              padding: '10px 20px',
              fontSize: '16px',
              cursor: 'pointer',
              backgroundColor: '#6f42c1',
              color: 'white',
              border: 'none',
              borderRadius: '4px',
              transition: 'background-color 0.3s',
            }}
            onMouseOver={(e) => (e.target.style.backgroundColor = '#5a3598')}
            onMouseOut={(e) => (e.target.style.backgroundColor = '#6f42c1')}
          >
            Register
          </button>
        </Link>
        <Link to="/login">
          <button
            style={{
              padding: '10px 20px',
              fontSize: '16px',
              cursor: 'pointer',
              backgroundColor: '#6f42c1',
              color: 'white',
              border: 'none',
              borderRadius: '4px',
              transition: 'background-color 0.3s',
            }}
            onMouseOver={(e) => (e.target.style.backgroundColor = '#5a3598')}
            onMouseOut={(e) => (e.target.style.backgroundColor = '#6f42c1')}
          >
            Login
          </button>
        </Link>
      </div>
    </div>
  );
}

export default HomePage;