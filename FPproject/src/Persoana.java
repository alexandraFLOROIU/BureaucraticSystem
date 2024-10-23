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

    public boolean areDocument(String numeDocument){
        for (Document doc : documenteDetinute) {
            if (doc.getNume().equals(numeDocument)) {
                System.out.println("S-a gasit");
                return true;
            }
        }
        System.out.println("nah");
        return false;
    }

    public boolean areToateDocumenteleNecesare(Document documentSolicitat) {
        List<String> documenteNecesare = documentSolicitat.getDocumenteNecesare();
        for (String numeDocNecesar : documenteNecesare) {
            if (!areDocument(numeDocNecesar)) {
                return false;
            }
        }
        return true;
    }
}
