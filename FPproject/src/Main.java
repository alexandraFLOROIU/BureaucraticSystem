

public class Main {
    public static void main(String[] args) {

        // Creăm un obiect CityHall și adăugăm birourile la acesta
        CityHall cityHall = new CityHall();

        // Creăm o persoană cu documentele inițiale
        Person person = new Person("Ion Popescu");

        // Cerem automat documente de la birouri diferite
        System.out.println("Cerere automată de documente pentru " + person.getName() + ":");

        cityHall.gestioneaza(person,"certificat fiscal");
        cityHall.gestioneaza(person,"chitanță de plată a taxelor locale");
        cityHall.gestioneaza(person, "autorizație de construire");
        cityHall.gestioneaza(person,"ajutor social");

        // Afișăm lista de documente obținute
        System.out.println("Documente obținute de " + person.getName() + ":");
        for (String doc : person.getDocuments()) {
            System.out.println("- " + doc);
        }
    }
}
