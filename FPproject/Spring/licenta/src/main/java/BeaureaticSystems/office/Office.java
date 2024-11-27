package BeaureaticSystems.office;

import BeaureaticSystems.counter.Counter;
import BeaureaticSystems.document.DocumentType;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<DocumentType> compatibleDocumentTypes;

    @OneToMany
    private List<Counter> counters;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DocumentType> getCompatibleDocumentTypes() {
        return compatibleDocumentTypes;
    }

    public void setCompatibleDocumentTypes(List<DocumentType> compatibleDocumentTypes) {
        this.compatibleDocumentTypes = compatibleDocumentTypes;
    }

    public List<Counter> getCounters() {
        return counters;
    }

    public void setCounters(List<Counter> counters) {
        this.counters = counters;
    }

}
