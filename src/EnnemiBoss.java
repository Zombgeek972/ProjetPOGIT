import java.util.List;

/**
 * Un Boss vise la Tour qui se trouve à sa portée le plus proche de sa position.
 */
public class EnnemiBoss extends Ennemis {

    /**
     * constructeur d'un ennemi Boss.
     */
    public EnnemiBoss() {
        super(150, 100, 10, 2, Element.Feu, 0.5, 100, StdDraw.BLACK);
    }

    public String toString() {
        return "Boss";
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
