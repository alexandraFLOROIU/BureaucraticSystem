package BeaureaticSystems.clients;

import BeaureaticSystems.document.DocumentType;
import BeaureaticSystems.document.DocumentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

@Service
@AllArgsConstructor
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DocumentTypeService documentTypeService;

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public Client createClient(String name, List<DocumentType> ownedDocuments) {
        Client client = new Client(name);
        client.setOwnedDocuments(ownedDocuments);
        return clientRepository.save(client);
    }

    public Client getClient(int clientId) {
        Optional<Client>client = clientRepository.findById(clientId);
        return client.orElseThrow(null);
    }

    public Client getClientByName(String name) {
        return clientRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Client with name '" + name + "' not found"));
    }

    public Client addDocumentToClient(int clientId, int documentId) {
        Client client = getClient(clientId);
        DocumentType targetDocument = documentTypeService.getDocumentTypeById(documentId);
        if (client == null) {
            throw new RuntimeException("Client not found");
        }
        if(targetDocument == null) {
            throw new RuntimeException("Document not found");
        }
        if(client.getOwnedDocuments().contains(targetDocument)) {
            throw new RuntimeException("Document already owned");
        }
        client.getOwnedDocuments().add(targetDocument);
        return clientRepository.save(client);
    }

    public Client removeDocumentFromClient(int clientId, int docId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));

        // Filtrare pentru eliminarea documentului
        boolean removed = client.getOwnedDocuments().removeIf(doc -> doc.getId() == docId);

        if (!removed) {
            throw new RuntimeException("Document not found for client: " + docId);
        }

        // Salvează modificările clientului
        clientRepository.save(client);
        return client;
    }

    public Client setDefaultDocs(int clientId) {
        List<DocumentType> defaultDocs = documentTypeService.getAllDocumentTypesWithNoDependencies();
        Client client = getClient(clientId);
        if (client == null) {
            throw new RuntimeException("Client not found");
        }
        if(defaultDocs == null) {
            throw new RuntimeException("Document not found");
        }
        for(DocumentType defaultDoc : defaultDocs) {
            client.getOwnedDocuments().add(defaultDoc);
        }
        return clientRepository.save(client);
    }

    public List<DocumentType> getOwnedDocuments(int clientId) {
        // Căutăm clientul în baza de date
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));

        // Returnăm lista ownedDocuments
        return client.getOwnedDocuments();
    }

    public List<Client> getAllClients() {
        // Găsim toți clienții folosind repository-ul
        return clientRepository.findAll();
    }

}
