import React, { useState, useEffect } from 'react';
import axios from 'axios';

const AdminPage = () => {
  const [offices, setOffices] = useState([]);
  const [clients, setClients] = useState([]);
  const [selectedClientDocuments, setSelectedClientDocuments] = useState([]);
  const [isPopupOpen, setIsPopupOpen] = useState(false);
  const [message, setMessage] = useState('');

  useEffect(() => {
    fetchOffices();
    fetchClients();

    const intervalId = setInterval(() => {
      fetchOffices();
      fetchClients();
    }, 10000); // 10 secunde

    return () => clearInterval(intervalId);
  }, []);

  const fetchOffices = async () => {
    try {
      const response = await axios.get('http://localhost:7443/office');
      setOffices(response.data);
    } catch (error) {
      console.error('Error fetching offices:', error);
      setMessage('Failed to fetch offices. Please try again later.');
    }
  };

  const fetchClients = async () => {
    try {
      const response = await axios.get('http://localhost:7443/client/all');
      setClients(response.data);
    } catch (error) {
      console.error('Error fetching clients:', error);
      setMessage('Failed to fetch clients. Please try again later.');
    }
  };

  const addCounterToOffice = async (officeId) => {
    try {
      const createCounterResponse = await axios.post('http://localhost:7443/counter', { status: 'AVAILABLE' });
      const newCounter = createCounterResponse.data;

      const patchResponse = await axios.patch(`http://localhost:7443/office/${officeId}/counter`, newCounter);

      if (patchResponse.status === 200) {
        setMessage(`Counter successfully added to Office ID: ${officeId}`);
        fetchOffices();
      }
    } catch (error) {
      console.error('Error adding counter:', error);
      setMessage('Failed to add counter. Please try again later.');
    }
  };

  const deleteCounter = async (officeId, counterId) => {
    try {
      const patchResponse = await axios.patch(`http://localhost:7443/office/${officeId}/counter/${counterId}`);
      if (patchResponse.status === 200 || patchResponse.status === 201) {
        await axios.delete(`http://localhost:7443/counter/${counterId}`);
        setMessage('Counter deleted successfully!');
        fetchOffices();
      } else {
        setMessage('Failed to remove counter from office. Please try again later.');
      }
    } catch (error) {
      console.error('Error deleting counter:', error);
      setMessage('Failed to delete counter. Please try again later.');
    }
  };

  const fetchClientDocuments = async (clientId) => {
    try {
      const response = await axios.get(`http://localhost:7443/client/${clientId}/ownedDocuments`);
      setSelectedClientDocuments(response.data);
      setIsPopupOpen(true);
    } catch (error) {
      console.error('Error fetching client documents:', error);
      setMessage('Failed to fetch client documents. Please try again later.');
    }
  };

  const closePopup = () => {
    setIsPopupOpen(false);
    setSelectedClientDocuments([]);
  };

  return (
    <div style={{ display: 'flex', flexDirection: 'row', padding: '20px' }}>
      {/* Secțiunea pentru birouri */}
      <div style={{ flex: 1, paddingRight: '20px', borderRight: '1px solid #ddd' }}>
        <h1>City Hall Offices</h1>
        {message && <p style={{ color: 'red' }}>{message}</p>}

        <div style={{ marginTop: '20px' }}>
          {offices.length > 0 ? (
            offices.map((office) => (
              <div
                key={office.id}
                style={{
                  border: '1px solid #ddd',
                  borderRadius: '8px',
                  padding: '15px',
                  marginBottom: '20px',
                }}
              >
                <h3 style={{ margin: '0 0 10px 0', color: 'gray' }}>Office ID: {office.id}</h3>
                <h2 style={{ margin: '0 0 10px 0' }}>{office.name}</h2>
                <p><strong>Counters:</strong> {office.counters?.length || 0}</p>

                {office.counters && office.counters.length > 0 ? (
                  <ul style={{ paddingLeft: '20px', marginTop: '5px' }}>
                    {office.counters.map((counter) => (
                      <li key={counter.id}>
                        <div style={{ display: 'flex', alignItems: 'center' }}>
                          <span>
                            Counter ID: {counter.id} - Status:{' '}
                            <span style={{ color: getStatusColor(counter.status) }}>
                              {counter.status}
                            </span>
                          </span>
                          <button
                            onClick={() => deleteCounter(office.id, counter.id)}
                            style={{
                              marginLeft: '10px',
                              padding: '5px 10px',
                              backgroundColor: 'red',
                              color: 'white',
                              border: 'none',
                              borderRadius: '5px',
                              cursor: 'pointer',
                            }}
                          >
                            Delete Counter
                          </button>
                        </div>
                      </li>
                    ))}
                  </ul>
                ) : (
                  <p>No counters available.</p>
                )}

                <button
                  onClick={() => addCounterToOffice(office.id)}
                  style={{
                    marginTop: '10px',
                    padding: '10px 15px',
                    backgroundColor: 'green',
                    color: 'white',
                    border: 'none',
                    borderRadius: '5px',
                    cursor: 'pointer',
                  }}
                >
                  Add Counter
                </button>

                <div style={{ marginTop: '20px' }}>
                  <h4>Available Documents:</h4>
                  {office.compatibleDocumentTypes && office.compatibleDocumentTypes.length > 0 ? (
                    <ul>
                      {office.compatibleDocumentTypes.map((documentType, index) => (
                        <li key={index}>{documentType.name}</li>
                      ))}
                    </ul>
                  ) : (
                    <p>No documents available for this office.</p>
                  )}
                </div>
              </div>
            ))
          ) : (
            <p>No offices available.</p>
          )}
        </div>
      </div>

      {/* Secțiunea pentru clienți */}
      <div style={{ flex: 1, paddingLeft: '20px' }}>
        <h1>Clients</h1>
        {clients.length > 0 ? (
          <ul style={{ listStyle: 'none', padding: 0, margin: 0 }}>
            {clients.map((client) => (
              <li key={client.id} style={{ marginBottom: '10px' }}>
                <button
                  onClick={() => fetchClientDocuments(client.id)}
                  style={{
                    background: 'none',
                    border: 'none',
                    padding: 0,
                    fontSize: 'inherit',
                    color: 'inherit',
                    textAlign: 'left',
                    cursor: 'pointer',
                  }}
                >
                  {client.name}
                </button>
              </li>
            ))}
          </ul>
        ) : (
          <p>No clients available.</p>
        )}
      </div>

      {/* Popup pentru documentele clientului */}
      {isPopupOpen && (
        <>
          <div
            style={{
              position: 'fixed',
              top: '50%',
              left: '50%',
              transform: 'translate(-50%, -50%)',
              backgroundColor: 'white',
              padding: '20px',
              borderRadius: '10px',
              boxShadow: '0 0 10px rgba(0, 0, 0, 0.3)',
              zIndex: 1000,
            }}
          >
            <h2>Client Documents</h2>
            {selectedClientDocuments.length > 0 ? (
              <ul>
                {selectedClientDocuments.map((doc, index) => (
                  <li key={index}>{doc.name}</li>
                ))}
              </ul>
            ) : (
              <p>No documents available.</p>
            )}
            <button
              onClick={closePopup}
              style={{
                marginTop: '20px',
                padding: '10px 15px',
                backgroundColor: 'red',
                color: 'white',
                border: 'none',
                borderRadius: '5px',
                cursor: 'pointer',
              }}
            >
              Close
            </button>
          </div>
          <div
            onClick={closePopup}
            style={{
              position: 'fixed',
              top: 0,
              left: 0,
              width: '100%',
              height: '100%',
              backgroundColor: 'rgba(0, 0, 0, 0.5)',
              zIndex: 999,
            }}
          ></div>
        </>
      )}
    </div>
  );
};

const getStatusColor = (status) => {
  switch (status) {
    case 'AVAILABLE':
      return 'green';
    case 'BUSY':
      return 'red';
    case 'BREAK':
      return 'orange';
    default:
      return 'black';
  }
};

export default AdminPage;
