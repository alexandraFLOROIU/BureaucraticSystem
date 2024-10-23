import java.util.List;

public class Ghiseu {
    private List<Document> documenteDisponibile;

    public Ghiseu(List<Document> documenteDisponibile) {
        this.documenteDisponibile = documenteDisponibile;
    }

    public void ProceseazaCerere(Persoana persoanaLaRand, Document documentSolicitat){
        if(persoanaLaRand.areToateDocumenteleNecesare(documentSolicitat)){
            persoanaLaRand.adaugaDocumentSolicitat(documentSolicitat);
            System.out.println("S-a adaugat document:" + documentSolicitat + "\n");
        }
        else{
            System.out.println("Nu se poate aproba cererea.\n");
        }
    }
}
