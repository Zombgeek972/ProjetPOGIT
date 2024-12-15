import java.awt.Color;

/**
 * Il vise l’ennemi qui se trouve à sa portée ayant le plus grand nombre de PV. Les ennemis à moins de 1.0 case de la cible subissent les mêmes dommages que sa cible.
 */
public class TourEarthCaster extends Tours {
    private String type;
    private String nom;

    public TourEarthCaster(int pv, int atk, double atkSpeed, double range, int cout, String type, String nom) {
        super(pv, atk, atkSpeed, range, Element.Terre, cout);
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
        StdDraw.setPenColor(new Color(139, 69, 19)); //marron
        StdDraw.filledCircle(x,y,rayon);
    }
}
