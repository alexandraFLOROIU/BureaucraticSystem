package BeaureaticSystems.clients;

import BeaureaticSystems.document.DocumentType;
import BeaureaticSystems.document.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping("first/")
    public String index() {
        return "index";
    }

    @PostMapping()
    public ResponseEntity<?> createClient(@RequestBody ClientDocument clientDocument) {
        try {
            String name = clientDocument.getName();
            List<DocumentType> ownedDocuments = clientDocument.getOwnedDocuments();

//            if (clientService.existsByName(name)) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client name already exists!");
//            }

            // Creează clientul
            Client client = clientService.createClient(name, ownedDocuments);
            return ResponseEntity.ok(client);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getClientByName(@RequestParam String name) {
        try {
            // Găsim clientul după nume folosind serviciul
            Client client = clientService.getClientByName(name);

            // Returnăm clientul găsit
            return ResponseEntity.ok(client);
        } catch (RuntimeException e) {
            // În caz de eroare, returnăm un mesaj corespunzător
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PatchMapping("/{id}/document")
    public ResponseEntity addDocument(@PathVariable int id, @RequestBody int docId) {
        try{
        Client servedClient = clientService.addDocumentToClient(id,docId);
        return ResponseEntity.status(HttpStatus.CREATED).body(servedClient);
        }catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}/document")
    public ResponseEntity<?> removeDocument(@PathVariable int id, @RequestBody int docId) {
        try {
            // Apelează metoda din service care elimină documentul
            Client updatedClient = clientService.removeDocumentFromClient(id, docId);
            return ResponseEntity.ok(updatedClient);
        } catch (RuntimeException e) {
            // Gestionare eroare dacă clientul sau documentul nu există
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PatchMapping("/{clientId}/default_documents")
    public ResponseEntity<?> setDefaultDocs(@PathVariable int clientId) {
        try {
            Client client = clientService.setDefaultDocs(clientId);
            return ResponseEntity.status(HttpStatus.CREATED).body(client);
        }catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("{clientId}/ownedDocuments")
    public ResponseEntity<List<DocumentType>> getClientDocuments(@PathVariable int clientId) {
        List<DocumentType> documents = clientService.getOwnedDocuments(clientId);
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        try {
            // Obținem lista tuturor clienților folosind serviciul
            List<Client> clients = clientService.getAllClients();

            // Returnăm lista ca răspuns cu statusul OK
            return ResponseEntity.ok(clients);
        } catch (RuntimeException e) {
            // În caz de eroare, returnăm un mesaj corespunzător
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
