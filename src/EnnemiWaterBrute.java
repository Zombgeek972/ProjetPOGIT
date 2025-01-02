import java.util.List;

/**
 * Un Water Brute vise la Tour qui se trouve à sa portée ayant le moins de PV. Les Tours à une distance (euclidienne entre les positions des deux éléments) inférieure à 1.5 case de la cible subissent les mêmes dommages que sa cible.
 */
public class EnnemiWaterBrute extends Ennemis {

    public EnnemiWaterBrute() {
        super(30, 5, 1, 3, Element.Eau, 1, 3, StdDraw.BLUE);
    }

    public String toString() {
        return "Water Brute";
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
            // on fait subir les dégats aux tours à moins de 1.5 cases de la tour visée par l'ennemi
            for (Tours tour : nvLst) {
                if (tour.distance(moinsPv) <= 2 * halfLengthCell * 1.5) {
                    if (tour != moinsPv) {
                        tour.enlevePV(this.getAtk() * this.vulnerabitite(moinsPv));
                    }
                }
            }
        }
    }
}
