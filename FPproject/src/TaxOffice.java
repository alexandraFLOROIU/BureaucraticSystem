// TaxOffice.java
import java.util.Arrays;

public class TaxOffice extends Office {
    public TaxOffice() {
        // Adăugăm două ghișee specifice biroului de taxe, fiecare având documente proprii pe care le poate emite
        addCounter(new OfficeCounter("Ghișeul Taxe 1", Arrays.asList("certificat fiscal", "chitanță de plată a taxelor locale")));
        addCounter(new OfficeCounter("Ghișeul Taxe 2", Arrays.asList("decizie de impunere", "adeverință de venit")));
    }

    @Override
    public Document officeRequestDocument(Person person, String documentName, CityHall cityHall) {
         // Gestionăm pauzele de cafea

        Document document = createDocument(documentName);
        for (OfficeCounter counter : counters) {

            manageCoffeeBreaks();
            if (!counter.isOnBreak() && counter.requestDocumentWithRedirect(person, document, this, cityHall) != null) {
                person.addDocument(document.getName());
                return document;
            }
        }

        System.out.println("Toate ghișeele disponibile sunt în pauză sau nu pot emite documentul solicitat.");
        return null;
    }

    @Override
    protected Document createDocument(String documentName) {
        return switch (documentName) {
            case "certificat fiscal" -> new Document("certificat fiscal", Arrays.asList("carte de identitate", "extras de cont"));
            case "decizie de impunere" -> new Document("decizie de impunere", Arrays.asList("carte de identitate", "documente de proprietate"));
            case "adeverință de venit" -> new Document("adeverință de venit", Arrays.asList("carte de identitate", "declarație de venit", "chitanță de plată a taxelor locale"));
            case "chitanță de plată a taxelor locale" -> new Document("chitanță de plată a taxelor locale", Arrays.asList("carte de identitate", "documente de proprietate", "decizie de impunere" ));
            default -> new Document(documentName, Arrays.asList());
        };
    }
}
