import java.util.List;
/**
 * Il vise l’ennemi qui se trouve à sa portée le plus proche de sa position.
 */
public class TourWindCaster extends Tours {

    public TourWindCaster() {
        super(30, 5, 1.5, 6, Element.Air, 50, StdDraw.WHITE);
    }

    public void draw(double x, double y, double rayon) {
        super.draw(x, y, getCouleur(), rayon);
    }

    /**
     * on fait subir des degats a l'ennemi le plus proche de sa position, et on remet le rechargement de la tour à 0
     * @param lst une file d'ennemis qui ont spawn, qui sont en vie et qui ne sont pas arrivés.
     */
    @Override
    void envoieDegats(List<Combattant> lst, int halfLengthCell) {
        Ennemis plusProche = null;
        Double distPlusProche = null;

        List<Ennemis> nvLst = lst.stream().map(e -> (Ennemis) e).toList();

        for (Ennemis e : nvLst) {
            if (this.distance(e) <= this.getRange() * halfLengthCell * 2) {
                if (plusProche == null) {
                    plusProche = e;
                    distPlusProche = this.distance(plusProche);
                }
                else {
                    double dist = this.distance(e);
                    if (dist < distPlusProche) {
                        plusProche = e;
                        distPlusProche = dist;
                    }
                }
            }
        }
        
        if (distPlusProche != null) {
            plusProche.enlevePV(this.getAtk() * plusProche.vulnerabitite(this));
        }
    }

    @Override
    public Tours nouvelleInstance() {
        return new TourWindCaster();
    }
}
