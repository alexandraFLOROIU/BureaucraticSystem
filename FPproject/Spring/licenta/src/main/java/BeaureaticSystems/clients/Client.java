
package BeaureaticSystems.clients;

import BeaureaticSystems.document.DocumentType;
import BeaureaticSystems.document.DocumentTypeService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
public class Client implements Runnable {
    // Getters and Setters
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    private String name;

    @ManyToMany
    @JoinTable(
            name = "client_owned_documents", // Numele tabelei intermediare
            joinColumns = @JoinColumn(name = "client_id"), // Coloana pentru Client
            inverseJoinColumns = @JoinColumn(name = "document_id") // Coloana pentru DocumentType
    )
    @Getter
    private List<DocumentType> ownedDocuments = new ArrayList<>();

    @Transient
    private DocumentType targetDocument;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public void initialize(DocumentType targetDocument) {
        this.targetDocument = targetDocument;
    }

    @Override
    public void run() {
        System.out.println("Client " + id + " started process to obtain: " + targetDocument.getName());
        processDocument(targetDocument);
        System.out.println("Client " + id + " successfully obtained: " + targetDocument.getName());
    }

    private void processDocument(DocumentType document) {
        if (ownedDocuments.contains(document)) {
            System.out.println("Client " + id + " already has document: " + document.getName());
            return;
        }

        List<DocumentType> dependencies = targetDocument.getRequiredDocs();
        for (DocumentType dependency : dependencies) {
            if (!ownedDocuments.contains(dependency)) {
                System.out.println("Client " + id + " processing dependency: " + dependency.getName());
                processDocument(dependency);
            }
        }

        // Simulate processing
        try {
            System.out.println("Client " + id + " is processing document: " + document.getName());
            Thread.sleep(1000); // Simulate time to process the document
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ownedDocuments.add(document);
        System.out.println("Client " + id + " obtained document: " + document.getName());
    }
}
