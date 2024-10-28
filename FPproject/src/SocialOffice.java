// SocialOffice.java
import java.util.Arrays;

public class SocialOffice extends Office {
    public SocialOffice() {
        // Adăugăm 2 ghișee care pot emite ambele tipuri de documente specifice biroului de Asistență Socială
        addCounter(new OfficeCounter("Ghișeul Social 1", Arrays.asList("ajutor social", "tichete sociale")));
        addCounter(new OfficeCounter("Ghișeul Social 2", Arrays.asList("ajutor social", "tichete sociale")));
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

        System.out.println("Toate ghișeele sunt în pauză sau nu pot emite documentul solicitat.");
        return null;
    }

    private Document createDocument(String documentName) {
        return switch (documentName) {
            case "ajutor social" ->
                    new Document("ajutor social", Arrays.asList("cerere tip", "carte de identitate", "certificat de naștere",
                            "certificat de căsătorie", "adeverință de venit",
                            "dovadă domiciliu", "certificat fiscal", "adeverință medicală"));
            case "tichete sociale" -> new Document("tichete sociale", Arrays.asList("cerere tip", "carte de identitate",
                    "adeverință de venit", "certificat fiscal"));
            default -> new Document(documentName, Arrays.asList());
        };
    }
}
