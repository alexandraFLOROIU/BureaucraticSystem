package BeaureaticSystems.clients;

import BeaureaticSystems.document.DocumentType;
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

    @PostMapping()
    public ResponseEntity<Client> createClient(@RequestBody ClientDocument clientDocument) {
        String name = clientDocument.getName();
        List<DocumentType> ownedDocuments = clientDocument.getOwnedDocuments();
        Client client = clientService.createClient(name, ownedDocuments);
        return ResponseEntity.ok(client);
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
}
