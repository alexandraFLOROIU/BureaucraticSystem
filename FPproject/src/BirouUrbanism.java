import java.util.ArrayList;

public class BirouUrbanism extends Birou{
    private Document certificatDeUrbanism;
    private Document autorizatieDeConstruire;
    private Document autorizatieDeDemolare;
    private Ghiseu ghiseu1CertificatDeUrbanism;
    private Ghiseu ghiseu2CertificatDeUrbanism;
    private Ghiseu ghiseu1Autorizatie;
    private Ghiseu ghiseu2Autorizatie;

    public BirouUrbanism(Document certificatDeUrbanism, Document autorizatieDeConstruire, Document autorizatieDeDemolare, Ghiseu ghiseu1CertificatDeUrbanism, Ghiseu ghiseu2CertificatDeUrbanism, Ghiseu ghiseu1Autorizatie, Ghiseu ghiseu2Autorizatie) {
        this.certificatDeUrbanism = certificatDeUrbanism;
        this.autorizatieDeConstruire = autorizatieDeConstruire;
        this.autorizatieDeDemolare = autorizatieDeDemolare;
        this.ghiseu1CertificatDeUrbanism = ghiseu1CertificatDeUrbanism;
        this.ghiseu2CertificatDeUrbanism = ghiseu2CertificatDeUrbanism;
        this.ghiseu1Autorizatie = ghiseu1Autorizatie;
        this.ghiseu2Autorizatie = ghiseu2Autorizatie;
    }
}
