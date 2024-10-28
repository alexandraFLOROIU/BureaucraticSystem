import java.util.List;

public class Person extends  Thread implements Runnable{
    private final String personName;
    private List<Document> ownedDocuments;
    public Person(String personName, List<Document> documenteDetinute) {
        this.personName = personName;
        this.ownedDocuments = documenteDetinute;
    }

    public String getPersonName() {
        return personName;
    }

    public void run(){
        System.out.println("Hello World"); // de apelat o metoda comuna;
    }

//    public void isCerereActe(String numeBirou, String act) { //to be implemented
//        if(numeBirou == null && act == null) {
//            throw new RuntimeException("Cerea ta nu poate fii solutionata."); //to be modified
//        }
//        if(Objects.equals(numeBirou, "BirouUrbanism")){
//
//        }
//        if(Objects.equals(numeBirou, "BirouTaxe")){
//
//        }
//        if(Objects.equals(numeBirou, "BirouSocial")){
//
//        }
//    }

    public boolean hasDocument(Document document){
        for (Document doc : ownedDocuments) {
            if (doc.getName().equals(document.getName())) {
                System.out.println("S-a gasit -> " + document.getName());
                return true;
            }
        }
        System.out.println("NU s-a gasit -> " + document.getName());
        return false;
    }


    //s-ar putea sa trebuiasca adaugata metoda in obiectul ghiseu
    public void requestDocument(Document requiredDocument, OfficeCounter counter) {
        //Document document = new Document(numeDocument, null);

        if (counter.hasAllRequiredDocuments(requiredDocument,this)) {

            //trebuie adaugat metoda din ghiseu pentru acrodare document solicitat
            System.out.println("Am dat document");
            this.ownedDocuments.add(requiredDocument);
        } else {
            System.out.println("Clientul " + this.getPersonName() + " nu are toate documentele necesare pentru " + requiredDocument.getName());
            //
        }
    }

    public OfficeCounter chooseCounterForRequester(Document searchedDocument, List<Office> offices) {
        return offices.stream()
                .flatMap(office -> office.getOfficeCounters().stream()) // Trecem prin toate ghiseele
                .filter(officeCounter -> officeCounter.getAvailableDocuments().stream() // Verificăm fiecare document
                        .anyMatch(doc -> doc.getName().equals(searchedDocument.getName()))) // Găsim documentul căutat
                .findFirst() // Returnăm primul ghiseu care emite documentul
                .orElseThrow();
    }


}
