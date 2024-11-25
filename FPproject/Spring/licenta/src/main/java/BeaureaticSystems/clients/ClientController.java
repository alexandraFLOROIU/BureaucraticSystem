package BeaureaticSystems.clients;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    private ClientService clientService;

    @PostMapping("/create-client")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        clientService.createClient(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PostMapping("/start")
    public ResponseEntity<String> startClientProcess(@RequestParam int clientId, @RequestParam int documentId) {
        try {
            clientService.startClientProcess(clientId, documentId);
            return ResponseEntity.ok("Client process started");
        }catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
