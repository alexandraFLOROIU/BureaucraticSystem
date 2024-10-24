import java.util.List;

public class Ghiseu {
    private final List<Document> documenteDisponibile;

    public Ghiseu(List<Document> documenteDisponibile) {
        this.documenteDisponibile = documenteDisponibile;
    }

    public boolean VerificaDocumenteNecesare(Persoana persoanaLaRand, Document documentSolicitat){
        if(this.areToateDocumenteleNecesare(documentSolicitat, persoanaLaRand)){
            System.out.println("S-a adaugat document:" + documentSolicitat + "\n");
            return true;
        }
        else{
            System.out.println("Nu se poate aproba cererea.\n");
            return false;
        }
    }

    public boolean areToateDocumenteleNecesare(Document documentSolicitat, Persoana persoanaLaRand) {
        List<Document> documenteNecesare = documentSolicitat.getDocumenteNecesare();
        for (Document numeDocNecesar : documenteNecesare) {
            if (!persoanaLaRand.areDocument(numeDocNecesar)) {
                return false;
            }
        }
        return true;
    }

    public List<Document> getDocumenteDisponibile() {
        return documenteDisponibile;
    }

    //
}
