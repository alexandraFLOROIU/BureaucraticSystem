import java.util.List;

public class OfficeCounter {
    private final List<Document> availableDocuments;

    public OfficeCounter(List<Document> availableDocuments) {
        this.availableDocuments = availableDocuments;
    }

    public boolean hasAllRequiredDocuments(Document requiredDocument, Person personAtCounter) {
        List<Document> requiredDocuments = requiredDocument.getRequiredDocuments();
        for (Document requiredDocumentName : requiredDocuments) {
            if (!personAtCounter.hasDocument(requiredDocumentName)) {
                return false;
            }
        }
        return true;
    }

    public List<Document> getAvailableDocuments() {
        return availableDocuments;
    }

    //
}
