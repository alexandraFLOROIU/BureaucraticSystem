import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const DocumentPage = () => {
  const [documents, setDocuments] = useState([]);
  const [ownedDocuments, setOwnedDocuments] = useState([]);
  const [message, setMessage] = useState('');
  const [isProcessing, setIsProcessing] = useState(false);
  const [clientName, setClientName] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('client'));
    if (!user) {
      navigate('/login');
    } else {
      fetchDocuments();
      fetchOwnedDocuments(user.id);
      setClientName(user.name);
    }
  }, [navigate]);

  const fetchDocuments = async () => {
    try {
      const response = await axios.get('http://localhost:7443/document');
      setDocuments(response.data);
    } catch (error) {
      console.error('Error fetching documents:', error);
    }
  };

  const fetchOwnedDocuments = async (clientId) => {
    try {
      const response = await axios.get(`http://localhost:7443/client/${clientId}/ownedDocuments`);
      setOwnedDocuments(response.data);
    } catch (error) {
      console.error('Error fetching owned documents:', error);
    }
  };

  const handleRequestDocument = async (docId) => {
    setIsProcessing(true);
    setMessage('Processing request...');
    try {
      const user = JSON.parse(localStorage.getItem('client'));
      const response = await axios.post('http://localhost:7443/office/document', null, {
        params: {
          clientId: user.id,
          documentId: docId,
        },
      });

      if (response.status === 201) {
        setMessage(`Document with ID ${docId} successfully added to your account.`);
        fetchOwnedDocuments(user.id);
      }
    } catch (error) {
      setMessage('Failed to request document. Please try again.');
    } finally {
      setIsProcessing(false);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('client');
    navigate('/login');
  };

  return (
    <div style={{ display: 'flex', flexDirection: 'column', padding: '20px' }}>
      <style>
        {`
          @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
          }
        `}
      </style>
      <div style={{ backgroundColor: '#6f42c1', color: 'white', padding: '15px 20px', textAlign: 'center', fontSize: '24px', fontWeight: 'bold', marginBottom: '20px', width: '100%' }}>
        Welcome, {clientName}!
      </div>

      <div style={{ display: 'flex', justifyContent: 'space-between', marginTop: '20px' }}>
        <div style={{ flex: 1, marginRight: '20px' }}>
          <h2>Available Documents</h2>
          {message && <p style={{ color: 'green' }}>{message}</p>}
          <ul style={{ paddingLeft: 0, listStyleType: 'none' }}>
            {documents.map((doc) => (
              <li key={doc.id} style={{ marginBottom: '15px' }}>
                <div style={{ display: 'flex', alignItems: 'center', gap: '5px', justifyContent: 'flex-start' }}>
                  <span style={{ flex: 'none' }}>{doc.name}</span>
                  <button
                    onClick={() => handleRequestDocument(doc.id)}
                    style={{
                      padding: '4px 10px',
                      backgroundColor: '#6f42c1',
                      color: 'white',
                      border: 'none',
                      borderRadius: '4px',
                      cursor: 'pointer',
                      pointerEvents: isProcessing ? 'none' : 'auto',
                    }}
                  >
                    Request
                  </button>
                </div>
              </li>
            ))}
          </ul>
        </div>

        <div style={{ flex: 1 }}>
          <h2>Owned Documents</h2>
          <ul style={{ paddingLeft: 0, listStyleType: 'none' }}>
            {ownedDocuments.map((doc) => (
              <li key={doc.id} style={{ marginBottom: '10px' }}>
                {doc.name}
              </li>
            ))}
          </ul>
        </div>
      </div>

      <div style={{ position: 'absolute', bottom: '20px', left: '20px' }}>
        <button onClick={handleLogout} style={{ padding: '10px 20px', backgroundColor: '#dc3545', color: 'white', border: 'none', borderRadius: '4px' }}>
          Logout
        </button>
      </div>

      {isProcessing && (
  <div
    style={{
      position: 'fixed',
      top: 0,
      left: 0,
      width: '100%',
      height: '100%',
      backgroundColor: 'rgba(0, 0, 0, 0.5)',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      zIndex: 1000,
      pointerEvents: 'none',
    }}
  >
    <div
      style={{
        color: '#ffffff', // Text alb pentru un contrast mai bun
        fontSize: '24px', // Mărim fontul
        fontWeight: 'bold', // Text îngroșat
        textShadow: '1px 1px 4px rgba(0, 0, 0, 0.7)', // Adăugăm o umbră pentru lizibilitate
        textAlign: 'center',
        display: 'flex',
        flexDirection: 'column', // Aranjează textul și spinnerul pe verticală
        alignItems: 'center',
        gap: '10px',
      }}
    >
      <p style={{ margin: 0 }}>{message}</p>
      <div
        className="spinner"
        style={{
          border: '8px solid #f3f3f3',
          borderTop: '8px solid #6f42c1',
          borderRadius: '50%',
          width: '40px',
          height: '40px',
          animation: 'spin 2s linear infinite',
        }}
      />
    </div>
  </div>
)}
    </div>
  );
};

export default DocumentPage;
