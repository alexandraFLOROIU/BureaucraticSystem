import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Creăm un obiect CityHall și adăugăm birourile la acesta
        CityHall cityHall = new CityHall();

        // Creăm o persoană cu documentele inițiale
        Person person = new Person("Ion Popescu");

        // Cerem automat documente de la birouri diferite
        System.out.println("Cerere automată de documente pentru " + person.getName() + ":");

        // Modificăm apelurile pentru a include cityHall ca parametru
        for (Office office : cityHall.getOffices()) {
            office.officeRequestDocument(person, "chitanță de plată a taxelor locale", cityHall);
            office.officeRequestDocument(person, "autorizație de construire", cityHall);
            office.officeRequestDocument(person, "ajutor social", cityHall);
        }

//        cityHall.gestioneaza(person,"certificat fiscal");
        // Afișăm lista de documente obținute
        System.out.println("Documente obținute de " + person.getName() + ":");
        for (String doc : person.getDocuments()) {
            System.out.println("- " + doc);
        }
    }
}
