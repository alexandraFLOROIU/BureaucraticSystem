import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
    //1
        Document buletin = new Document("carte de identitate", null);
    //2
        Document certificatDeNastere = new Document("certificat de nastere", null);
    //3
        Document certificatDeCasatorie = new Document("Certificat de casatorie", null);
    //4
        Document dovadaDomiciliu = new Document("Dovada Domiciliu",null);
    //5
        Document adeverintaMedicala = new Document("adeverinta medicala",null);
    //6
        Document documenteDeProprietate = new Document("documente de proprietate",null);
    //7
        Document extrasDeCont = new Document("extras de cont",null);
    //8
        Document declaratieDeVenit = new Document("declaratie de venit",null);
    List<Birou> birourilePrimariei = new ArrayList<>();
    //9
        List<Document> l1 = new ArrayList<>();
        l1.add(buletin);
        l1.add(extrasDeCont);
        Document certificatFiscal = new Document("certificat Fiscal",l1);

    //10
        List<Document> l2 = new ArrayList<>();
        l2.add(buletin);
        l2.add(certificatFiscal);
        Document certificatDeUrbanism = new Document("certificat de urbanism",l2);

    //11
        List<Document> l3 = new ArrayList<>();
        l3.add(certificatFiscal);
        l3.add(certificatDeUrbanism);
        Document autorizatieDeConstruire = new Document("autorizare de construire",l3);
    //12
        Document autorizatieDeDemolare = new Document("autorizare de demolare",l3);

    //13
        List<Document> l4 = new ArrayList<>();
        l4.add(buletin);
        l4.add(documenteDeProprietate);
        Document decizieDeImpunere = new Document("decizie de impunere",l4);

    //14
        List<Document> l5 = new ArrayList<>();
        l5.add(buletin);
        l5.add(decizieDeImpunere);
        l5.add(documenteDeProprietate);
        Document chitantaDePlataTaxe = new Document("chitanta de plata",l5);

    //15
        List<Document> l6 = new ArrayList<>();
        l6.add(buletin);
        l6.add(declaratieDeVenit);
        l6.add(chitantaDePlataTaxe);
        Document adeverintaDeVenit = new Document("adeverinta de venit",l6);

    //16
        Document cerereTip = new Document("cerere tip",null);

    //17
        List<Document> l7 = new ArrayList<>();
        l7.add(buletin);
        l7.add(cerereTip);
        l7.add(certificatDeNastere);
        l7.add(certificatDeCasatorie);
        l7.add(adeverintaDeVenit);
        l7.add(dovadaDomiciliu);
        l7.add(certificatFiscal);
        l7.add(adeverintaMedicala);
        Document ajutorSocial = new Document("ajutor social",l7);

    //18
        List<Document> l8 = new ArrayList<>();
        l8.add(buletin);
        l8.add(adeverintaDeVenit);
        l8.add(cerereTip);
        l8.add(certificatFiscal);
        Document ticheteSociale = new Document("tichete sociale",l8);

        //lista test pentru Ana(pers 1)
        List<Document> listaTestAna = new ArrayList<>();
        listaTestAna.add(buletin);
        listaTestAna.add(certificatDeNastere);
        listaTestAna.add(adeverintaMedicala);

        Persoana persoana1 = new Persoana("Ana", listaTestAna);
        Persoana persoana2 = new Persoana("Ionut", null);
        Persoana persoana3 = new Persoana("Bob", null);
        Thread myThread = new Thread(persoana1);
        //test pers 1
        myThread.start();
        persoana1.areDocument(buletin);                 //DA
        persoana1.areDocument(certificatDeNastere);     //DA
        persoana1.areDocument(adeverintaMedicala);      //DA
        persoana1.areDocument(ticheteSociale);          //NU

        //Ghiseu ghiseulMeu = persoana1.alegeGhiseuPentruSolicitant(buletin, birourilePrimariei);
        //persoana1.solicitaDocument(buletin, ghiseulMeu);

        //test pers 2
        persoana2.start();

        //test pers 3
        persoana3.start();
    }
}