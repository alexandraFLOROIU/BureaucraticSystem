// OfficeCounter.java
import java.util.*;
public class OfficeCounter {
    private final String name;
    private final List<String> allowedDocuments; // Documentele pe care acest ghișeu le poate emite
    private boolean onBreak = false;

    public OfficeCounter(String name, List<String> allowedDocuments) {
        this.name = name;
        this.allowedDocuments = allowedDocuments;
    }

    public boolean isOnBreak() {
        return onBreak;
    }

    public void toggleBreak() {
        onBreak = !onBreak;
    }

    public Document requestDocument(Person person, Document document) {
        if (onBreak) {
            System.out.println("Ghișeul " + name + " este în pauză.");
            return null;
        }

        if (!allowedDocuments.contains(document.getName())) {  // Verificăm dacă documentul este în lista de documente permise
            System.out.println("Ghișeul " + name + " nu poate emite documentul " + document.getName());
            return null;
        }

        // Verificăm documentele intermediare necesare
        for (String requiredDoc : document.getRequiredDocuments()) {
            if (!person.hasDocument(requiredDoc)) {
                System.out.println("Pentru a obține " + document.getName() + ", este necesar " + requiredDoc);
                return null;
            }
        }

        System.out.println("Documentul " + document.getName() + " a fost eliberat de ghișeul " + name);
        return document;
    }

    @Override
    public String toString() {
        return name;
    }
}
