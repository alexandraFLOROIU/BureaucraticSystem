package BeaureaticSystems.office;

import BeaureaticSystems.clients.Client;
import BeaureaticSystems.clients.ClientRepository;
import BeaureaticSystems.counter.Counter;
import BeaureaticSystems.counter.CounterRepository;
import BeaureaticSystems.document.DocumentType;
import BeaureaticSystems.document.DocumentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class OfficeService {
    private OfficeRepository officeRepository;
    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CounterRepository counterRepository;

    private final Object counterLock = new Object();

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
    @Async
    public CompletableFuture<Client> requestDocument(int clientId, int documentId) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found: " + clientId));
        DocumentType targetDocument = documentTypeRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("DocumentType not found: " + documentId));
        for(DocumentType d : client.getOwnedDocuments()){
            if(d.getId() == targetDocument.getId()){
                throw new RuntimeException("Client already owns document " + d.getId());
            }
        }

        return processDocumentRequest(client, targetDocument);
    }

    private boolean containsDocument(List<DocumentType> documentTypes, DocumentType dependency) {
        for (DocumentType doc : documentTypes) {
            if (doc.getId() == dependency.getId()) { // Compară doar pe baza ID-ului
                return true;
            }
        }
        return false;
    }

    private List<DocumentType> findRequiredDocuments(Client client, DocumentType targetDocumentType) {
        Set<DocumentType> requiredDocuments = new HashSet<>();
        Queue<DocumentType> documentsToCheck = new LinkedList<>();
        documentsToCheck.add(targetDocumentType);
        requiredDocuments.add(targetDocumentType);

        while (!documentsToCheck.isEmpty()) {
            DocumentType currentType = documentsToCheck.poll();

            // Get all required documents for current document type
            List<DocumentType> directRequiredDocs = currentType.getRequiredDocs();

            for (DocumentType requiredDoc : directRequiredDocs) {
                // Check if client already has this document
                boolean hasDocument = containsDocument(client.getOwnedDocuments(), requiredDoc);

                if (!hasDocument) {
                    requiredDocuments.add(requiredDoc);
                    documentsToCheck.add(requiredDoc);
                }
            }
        }

        List<DocumentType> sortedList = new ArrayList<>(requiredDocuments);
        Collections.reverse(sortedList);
        return sortedList;
    }

    @Async
    public CompletableFuture<Client> processDocumentRequest(
            Client client,
            DocumentType targetDocumentType
    ) {
        return CompletableFuture.supplyAsync(() -> {

            class ClientWrapper {
                private Client client;

                public ClientWrapper(Client client) {
                    this.client = client;
                }

                public Client getClient() {
                    return client;
                }

                public void setClient(Client client) {
                    this.client = client;
                }
            }

            ClientWrapper clientWrapper = new ClientWrapper(client);

            System.out.println("Client " + client.getId() + " find all required documents recursively");
            // Find all required documents recursively
            List<DocumentType> requiredDocuments = findRequiredDocuments(clientWrapper.getClient(), targetDocumentType);

            // Process each required document
            for (DocumentType documentType : requiredDocuments) {
                System.out.println("Document " + documentType.getId() + " for client " + client.getId() + " will be processed");
                clientWrapper.setClient(processIndividualDocument(clientWrapper.getClient(), documentType));
            }

            return clientWrapper.getClient();
        }, executorService);
    }

    private Client processIndividualDocument(Client client, DocumentType documentType) {
        // Find an appropriate office and available counter
        Office office = findOfficeForDocumentType(documentType);
        System.out.println("Office with id " + office.getId() + " was found for client with id " + client.getId());

        Counter availableCounter = findAvailableCounter(office);
        System.out.println("Counter with id " + availableCounter.getId() + " was found for client with id " + client.getId());
        try {
            // Mark counter as busy
            availableCounter.setStatus(Counter.Status.BUSY);
            counterRepository.save(availableCounter);
            System.out.println("Counter with id " + availableCounter.getId() + " became busy because document for client with id " + client.getId() + " is being processed");

            // Simulate processing (replace with actual logic)
            Thread.sleep(new Random().nextInt(100, 500));

            client.getOwnedDocuments().add(documentType);
            client.setTargetDocument(documentType);
            clientRepository.save(client);
            System.out.println("Client " + client.getId() + " obtained document with id " + documentType.getId());

        } catch (InterruptedException e) {
            throw new RuntimeException("Document processing interrupted", e);
        } finally {
            // Mark counter as available
            availableCounter.setStatus(Counter.Status.AVAILABLE);
            counterRepository.save(availableCounter);
            System.out.println("Counter with id " + availableCounter.getId() + " became available again");
        }
        return client;
    }

    private Office findOfficeForDocumentType(DocumentType documentType) {

        Iterable<Office> offices = getAllOffices();
        for (Office o : offices) {
            //System.out.println("parcurg birouri");

            for (DocumentType doc : o.getCompatibleDocumentTypes()) {
                if (doc.getId() == documentType.getId()) {
                    return o;
                }
            }

        }
        return null;
    }

    private Counter waitForAvailableCounter(Office office) {
        synchronized (counterLock) {
            while (true) {
                System.out.println("For office with id " + office.getId() + " no counter is currently available");
                // Try to find an available counter in the office
                Optional<Counter> availableCounter = office.getCounters().stream()
                        .filter(counter -> counter.getStatus() == Counter.Status.AVAILABLE)
                        .findFirst();

                // If an available counter is found, return it
                if (availableCounter.isPresent()) {
                    return availableCounter.get();
                }

                try {
                    // Wait for a notification about counter availability
                    // This will release the lock and suspend the thread
                    System.out.println("Client waits for a counter within office with id " + office.getId());
                    counterLock.wait();
                } catch (InterruptedException e) {
                    // Restore the interrupted status
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Waiting for counter was interrupted", e);
                }
            }
        }
    }
    
    // Modify the randomlyPutCounterOnBreak method to notify waiting threads
    private void randomlyPutCounterOnBreak(Counter counter) {
        synchronized (counterLock) {
            boolean entered = false;
            if (counter.getStatus() == Counter.Status.AVAILABLE && new Random().nextInt(5) == 0) {
                counter.setStatus(Counter.Status.BREAK);
                counterRepository.save(counter);
                entered = true;
                System.out.println("Counter with id " + counter.getId() + " became busy");
            }


            // Automatically end break if time is up
            if (counter.getStatus() == Counter.Status.BREAK && !entered) {

                counter.setStatus(Counter.Status.AVAILABLE);
                counterRepository.save(counter);
                System.out.println("Counter with id " + counter.getId() + " became available again from break");
                // Notify waiting threads that a counter is now available
                counterLock.notifyAll();
            }
        }
    }

    // Modify the findAvailableCounter method to handle potential waiting
    private Counter findAvailableCounter(Office office) {
        synchronized (counterLock) {
            Optional<Counter> availableCounter = office.getCounters().stream()
                    .filter(counter -> counter.getStatus() == Counter.Status.AVAILABLE)
                    .findFirst();

            // If no counter is available immediately, wait for one
            return availableCounter.orElseGet(() -> waitForAvailableCounter(office));
        }
    }
    
    // Scheduled method to periodically check and update counter statuses
    @Scheduled(fixedRate = 60000) // Every minute
    public void simulateCounterBreaks() {
        synchronized (counterLock) {
            Iterable<Counter> counters = counterRepository.findAll();
            counters.forEach(this::randomlyPutCounterOnBreak);
        }
    }
}