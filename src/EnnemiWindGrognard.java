import java.util.List;

/**
 * Un Wind Grognard vise la Tour qui se trouve à sa portée ayant le moins de PV.
 */
public class EnnemiWindGrognard extends Ennemis {

    public EnnemiWindGrognard() {
        super(1, 7, 2, 5, Element.Air, 2, 1, StdDraw.WHITE);
    }

    public String toString() {
        return "Wind Grognard";
    }

    @Override
    public void envoieDegats(List<Combattant> lst, int halfLengthCell) {
        List<Tours> nvLst = lst.stream().map(e -> (Tours) e).toList();
        Tours moinsPv = null;

        for (Tours tour : nvLst) {
            if (this.distance(tour) <= this.getRange() * halfLengthCell * 2) {
                if (moinsPv == null) {
                    moinsPv = tour;
                }
                else {
                    if (moinsPv.getPv() > tour.getPv()) {
                        moinsPv = tour;
                    }
                }
            }
        }

        if (moinsPv != null) {
            moinsPv.enlevePV(this.getAtk() * this.vulnerabitite(moinsPv));
        }
    }
}
