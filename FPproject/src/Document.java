import java.util.List;

public class Document {
    private final List<String> documenteNecesare;
    private String numeDocument;

    public Document(String numeDocument, List<String> documenteNecesareObtinere)
    {
        this.numeDocument = numeDocument;
        this.documenteNecesare = documenteNecesareObtinere;
    }

    public String getNume()
    {
        return numeDocument;
    }

    public List<String> getDocumenteNecesare()
    {
        return this.documenteNecesare;
    }

    public boolean areDependente()
    {
        return documenteNecesare != null;
    }

}
