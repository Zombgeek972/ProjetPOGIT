import java.util.List;

/**
 * Une tour Water Caster vise l’ennemi qui se trouve à sa portée le plus avancé sur le chemin de la base.
 */
public class TourWaterCaster extends Tours {

    /**
     * constructeur permettant de créer une tour Water Caster.
     */
    public TourWaterCaster() {
        super(30, 3, 1, 4, Element.Eau, 50, StdDraw.BLUE);
    }

    /**
     * permet d'afficher la tour à l'ecran.
     * @param x sa coordonnée x sur le canvas.
     * @param y sa coordonnée y sur le canvas.
     * @param rayon sa taille.
     */
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
        return new TourWaterCaster();
    }
}
