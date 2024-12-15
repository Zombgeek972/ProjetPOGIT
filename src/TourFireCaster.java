/**
 * Il vise l’ennemi qui se trouve à sa portée le plus proche de sa position. Les ennemis à une distance (euclidienne entre les positions des deux éléments) inférieure à 0.75 case de la cible subissent les mêmes dommages que sa cible.
 */
public class TourFireCaster extends Tours {
    private String type;
    private String nom;

    public TourFireCaster(int pv, int atk, double atkSpeed, double range, int cout, String type, String nom) {
        super(pv, atk, atkSpeed, range, Element.Feu, cout);
        this.type = type;
        this.nom = nom;
    }

    public String getType() {
        return type;
    }
    public String getNom() {
        return nom;
    }

    public void draw(double x, double y, int rayon) {
        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.filledCircle(x,y,rayon);
    }
}
