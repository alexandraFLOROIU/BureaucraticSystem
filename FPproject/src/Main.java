import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Document adeverinta = new Document("adeverinta", null);
        List<Document> documenteAna = new ArrayList<Document>();
        documenteAna.add(adeverinta);

        Document buletin = new Document("carte de identitate", null);
        Document certificatDeNastere = new Document("certificat de nastere", null);
        Document certificatDeCasatorie = new Document("Certificat de casatorie", null);
        Document dovadaDomiciliu = new Document("Dovada Domiciliu",null);
        Document adeverintaMedicala = new Document("adeverinta medicala",null);
        Document documenteDeProprietate = new Document("documente de proprietate",null);
        Document extrasDeCont = new Document("extras de cont",null);
        Document declaratieDeVenit = new Document("declaratie de venit",null);

        List<Document> l1 = new ArrayList<Document>();
        l1.add(buletin);
        l1.add(extrasDeCont);
        Document certificatFiscal = new Document("certificat de urbanism",l1);

        Persoana persoana1 = new Persoana("Ana", documenteAna);
        Persoana persoana2 = new Persoana("Ionut", null);
        Persoana persoana3 = new Persoana("Bob", null);

        persoana1.start();
        persoana1.areDocument(adeverinta);
        persoana2.start();
        persoana3.start();
    }
}