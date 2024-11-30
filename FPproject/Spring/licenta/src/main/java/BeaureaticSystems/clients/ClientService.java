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
}
