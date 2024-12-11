import java.util.List;
import java.util.ArrayList;
public class Magasin {
    List<Tours> lstToursDispos;

    public Magasin(List<Tours> lstToursDispos) {
        this.lstToursDispos = new ArrayList<>();
    }

    public List<Tours> getLstToursDispos() {
        return lstToursDispos;
    }
    
}
