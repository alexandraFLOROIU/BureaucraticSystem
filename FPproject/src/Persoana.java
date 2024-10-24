import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Persoana extends  Thread implements Runnable{
    private final String numePersoana;
    private List<Document> documenteDetinute ;
    public Persoana(String numePersoana, List<Document> documenteDetinute) {
        this.numePersoana = numePersoana;
        this.documenteDetinute = documenteDetinute;
    }

    public String getNumePersoana() {
        return numePersoana;
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

    public boolean areDocument(Document document){
        for (Document doc : documenteDetinute) {
            if (doc.getNume().equals(document.getNume())) {
                System.out.println("S-a gasit -> " + document.getNume());
                return true;
            }
        }
        System.out.println("NU s-a gasit -> " + document.getNume());
        return false;
    }


    //s-ar putea sa trebuiasca adaugata metoda in obiectul ghiseu
    public void solicitaDocument(Document documentSolicitat, Ghiseu ghiseu) {
        //Document document = new Document(numeDocument, null);

        if (ghiseu.areToateDocumenteleNecesare(documentSolicitat,this)) {

            //trebuie adaugat metoda din ghiseu pentru acrodare document solicitat
            System.out.println("Am dat document");
            this.documenteDetinute.add(documentSolicitat);
        } else {
            System.out.println("Clientul " + this.getNumePersoana() + " nu are toate documentele necesare pentru " + documentSolicitat.getNume());
            //
        }
    }

    public Ghiseu alegeGhiseuPentruSolicitant(Document documentCautat, List<Birou> birouri) {
        return birouri.stream()
                .flatMap(birou -> birou.getGhiseu().stream()) // Trecem prin toate ghiseele
                .filter(ghiseu -> ghiseu.getDocumenteDisponibile().stream() // Verificăm fiecare document
                        .anyMatch(doc -> doc.getNume().equals(documentCautat.getNume()))) // Găsim documentul căutat
                .findFirst() // Returnăm primul ghiseu care emite documentul
                .orElseThrow();
    }


}
