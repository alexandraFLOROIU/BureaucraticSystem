package BeaureaticSystems.counter;

import BeaureaticSystems.document.DocumentType;
import BeaureaticSystems.office.Office;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@NoArgsConstructor
public class Counter {
    @Id
    private Long id;

    @ManyToOne
    private DocumentType request;

    @ManyToOne
    private Office office;

}
