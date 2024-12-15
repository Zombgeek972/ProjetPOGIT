import java.awt.Color;

/**
 * Il vise l’ennemi qui se trouve à sa portée ayant le plus grand nombre de PV. Les ennemis à moins de 1.0 case de la cible subissent les mêmes dommages que sa cible.
 */
public class TourEarthCaster extends Tours {
    private String type;
    private String nom;

    public TourEarthCaster(String type, String nom) {
        super(50, 7, 0.5, 2.5, Element.Terre, 100);
        this.type = type;
        this.nom = nom;
    }

    public String getType() {
        return type;
    }
    public String getNom() {
        return nom;
    }
    
    @Override
    public void draw(double x, double y, double rayon) {
        StdDraw.setPenColor(new Color(139, 69, 19)); //marron
        StdDraw.filledCircle(x,y,rayon);
    }
}
