package BeaureaticSystems.document;

import BeaureaticSystems.clients.Client;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "document_type_dependencies",
            joinColumns = @JoinColumn(name = "document_type_id"),
            inverseJoinColumns = @JoinColumn(name = "required_document_id")
    )
    private List<DocumentType> requiredDocs;

    public String getName() {
        return name;
    }

    private String name;

    public DocumentType(int id, List<DocumentType> requiredDocs, String name) {
        this.id = id;
        this.requiredDocs = requiredDocs;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public List<DocumentType> getRequiredDocs() {
        return requiredDocs;
    }
}
