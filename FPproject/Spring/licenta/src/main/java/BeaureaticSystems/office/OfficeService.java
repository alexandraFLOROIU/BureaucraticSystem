package BeaureaticSystems.office;

import BeaureaticSystems.clients.Client;
import BeaureaticSystems.clients.ClientRepository;
import BeaureaticSystems.document.DocumentType;
import BeaureaticSystems.document.DocumentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Client requestDocument(int clientId, int documentId) {
       // Office office = officeRepository.findById(officeId)
               // .orElseThrow(() -> new RuntimeException("Office not found: " + officeId));
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found: " + clientId));
        DocumentType targetDocument = documentTypeRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("DocumentType not found: " + documentId));

        executorService.submit(() -> processDocumentRequest(client, targetDocument));
        return client;
    }

    // Procesarea cererii unui document
    @Transactional
    private void processDocumentRequest(Client client, DocumentType targetDocument) {
        System.out.println("intru in metoda process");

        synchronized (client) {
           // System.out.println("Office " + office.getId() + ": Processing request for client " + client.getId() + " - Document: " + targetDocument.getName());

            // Verificăm dacă clientul deține deja documentul
            if (client.getOwnedDocuments().contains(targetDocument)) {
                System.out.println("Client " + client.getId() + " already owns document: " + targetDocument.getName());
                return;
            }
            else {
                System.out.println("aici nu ajung");
                // Procesarea documentelor dependente
                for (DocumentType dependency : targetDocument.getRequiredDocs()) {
                    System.out.println("parcurg lista de dependente a targetDocument");
                    for (Office o : this.getAllOffices()) {
                        System.out.println("parcurg birouri");

                            for (DocumentType doc : o.getCompatibleDocumentTypes()) {
                                if (doc.getId() == dependency.getId()) { // Compară doar pe baza ID-ului


                                    System.out.println("verific documentu din birou");
                                    if (!client.getOwnedDocuments().contains(dependency)) {
                                        System.out.println("verific daca clientu are dependenta");
                                        System.out.println("Processing dependency for client " + client.getId() + ": " + dependency.getName());
                                        processDocumentRequest(client, dependency);
                                    } else {
                                        System.out.println("nu merge");
                                    }
                                }
                        else{
                                System.out.println("nu gaseste in birou documentul");
                            }
                        }
                    }
                }
            }
            // Simularea procesării documentului
            try {
              //  System.out.println("Office " + office.getId() + " is preparing document: " + targetDocument.getName() + " for client " + client.getId());
                Thread.sleep(1000); // Simulare procesare
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Adăugarea documentului la client
            client.getOwnedDocuments().add(targetDocument);
            client.setTargetDocument(targetDocument);
            clientRepository.save(client);
            System.out.println("Client " + client.getId() + " successfully received document: " + targetDocument.getName());
        }
    }

    private boolean containsDocument(List<DocumentType> documentTypes, DocumentType dependency) {
        for (DocumentType doc : documentTypes) {
            if (doc.getId() == dependency.getId()) { // Compară doar pe baza ID-ului
                return true;
            }
        }
        return false;
    }

}