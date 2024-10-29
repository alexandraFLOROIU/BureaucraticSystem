import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creăm birourile necesare
        TaxOffice taxOffice = new TaxOffice();
        UrbanPlanningOffice urbanPlanningOffice = new UrbanPlanningOffice();
        SocialOffice socialOffice = new SocialOffice();


        // Creăm o persoană cu documentele inițiale
        Person person = new Person("Ion Popescu");

        // Cerem automat documente de la birouri diferite
        System.out.println("Cerere automată de documente pentru " + person.getName() + ":");
        taxOffice.requestDocument(person, "certificat fiscal");
        urbanPlanningOffice.requestDocument(person, "autorizație de construire");
        socialOffice.requestDocument(person, "ajutor social");
        for (String doc : person.getDocuments()) {
            System.out.println("- " + doc);
        }
    }

}
