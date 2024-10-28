import java.util.List;

public class Document {
    private final List<Document> requiredDocuments;
    private final String documentName;

    public Document(String documentName, List<Document> requiredDocumentsForObtaining)
    {
        this.documentName = documentName;
        this.requiredDocuments = requiredDocumentsForObtaining;
    }

    public String getName()
    {
        return documentName;
    }

    public List<Document> getRequiredDocuments()
    {
        return this.requiredDocuments;
    }

    public boolean hasDependencies()
    {
        return requiredDocuments != null;
    }

    public String getDocumentName(){
        return documentName;
    }

}
