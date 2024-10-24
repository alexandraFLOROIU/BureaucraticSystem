import java.util.ArrayList;
import java.util.List;

//abstracta sau nu?

public class Birou {
    public boolean depunCerere;


    private List<Ghiseu> ghiseu;
    public Birou() {
        ghiseu = new ArrayList<>();
    }

    public List<Ghiseu> getGhiseu() {
        return ghiseu;
    }


}
