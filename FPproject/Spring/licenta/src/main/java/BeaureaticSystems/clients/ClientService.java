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

   public void startClientProcess(int clientId, int documentId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        DocumentType targetDocument = documentTypeService.getDocumentTypeById(documentId);

        executorService.submit(() -> processClientDocuments(client, targetDocument));
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

    private void processClientDocuments(Client client, DocumentType targetDocument) {
        System.out.println("Client " + client.getId() + " started process to obtain: " + targetDocument.getName());

        if (client.getOwnedDocuments().contains(targetDocument)) {
            System.out.println("Client " + client.getId() + " already owns document: " + targetDocument.getName());
            return;
        }

        for (DocumentType dependency : targetDocument.getRequiredDocs()) {
            if (!client.getOwnedDocuments().contains(dependency)) {
                System.out.println("Client " + client.getId() + " processing dependency: " + dependency.getName());
                processClientDocuments(client, dependency);
            }
        }

        // Simulare procesare document
        try {
            System.out.println("Client " + client.getId() + " is processing document: " + targetDocument.getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        client.getOwnedDocuments().add(targetDocument);
        clientRepository.save(client);
        System.out.println("Client " + client.getId() + " successfully obtained document: " + targetDocument.getName());
    }
}
