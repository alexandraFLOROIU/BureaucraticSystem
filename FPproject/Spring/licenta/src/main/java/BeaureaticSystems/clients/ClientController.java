package BeaureaticSystems.clients;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    private ClientService clientService;

    @PostMapping("/create-client")
    public ResponseEntity createClient(@RequestBody Client client) {
        clientService.createClient(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }
}
