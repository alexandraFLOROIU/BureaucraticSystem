//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Persoana persoana1 = new Persoana("Ana", 38, "casatorita");
        Persoana persoana2 = new Persoana("Ionut", 22, "singur");
        Persoana persoana3 = new Persoana("Bob", 25, "divortat");

        persoana1.start();
        persoana2.start();
        persoana3.start();
    }
}