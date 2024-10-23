import java.util.Objects;

public class Persoana extends  Thread{
    public String nume;
    public int age;
    public String stareCivila;
    public boolean cerereActe;
    public boolean carteDeIdentitate;
    public boolean certificatNastere;
    public boolean certificaCasatorie;
    public boolean extrasBancar;
    public boolean documenteProprietate;
    public boolean adeverintaDeVenit;
    public boolean adeverintaMedicala;
    public boolean declaratieDeVenit;
     //acte pentru Birou de Urbanism și Amenajare teritorială

    public boolean certificatDeUrbanism;
    public boolean autorizatieDeConstruire;
    public boolean autorizatieDeDemolare;
    //Vor exista 2 ghișee care pot emite certificat de urbanism
    // și două ghișee care pot emite atât autorizație de construire,
    // cât și autorizație de demolare.

    //acte pentru Birou de Taxe și impozite

    public boolean certificatFiscal;
    public boolean chitantaDePlataATaxelorLocale;
    public boolean decizieDeImpunere;
    //Vor exista 2 ghișee care pot emite certificat fiscal, chitanță de plată a taxelor locale
    // și două ghișee care pot emite decizie de impunere, adeverință de venit.


    //acte pentru Birou de Asistență Socială

    public boolean depunereAjutorSocial;
    public boolean ticheteSociale;

    //Vor exista 2 ghișee care pot emite ambele tipuri de documente.

    public Persoana(String nume, int age,String stareCivila) {
        this.nume = nume;
        this.age = age;
        this.stareCivila = "";
    }

    public String getNume() {
        return nume;
    }

    public int getAge() {
        return age;
    }

    public String getStareCivila() {
        return stareCivila;
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
}
