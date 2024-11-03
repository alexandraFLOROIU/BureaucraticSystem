import java.util.List;

public class DocumentType {
    private int id;
    private List<DocumentType> requiredDocs;

    public String getName() {
        return name;
    }

    private String name;

    public DocumentType(int id, List<DocumentType> requiredDocs, String name) {
        this.id = id;
        this.requiredDocs = requiredDocs;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public List<DocumentType> getRequiredDocs() {
        return requiredDocs;
    }
}