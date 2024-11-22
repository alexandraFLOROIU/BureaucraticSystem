package BeaureaticSystems.clients;

import BeaureaticSystems.document.DocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
public class Client {
    @Id
    private Long id;

    private String name;

    @OneToMany
    private List<DocumentType> ownedDocuments;

}
