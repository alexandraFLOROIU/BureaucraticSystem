public class UrbanPlanningOffice extends Office {
    private Document planningCertificat;
    private Document buildingPermit;
    private Document demolitionPermit;
    private OfficeCounter ghiseu1CertificatDeUrbanism;
    private OfficeCounter ghiseu2CertificatDeUrbanism;
    private OfficeCounter ghiseu1Autorizatie;
    private OfficeCounter ghiseu2Autorizatie;

    public UrbanPlanningOffice(Document planningCertificat, Document autorizatieDeConstruire, Document autorizatieDeDemolare, OfficeCounter ghiseu1CertificatDeUrbanism, OfficeCounter ghiseu2CertificatDeUrbanism, OfficeCounter ghiseu1Autorizatie, OfficeCounter ghiseu2Autorizatie) {
        this.planningCertificat = planningCertificat;
        this.buildingPermit = autorizatieDeConstruire;
        this.demolitionPermit = autorizatieDeDemolare;
        this.ghiseu1CertificatDeUrbanism = ghiseu1CertificatDeUrbanism;
        this.ghiseu2CertificatDeUrbanism = ghiseu2CertificatDeUrbanism;
        this.ghiseu1Autorizatie = ghiseu1Autorizatie;
        this.ghiseu2Autorizatie = ghiseu2Autorizatie;
    }
}
