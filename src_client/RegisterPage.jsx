import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function RegisterPage() {
  const backendUrl = 'http://localhost:7443';
  const [username, setUsername] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  // Verificăm disponibilitatea username-ului
  const checkUsernameAvailability = async (username) => {
    try {
      const response = await axios.get(`${backendUrl}/client`, {
        params: { name: username },
      });

      // Dacă răspunsul este 200, înseamnă că există deja un client cu acest nume
      if (response.status === 200) {
        return true; // Clientul există deja
      }
    } catch (error) {
      if (error.response?.status === 404) {
        return false; // Clientul nu există
      }
      // Dacă există o altă eroare, aruncăm eroarea
      throw new Error('Error while checking username availability');
    }
  };

  const handleRegister = async () => {
    // Verificăm dacă username-ul este deja utilizat
    const isUsernameTaken = await checkUsernameAvailability(username);

    if (isUsernameTaken) {
      setMessage('Username already taken. Please choose a different one.');
      return; // Oprim procesul de înregistrare
    }

    // Dacă username-ul nu este deja luat, continuăm cu înregistrarea
    try {
      const payload = {
        name: username,
        ownedDocuments: [],
      };
      const response = await axios.post(`${backendUrl}/client`, payload, {
        headers: { 'Content-Type': 'application/json' },
      });

      if (response.status === 200 || response.status === 201) {
        const clientId = response.data.id;
        setMessage('Client created successfully. Adding default documents...');
        await axios.patch(`${backendUrl}/client/${clientId}/default_documents`);
        setMessage('Registration successful with default documents!');
        setTimeout(() => navigate('/login'), 2000);
      }
    } catch (error) {
      setMessage(error.response?.data?.message || 'Registration failed. Please try again.');
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
      <h1 style={{ marginBottom: '20px' }}>Register</h1>
      <p style={{ fontSize: '18px', marginBottom: '30px', textAlign: 'center' }}>
        Enter a username to create an account.
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
          onClick={handleRegister}
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
          Register
        </button>
      </div>
      {message && (
        <p
          style={{
            marginTop: '20px',
            fontSize: '16px',
            color: message.includes('success') ? 'green' : 'red',
          }}
        >
          {message}
        </p>
      )}
      <p style={{ marginTop: '30px', fontSize: '16px' }}>
        Already have an account?{' '}
        <span
          style={{
            color: '#6f42c1',
            cursor: 'pointer',
            textDecoration: 'underline',
          }}
          onClick={() => navigate('/login')}
        >
          Login here
        </span>
      </p>
    </div>
  );
}

export default RegisterPage;