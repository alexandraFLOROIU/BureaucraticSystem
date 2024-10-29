// SocialOffice.java
import java.util.Arrays;

public class SocialOffice extends Office {
    public SocialOffice() {
        // Adăugăm 2 ghișee care pot emite ambele tipuri de documente specifice biroului de Asistență Socială
        addCounter(new OfficeCounter("Ghișeul Social 1", Arrays.asList("ajutor social", "tichete sociale", "cerere tip")));
        addCounter(new OfficeCounter("Ghișeul Social 2", Arrays.asList("ajutor social", "tichete sociale", "cerere tip")));
    }

    @Override
    public Document officeRequestDocument(Person person, String documentName, CityHall cityHall) {
     //   manageCoffeeBreaks(); // Gestionăm pauzele de cafea

        Document document = createDocument(documentName);
        for (OfficeCounter counter : counters) {
            Document issuedDoc = counter.requestDocumentWithRedirect(person, document, this, cityHall);
            if (issuedDoc != null) {
                person.addDocument(document.getName());
                return issuedDoc;
            }
        }

        System.out.println("Toate ghișeele sunt în pauză sau nu pot emite documentul solicitat.");
        return null;
    }

    @Override
    protected Document createDocument(String documentName) {
        return switch (documentName) {
            case "ajutor social" ->
                    new Document("ajutor social", Arrays.asList("cerere tip", "carte de identitate", "certificat de naștere",
                            "certificat de căsătorie", "adeverință de venit",
                            "dovadă domiciliu", "certificat fiscal", "adeverință medicală"));
            case "tichete sociale" ->
                    new Document("tichete sociale", Arrays.asList("cerere tip", "carte de identitate",
                            "adeverință de venit", "certificat fiscal"));
            case "cerere tip" ->
                    new Document("cerere tip", Arrays.asList("carte de identitate"));
            default ->
                    new Document(documentName, Arrays.asList());
        };
    }
}
