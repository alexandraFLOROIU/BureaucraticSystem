// UrbanPlanningOffice.java
import java.util.Arrays;

public class UrbanPlanningOffice extends Office {
    public UrbanPlanningOffice() {
        // Adăugăm ghișee pentru fiecare tip de document
        addCounter(new OfficeCounter("Ghișeul Urbanism 1", Arrays.asList("certificat de urbanism")));
        addCounter(new OfficeCounter("Ghișeul Urbanism 2", Arrays.asList("certificat de urbanism")));
        addCounter(new OfficeCounter("Ghișeul Construcții 1", Arrays.asList("autorizație de construire", "autorizație de demolare")));
        addCounter(new OfficeCounter("Ghișeul Construcții 2", Arrays.asList("autorizație de construire", "autorizație de demolare")));
    }

    @Override
    public Document requestDocument(Person person, String documentName) {
        manageCoffeeBreaks(); // Gestionăm pauzele de cafea

        Document document = createDocument(documentName);
        for (OfficeCounter counter : counters) {
            if (!counter.isOnBreak() && counter.requestDocument(person, document) != null) {
                person.addDocument(document.getName());
                return document;
            }
        }

        System.out.println("Toate ghișeele sunt în pauză sau nu pot emite documentul solicitat. doc 1");
        return null;
    }

    private Document createDocument(String documentName) {
        switch (documentName) {
            case "certificat de urbanism":
                return new Document("certificat de urbanism", Arrays.asList("carte de identitate"));
            case "autorizație de construire":
                return new Document("autorizație de construire", Arrays.asList("certificat de urbanism", "certificat fiscal"));
            case "autorizație de demolare":
                return new Document("autorizație de demolare", Arrays.asList("certificat de urbanism", "certificat fiscal"));
            default:
                return new Document(documentName, Arrays.asList());
        }
    }
}
