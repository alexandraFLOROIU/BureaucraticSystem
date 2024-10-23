import java.util.List;

public class Document {
    private final List<Document> documenteNecesare;
    private String numeDocument;

    public Document(String numeDocument, List<Document> documenteNecesareObtinere)
    {
        this.numeDocument = numeDocument;
        this.documenteNecesare = documenteNecesareObtinere;
    }

    public String getNume()
    {
        return numeDocument;
    }

    public List<Document> getDocumenteNecesare()
    {
        return this.documenteNecesare;
    }

    public boolean areDependente()
    {
        return documenteNecesare != null;
    }

}
