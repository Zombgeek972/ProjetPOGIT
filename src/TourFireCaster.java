import java.awt.Color;
import java.util.List;

/**
 * Une tour Fire Caster vise l’ennemi qui se trouve à sa portée le plus proche de sa position. Les ennemis à une distance (euclidienne entre les positions des deux éléments) inférieure à 0.75 case de la cible subissent les mêmes dommages que sa cible.
 */
public class TourFireCaster extends Tours {

    public TourFireCaster() {
        super(30, 10, 0.5, 2.5, Element.Feu, 100, new Color(255, 140, 0));
    }

    public void draw(double x, double y, double rayon) {
        super.draw(x, y, getCouleur(), rayon);
    }

    @Override
    void envoieDegats(List<Combattant> lst, int halfLengthCell) {
        Ennemis plusProche = null;
        Double distPlusProche = null;

        List<Ennemis> nvLst = lst.stream().map(e -> (Ennemis) e).toList();

        int indice = 0;
        for (int i = 0; i<nvLst.size(); i++) {
            Ennemis e = nvLst.get(i);

            if (this.distance(e) <= this.getRange() * halfLengthCell * 2) {
                if (plusProche == null) {
                    plusProche = e;
                    distPlusProche = this.distance(plusProche);
                    indice = i;
                }
                else {
                    double dist = this.distance(e);
                    if (dist < distPlusProche) {
                        plusProche = e;
                        distPlusProche = dist;
                        indice = i;
                    }
                }
            }
        }
        
        if (plusProche != null) {
            plusProche.enlevePV(this.getAtk() * plusProche.vulnerabitite(this));

            if (!lst.isEmpty()) {
                // on regarde les énnemis apres celui selectionné, on regarde si ils sont à moins de 1 case
                for (int i = indice; i<lst.size(); i++) {
                    Ennemis e = nvLst.get(i);
                    if (plusProche.distance(e) < halfLengthCell * 2 * 0.75) {
                        e.enlevePV(this.getAtk() * e.vulnerabitite(this));
                    }
                }

                // pareil pour ceux avant celui selectionné
                for (int i = indice; i >= 0; i--) {
                    Ennemis e = nvLst.get(i);
                    if (plusProche.distance(e) < halfLengthCell * 2 * 0.75) {
                        e.enlevePV(this.getAtk() * e.vulnerabitite(this));
                    }
                }
            }
        }
    }

    @Override
    public Tours nouvelleInstance() {
        return new TourFireCaster();
    }
}
