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
                System.out.println("Ghișeul " + counter + " este " + (counter.isOnBreak() ? "în pauză" : "disponibil"));
            }
        }
    }

    public abstract Document requestDocument(Person person, String documentName);
}
