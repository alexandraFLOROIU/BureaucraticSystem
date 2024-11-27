package BeaureaticSystems.clients;

import BeaureaticSystems.document.DocumentType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ClientDocument {
    @NotBlank(message = "Name is mandatory")
    private String name;

    private List<DocumentType> ownedDocuments;

    public String getName() {
        return name;
    }

    public List<DocumentType> getOwnedDocuments() {
        return ownedDocuments;
    }
}
