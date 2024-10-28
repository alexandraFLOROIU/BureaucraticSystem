// TaxOffice.java
import java.util.Arrays;

public class TaxOffice extends Office {
    public TaxOffice() {
        addCounter(new OfficeCounter("Ghișeul Taxe 1", Arrays.asList("certificat fiscal", "chitanță de plată a taxelor locale")));
        addCounter(new OfficeCounter("Ghișeul Taxe 2", Arrays.asList("decizie de impunere", "adeverință de venit")));
    }

    @Override
    public Document requestDocument(Person person, String documentName) {
        manageCoffeeBreaks();
        Document document = createDocument(documentName);

        for (OfficeCounter counter : counters) {
            if (!counter.isOnBreak() && counter.requestDocument(person, document) != null) {
                person.addDocument(document.getName());
                return document;
            }
        }

        System.out.println("Toate ghișeele disponibile sunt în pauză sau nu pot emite documentul solicitat.");
        return null;
    }

    private Document createDocument(String documentName) {
        switch (documentName) {
            case "certificat fiscal": return new Document("certificat fiscal", Arrays.asList("carte de identitate"));
            case "decizie de impunere": return new Document("decizie de impunere", Arrays.asList("carte de identitate", "documente de proprietate"));
            case "adeverință de venit": return new Document("adeverință de venit", Arrays.asList("carte de identitate", "declarație de venit", "chitanță de plată a taxelor"));
            default: return new Document(documentName, Arrays.asList());
        }
    }
}
