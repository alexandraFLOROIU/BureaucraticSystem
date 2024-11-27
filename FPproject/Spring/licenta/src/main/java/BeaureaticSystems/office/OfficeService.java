package BeaureaticSystems.office;

import BeaureaticSystems.clients.Client;
import BeaureaticSystems.clients.ClientRepository;
import BeaureaticSystems.document.DocumentType;
import BeaureaticSystems.document.DocumentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@AllArgsConstructor
public class OfficeService {
    private OfficeRepository officeRepository;
    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private ClientRepository clientRepository;

    // Thread pool pentru gestionarea cererilor multiple
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void createOffice(Office office) {
        List<DocumentType> managedDocumentTypes = office.getCompatibleDocumentTypes().stream()
                .map(doc -> documentTypeRepository.findById(doc.getId())
                        .orElseThrow(() -> new RuntimeException("DocumentType not found: " + doc.getId())))
                .toList();
        office.setCompatibleDocumentTypes(managedDocumentTypes);
        officeRepository.save(office);
    }

    public Office getOfficeById(int id) {
        Optional<Office> office = officeRepository.findById(id);
        return office.orElse(null);
    }

    public Office saveOffice(Office office) {
        officeRepository.save(office);
        return office;
    }

    public Iterable<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    // Cererea unui document de către un client
    public Client requestDocument(int officeId, int clientId, int documentId) {
        Office office = officeRepository.findById(officeId)
                .orElseThrow(() -> new RuntimeException("Office not found: " + officeId));
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found: " + clientId));
        DocumentType targetDocument = documentTypeRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("DocumentType not found: " + documentId));

        executorService.submit(() -> processDocumentRequest(office, client, targetDocument));
        return client;
    }

    // Procesarea cererii unui document
    private void processDocumentRequest(Office office, Client client, DocumentType targetDocument) {
        synchronized (client) {
            System.out.println("Office " + office.getId() + ": Processing request for client " + client.getId() + " - Document: " + targetDocument.getName());

            // Verificăm dacă clientul deține deja documentul
            if (client.getOwnedDocuments().contains(targetDocument)) {
                System.out.println("Client " + client.getId() + " already owns document: " + targetDocument.getName());
                return;
            }

            // Procesarea documentelor dependente
            for (DocumentType dependency : targetDocument.getRequiredDocs()) {
                if (!client.getOwnedDocuments().contains(dependency)) {
                    System.out.println("Processing dependency for client " + client.getId() + ": " + dependency.getName());
                    processDocumentRequest(office, client, dependency);
                }
            }

            // Simularea procesării documentului
            try {
                System.out.println("Office " + office.getId() + " is preparing document: " + targetDocument.getName() + " for client " + client.getId());
                Thread.sleep(1000); // Simulare procesare
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Adăugarea documentului la client
            client.getOwnedDocuments().add(targetDocument);
            clientRepository.save(client);
            System.out.println("Client " + client.getId() + " successfully received document: " + targetDocument.getName());
        }
    }
}