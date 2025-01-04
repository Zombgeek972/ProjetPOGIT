import java.awt.Color;
import java.util.List;

/**
 * Un Fire Grognard vise la Tour qui se trouve à sa portée la plus proche de sa position. Les Tours à une distance (euclidienne entre les positions des deux éléments) inférieure à 1.5 case de la cible subissent les mêmes dommages que sa cible.
 */
public class EnnemiFireGrognard extends Ennemis {

    /**
     * constructueur d'un Fire Grognard.
     */
    public EnnemiFireGrognard() {
        super(1, 4, 2, 3, Element.Feu, 2, 1, new Color(255, 140, 0));
    }

    public String toString() {
        return "Fire Grognard";
    }

    @Override
    public void envoieDegats(List<Combattant> lst, int halfLengthCell) {
        Tours plusProche = null;
        Double distPlusProche = null;

        List<Tours> nvLst = lst.stream().map(e -> (Tours) e).toList();

        for (int i = 0; i<nvLst.size(); i++) {
            Tours t = nvLst.get(i);

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
        
        if (plusProche != null) {
            plusProche.enlevePV(this.getAtk() * plusProche.vulnerabitite(this));

            // on fait subir les dégats aux tours à moins de 1.5 cases de la tour visée par l'ennemi
            for (Tours tour : nvLst) {
                if (tour.distance(plusProche) <= 2 * halfLengthCell * 1.5) {
                    if (tour != plusProche) {
                        System.out.println("tour detek");
                        tour.enlevePV(this.getAtk() * this.vulnerabitite(plusProche));
                    }
                }
            }
        }
    }
}
