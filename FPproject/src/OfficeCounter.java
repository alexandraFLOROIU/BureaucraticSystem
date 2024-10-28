import java.util.List;

public class OfficeCounter {
    private final List<Document> availableDocuments;

    public OfficeCounter(List<Document> availableDocuments) {
        this.availableDocuments = availableDocuments;
    }

    public boolean checkRequiredDocuments(Person persoanaLaRand, Document requiredDocument){
        if(this.hasAllRequiredDocuments(requiredDocument, persoanaLaRand)){
            System.out.println("S-a adaugat document:" + requiredDocument + "\n");
            return true;
        }
        else{
            System.out.println("Nu se poate aproba cererea.\n");
            return false;
        }
    }

    public boolean hasAllRequiredDocuments(Document requiredDocument, Person persoanaLaRand) {
        List<Document> requiredDocuments = requiredDocument.getRequiredDocuments();
        for (Document requiredDocumentName : requiredDocuments) {
            if (!persoanaLaRand.hasDocument(requiredDocumentName)) {
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
