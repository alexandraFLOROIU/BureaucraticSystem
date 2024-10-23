import java.util.List;
import java.util.Objects;

public class Persoana extends  Thread{
    private String numePersoana;
    private List<Document> documenteDetinute = null;
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

    public void isCerereActe(String numeBirou, String act) { //to be implemented
        if(numeBirou == null && act == null) {
            throw new RuntimeException("Cerea ta nu poate fii solutionata."); //to be modified
        }
        if(Objects.equals(numeBirou, "BirouUrbanism")){

        }
        if(Objects.equals(numeBirou, "BirouTaxe")){

        }
        if(Objects.equals(numeBirou, "BirouSocial")){

        }
    }

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

    public boolean areToateDocumenteleNecesare(Document documentSolicitat) {
        List<Document> documenteNecesare = documentSolicitat.getDocumenteNecesare();
        for (Document numeDocNecesar : documenteNecesare) {
            if (!areDocument(numeDocNecesar)) {
                return false;
            }
        }
        return true;
    }

    //s-ar putea sa trebuiasca adaugata metoda in obiectul ghiseu
    public void solicitaDocument(Document documentSolicitat) {
        //Document document = new Document(numeDocument, null);

        if (this.areToateDocumenteleNecesare(documentSolicitat)) {

            //trebuie adaugat metoda din ghiseu pentru acrodare document solicitat
            System.out.println("Am dat document");
        } else {
            System.out.println("Clientul " + this.getNumePersoana() + " nu are toate documentele necesare pentru " + documentSolicitat.getNume());
        }
    }

    public void adaugaDocumentSolicitat(Document documentSolicitat){
        this.documenteDetinute.add(documentSolicitat);
    }
}
