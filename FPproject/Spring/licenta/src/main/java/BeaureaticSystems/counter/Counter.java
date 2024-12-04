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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Enum pentru starea ghișeului
    public enum Status {
        BUSY,
        AVAILABLE,
        BREAK
    }

    // Atribut pentru starea curentă a ghișeului
    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
