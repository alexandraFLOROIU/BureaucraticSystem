import java.util.ArrayList;
import java.util.List;

//abstracta sau nu?

public class Office {
    public boolean submitRequest;


    private List<OfficeCounter> officeCounters;
    public Office() {
        officeCounters = new ArrayList<>();
    }

    public List<OfficeCounter> getOfficeCounters() {
        return officeCounters;
    }


}
