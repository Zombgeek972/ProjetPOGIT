import java.awt.Color;
import java.util.List;

/**
 * Un Earth Brute vise la Tour qui se trouve à sa portée le plus proche de sa position.
 */
public class EnnemiEarthBrute extends Ennemis {

    public EnnemiEarthBrute() {
        super(30, 2, 2, 3, Element.Terre, 1, 3, new Color(139, 69, 19));
    }

    public String toString() {
        return "Earth Brute";
    }

    @Override
    public void envoieDegats(List<Combattant> lst, int halfLengthCell) {
        Tours plusProche = null;
        Double distPlusProche = null;

        List<Tours> nvLst = lst.stream().map(e -> (Tours) e).toList();

        for (Tours t : nvLst) {
            if (this.distance(t) <= this.getRange() * halfLengthCell * 2) {
                if (plusProche == null) {
                    plusProche = t;
                    distPlusProche = this.distance(plusProche);
                }
                else {
                    double dist = this.distance(t);
                    if (dist < distPlusProche) {
                        plusProche = t;
                        distPlusProche = dist;
                    }
                }
            }
        }
        
        if (distPlusProche != null) {
            plusProche.enlevePV(this.getAtk() * plusProche.vulnerabitite(this));
        }
    }

}
