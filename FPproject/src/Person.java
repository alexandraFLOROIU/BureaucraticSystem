// Person.java
import java.util.HashSet;
import java.util.Set;
import java.util.*;

public class Person {
    private String name;
    private List<String> documents = new ArrayList<>(); // Set de documente deținute

    public Person(String name) {
        this.name = name;
        initializeDefaultDocuments();
    }

    private void initializeDefaultDocuments() {
        documents.add("carte de identitate");
        documents.add("certificat de naștere");
        documents.add("certificat de căsătorie");
        documents.add("dovadă domiciliu");
        documents.add("adeverință medicală");
        documents.add("documente de proprietate");
        documents.add("extras de cont");
        documents.add("declarație de venit");
    }

    public boolean hasDocument(String documentName) {
        return documents.contains(documentName);
    }

    public void addDocument(String documentName) {
        documents.add(documentName);
    }
    public String getName() {
        return name;
    }

    public List<String> getDocuments() {
        return documents;
    }
}
