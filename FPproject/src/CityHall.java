// CityHall.java
import java.util.ArrayList;
import java.util.List;

public class CityHall {
    private List<Office> offices = new ArrayList<>();

    public CityHall() {
        // Creăm birourile necesare
        TaxOffice taxOffice = new TaxOffice();
        UrbanPlanningOffice urbanPlanningOffice = new UrbanPlanningOffice();
        SocialOffice socialOffice = new SocialOffice();

        // Adăugăm birourile în lista de birouri
        offices.add(taxOffice);
        offices.add(urbanPlanningOffice);
        offices.add(socialOffice);
    }

    //    public void gestioneaza(Person person, String document){
//        for (Office office : offices) {
//            office.requestDocument(person, document,this);
//        }
//    }
    public void addOffice(Office office) {
        offices.add(office);
    }

    public List<Office> getOffices() {
        return offices;
    }

    public Document requestDocument(Person person, String documentName) {
        for (Office office : offices) {
            Document document = office.officeRequestDocument(person, documentName, this); // Trebuie să trimitem referința CityHall pentru apeluri recursive
            if (document != null) {
                return document;
            }
            System.out.println("Niciun birou nu poate emite documentul " + documentName);
        }
        return null;
    }
}