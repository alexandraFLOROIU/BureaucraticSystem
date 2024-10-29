// Document.java
import java.util.List;

public class Document {
    private String name;
    private List<String> requiredDocuments; // Lista documentelor intermediare necesare

    public Document(String name, List<String> requiredDocuments) {
        this.name = name;
        this.requiredDocuments = requiredDocuments;
    }

    public String getName() {
        return name;
    }

    public List<String> getRequiredDocuments() {
        return requiredDocuments;
    }
}
