/**
 * Il vise l’ennemi qui se trouve à sa portée le plus proche de sa position.
 */
public class TourWindCaster extends Tours {
    private String type;
    private String nom;

    public TourWindCaster(int pv, int atk, double atkSpeed, double range, int cout, String type, String nom) {
        super(pv, atk, atkSpeed, range, Element.Air, cout);
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
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(x,y,rayon);
    }
}
