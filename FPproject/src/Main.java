import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
    //1
        Document identityCard = new Document("identity Card", null);
    //2
        Document birthCertificate = new Document("birth Certificate", null);
    //3
        Document marriageCertificate = new Document("marriage Certificate", null);
    //4
        Document proofOfResidence = new Document("proof Of Residence",null);
    //5
        Document medicalCertificate = new Document("medical Certificate",null);
    //6
        Document propertyDocuments = new Document("property Documents ",null);
    //7
        Document accountStatement = new Document("account Statement",null);
    //8
        Document incomeDeclaration = new Document("income Declaration",null);
        List<Office> municipalOffices = new ArrayList<>();
    //9
        List<Document> l1 = new ArrayList<>();
        l1.add(identityCard);
        l1.add(accountStatement);
        Document TaxCertificate = new Document("Tax Certificate",l1);

    //10
        List<Document> l2 = new ArrayList<>();
        l2.add(identityCard);
        l2.add(TaxCertificate);
        Document planningCertificat = new Document("planning Certificat",l2);

    //11
        List<Document> l3 = new ArrayList<>();
        l3.add(TaxCertificate);
        l3.add(planningCertificat);
        Document buildingPermit = new Document("building Permit",l3);
    //12
        Document demolitionPermit = new Document("autorizare de demolare",l3);

    //13
        List<Document> l4 = new ArrayList<>();
        l4.add(identityCard);
        l4.add(propertyDocuments);
        Document taxAssessmentDecision = new Document("decizie de impunere",l4);

    //14
        List<Document> l5 = new ArrayList<>();
        l5.add(identityCard);
        l5.add(taxAssessmentDecision);
        l5.add(propertyDocuments);
        Document taxPaymentReceipt = new Document("tax Payment Receipt",l5);

    //15
        List<Document> l6 = new ArrayList<>();
        l6.add(identityCard);
        l6.add(incomeDeclaration);
        l6.add(taxPaymentReceipt);
        Document incomeStatement = new Document("income Statement",l6);

    //16
        Document standardRequest = new Document("standard Request",null);

    //17
        List<Document> l7 = new ArrayList<>();
        l7.add(identityCard);
        l7.add(standardRequest);
        l7.add(birthCertificate);
        l7.add(marriageCertificate);
        l7.add(incomeStatement);
        l7.add(proofOfResidence);
        l7.add(TaxCertificate);
        l7.add(medicalCertificate);
        Document socialAssistance = new Document("social Assistance",l7);

    //18
        List<Document> l8 = new ArrayList<>();
        l8.add(identityCard);
        l8.add(incomeStatement);
        l8.add(standardRequest);
        l8.add(TaxCertificate);
        Document socialVouchers = new Document("social Vouchers",l8);

        //lista test pentru Ana(pers 1)
        List<Document> listaTestAna = new ArrayList<>();
        listaTestAna.add(identityCard);
        listaTestAna.add(birthCertificate);
        listaTestAna.add(medicalCertificate);

        Person persoana1 = new Person("Ana", listaTestAna);
        Person persoana2 = new Person("Ionut", null);
        Person persoana3 = new Person("Bob", null);
        Thread myThread = new Thread(persoana1);
        //test pers 1
        myThread.start();
        persoana1.hasDocument(identityCard);                 //DA
        persoana1.hasDocument(birthCertificate);     //DA
        persoana1.hasDocument(medicalCertificate);      //DA
        persoana1.hasDocument(socialVouchers);          //NU

        //Ghiseu ghiseulMeu = persoana1.alegeGhiseuPentruSolicitant(identityCard, municipalOffices);
        //persoana1.requestDocument(Document requiredDocument,List<Office> offices)

        //test pers 2
        persoana2.start();

        //test pers 3
        persoana3.start();
    }
}