import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creăm birourile necesare
        TaxOffice taxOffice = new TaxOffice();
        UrbanPlanningOffice urbanPlanningOffice = new UrbanPlanningOffice();
        SocialOffice socialOffice = new SocialOffice();

        // Creăm o persoană cu documentele inițiale
        Person person = new Person("Ion Popescu");
        //person.addDocument("certificat de urbanism");
        //person.addDocument("certificat fiscal");

        // Cerem automat documente de la birouri diferite
        System.out.println("Cerere automată de documente pentru " + person.getName() + ":");

        // Simulăm cererea unui "certificat fiscal" de la biroul de taxe
        Document doc1 = urbanPlanningOffice.requestDocument(person, "certificat fiscal");
        if (doc1 != null) {
            person.addDocument(doc1.getName());
        }else{
            System.out.println("doc 1 not found");
        }

        // Simulăm cererea unei "autorizații de construire" de la biroul de urbanism
        Document doc2 = urbanPlanningOffice.requestDocument(person, "autorizație de construire");
        if (doc2 != null) {
            person.addDocument(doc2.getName());
        }

        // Simulăm cererea unui "ajutor social" de la biroul de asistență socială
        Document doc3 = socialOffice.requestDocument(person, "ajutor social");
        if (doc3 != null) {
            person.addDocument(doc3.getName());
        }

        // Afișăm lista de documente obținute de persoană
        System.out.println("\nDocumente obținute de " + person.getName() + ":");
        for (String doc : person.getDocuments()) {
            System.out.println("- " + doc);
        }
    }
}
