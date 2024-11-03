import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

class Office {
    int id;
    private List<DocumentType> compatibleDocumentTypes;
    private int numCounters;
    private BlockingDeque<DocumentRequest> requests;

    public Office(int id, List<DocumentType> compatibleDocumentTypes, int numCounters) {
        this.id = id;
        this.compatibleDocumentTypes = compatibleDocumentTypes;
        this.numCounters = numCounters;
        this.requests = new LinkedBlockingDeque<>();
    }

    public int getId() {
        return id;
    }

    public List<DocumentType> getCompatibleDocumentTypes() {
        return compatibleDocumentTypes;
    }

    public int getNumCounters() {
        return numCounters;
    }

    public BlockingDeque<DocumentRequest> getRequests() {
        return requests;
    }
}