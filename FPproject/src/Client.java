import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Client {
    private DocumentType requiredType;
    private Map<DocumentType, Document> documents;

    public Client(DocumentType requiredType) {
        this.requiredType = requiredType;
        this.documents = new HashMap<>();
    }

    public DocumentType getRequiredType() {
        return requiredType;
    }

    public Map<DocumentType, Document> getDocuments() {
        return documents;
    }

    public DocumentType getNextRequiredType() {
        if (this.documents.containsKey(requiredType)) {
            return null;
        }
        Queue<DocumentType> types = new LinkedList<>();
        types.add(requiredType);
        while (!types.isEmpty()) {
            DocumentType currentType = types.poll();
            boolean hasAllRequiredDocs = true;
            for (DocumentType requiredDoc : currentType.getRequiredDocs()) {
                if (!this.documents.containsKey(requiredDoc)) {
                    hasAllRequiredDocs = false;
                    types.add(requiredDoc);
                }
            }
            if (hasAllRequiredDocs) {
                return currentType;
            }
        }
        return null;
    }
}