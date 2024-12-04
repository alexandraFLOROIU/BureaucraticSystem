
package BeaureaticSystems.clients;

import BeaureaticSystems.document.DocumentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Client {
    // Getters and Setters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "client_owned_documents", // Numele tabelei intermediare
            joinColumns = @JoinColumn(name = "client_id"), // Coloana pentru Client
            inverseJoinColumns = @JoinColumn(name = "document_id") // Coloana pentru DocumentType
    )
    @Getter
    private List<DocumentType> ownedDocuments = new ArrayList<>();

    @Transient
    private DocumentType targetDocument;

  // @Autowired
  // private ClientService clientService;

    public Client(String name) {
        this.name = name;
    }

    public void initialize(DocumentType targetDocument) {
        this.targetDocument = targetDocument;
    }

    public void setOwnedDocuments(List<DocumentType> ownedDocuments) {
        this.ownedDocuments = ownedDocuments;
    }

    public void setTargetDocument(DocumentType targetDocument)
    {
        this.targetDocument = targetDocument;
    }

    public List<DocumentType> getOwnedDocuments() {
        return ownedDocuments;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    @Override
//    public void run() {
//        System.out.println("Client " + id + " started process to obtain: " + targetDocument.getName());
//        processDocument(targetDocument);
//        System.out.println("Client " + id + " successfully obtained: " + targetDocument.getName());
//      //  clientService.addDocumentToClient(this.id, targetDocument.getId()); // Actualizare stocare în baza de date
//    }



}
