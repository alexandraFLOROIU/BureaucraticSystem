import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
  const backendUrl = 'http://localhost:7443';
  const [username, setUsername] = useState('');
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await axios.get(`${backendUrl}/client`, { params: { name: username } });
      if (response.status === 200) {
        localStorage.setItem('client', JSON.stringify(response.data));
        navigate('/document');
      } else {
        setError('Login failed. Please try again.');
      }
    } catch (err) {
      setError(err.response?.data?.message || 'Client not found. Please try again.');
    }
  };

  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        height: '100vh',
        fontFamily: "'Arial', sans-serif",
        color: '#333',
      }}
    >
      <h1 style={{ marginBottom: '20px' }}>Login</h1>
      <p style={{ fontSize: '18px', marginBottom: '30px', textAlign: 'center' }}>
        Enter your username to login.
      </p>
      <div
        style={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          width: '300px',
        }}
      >
        <label
          htmlFor="username"
          style={{
            fontSize: '16px',
            marginBottom: '10px',
            textAlign: 'left',
            width: '100%',
          }}
        >
          Username:
        </label>
        <input
          type="text"
          id="username"
          placeholder="Enter username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          style={{
            padding: '10px',
            fontSize: '16px',
            width: '100%',
            border: '1px solid #ccc',
            borderRadius: '4px',
            marginBottom: '20px',
          }}
        />
        <button
          onClick={handleLogin}
          style={{
            padding: '8px 16px',
            fontSize: '14px',
            cursor: 'pointer',
            backgroundColor: '#6f42c1',
            color: 'white',
            border: 'none',
            borderRadius: '4px',
            width: '150px',
          }}
        >
          Login
        </button>
      </div>
      {error && (
        <p
          style={{
            marginTop: '20px',
            fontSize: '16px',
            color: 'red',
          }}
        >
          {error}
        </p>
      )}
      <p style={{ marginTop: '30px', fontSize: '16px' }}>
        Don't have an account?{' '}
        <span
          style={{
            color: '#6f42c1',
            cursor: 'pointer',
            textDecoration: 'underline',
          }}
          onClick={() => navigate('/register')}
        >
          Register here
        </span>
      </p>
    </div>
  );
};

export default LoginPage;