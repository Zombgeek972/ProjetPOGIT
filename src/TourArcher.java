import java.util.List;

/**
 * Une tour archer vise l’ennemi qui se trouve à sa portée le plus avancé sur le chemin de la base.
 */
public class TourArcher extends Tours {

    public TourArcher() {
        super(30, 5, 0.5, 2, Element.Neutre, 20, StdDraw.YELLOW);
    }
    
    public void draw(double x, double y, double rayon) {
        super.draw(x, y, getCouleur(), rayon);
    }

    /**
     * on fait subir des degats au premier que la tour peut atteindre (donc au plus proche de la base) de cette liste car les ennemis sont dans l'ordre de spawn.
     * @param lst une file d'ennemis qui ont spawn, qui sont en vie et qui ne sont pas arrivés.
     */
    @Override
    public void envoieDegats(List<Combattant> lst, int halfLengthCell) {
        List<Ennemis> nvLst = lst.stream().map(e -> (Ennemis) e).toList();
        for (Ennemis e : nvLst) {
            if (this.distance(e) <= this.getRange() * halfLengthCell * 2) {
                e.enlevePV(this.getAtk() * e.vulnerabitite(this));
                return;
            }
        }
    }

    @Override
    public Tours nouvelleInstance() {
        return new TourArcher();
    }
}
