import java.util.List;

class DocumentType {
    private int id;
    private List<DocumentType> requiredDocs;

    public DocumentType(int id, List<DocumentType> requiredDocs) {
        this.id = id;
        this.requiredDocs = requiredDocs;
    }

    public int getId() {
        return id;
    }

    public List<DocumentType> getRequiredDocs() {
        return requiredDocs;
    }
}