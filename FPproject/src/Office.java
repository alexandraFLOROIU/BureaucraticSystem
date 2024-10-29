// Office.java
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Office {
    protected List<OfficeCounter> counters = new ArrayList<>();
    private Random random = new Random();

    public void addCounter(OfficeCounter counter) {
        counters.add(counter);
    }

    public void manageCoffeeBreaks() {
        for (OfficeCounter counter : counters) {
            if (random.nextBoolean()) {
                counter.toggleBreak();
                System.out.println(counter + " este " + (counter.isOnBreak() ? "în pauză" : "disponibil"));
            }
        }
    }

    public abstract Document officeRequestDocument(Person person, String documentName, CityHall cityHall); // Adăugăm CityHall pentru recursivitate

    public Document obtainIntermediateDocument(Person person, String documentName, CityHall cityHall) {
        // Încearcă să obțină documentul intermediar din biroul curent
        for (OfficeCounter counter : counters) {
            if (!counter.isOnBreak()) {
                Document intermediateDoc = createDocument(documentName);
                if (counter.requestDocument(person, intermediateDoc) != null) {
                    person.addDocument(intermediateDoc.getName());
                    return intermediateDoc;
                }
            }
        }

        // Dacă nu găsește documentul în biroul curent, trimite la alte birouri prin CityHall
        return cityHall.requestDocument(person, documentName);
    }

    // Metoda pentru crearea documentului trebuie implementată în subclase
    protected abstract Document createDocument(String documentName);
}
