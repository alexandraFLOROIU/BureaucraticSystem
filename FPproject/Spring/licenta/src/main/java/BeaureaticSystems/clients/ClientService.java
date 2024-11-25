package BeaureaticSystems.clients;

import BeaureaticSystems.document.DocumentType;
import BeaureaticSystems.document.DocumentTypeRepository;
import BeaureaticSystems.document.DocumentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DocumentTypeService documentTypeService;

    public void createClient(Client client) {clientRepository.save(client);}

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    public void startClientProcess(int clientId, int documentId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        DocumentType targetDocument = documentTypeService.getDocumentTypeById(documentId);

        client.initialize(targetDocument);
        Thread clientThread = new Thread(client);
        clientThread.start();
    }
}
