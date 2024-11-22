package BeaureaticSystems.clients;

import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    public void createClient(Client client) {clientRepository.save(client);}
}
