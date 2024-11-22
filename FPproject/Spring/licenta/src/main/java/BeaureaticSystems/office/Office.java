package BeaureaticSystems.office;

import BeaureaticSystems.counter.Counter;
import BeaureaticSystems.document.DocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
public class Office {
    @Id
    private int id;

    @OneToMany
    private List<DocumentType> compatibleDocumentTypes;

    @OneToMany
    private List<Counter> counters;

    private int numCounters;

    public Office() {
    }

    public Office(int id, List<DocumentType> compatibleDocumentTypes, int numCounters) {
        this.id = id;
        this.compatibleDocumentTypes = compatibleDocumentTypes;
        this.numCounters = numCounters;
    }

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

    public int getNumCounters() {
        return numCounters;
    }

    public void setNumCounters(int numCounters) {
        this.numCounters = numCounters;
    }
}
