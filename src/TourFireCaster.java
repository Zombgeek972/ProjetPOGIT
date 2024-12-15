/**
 * Il vise l’ennemi qui se trouve à sa portée le plus proche de sa position. Les ennemis à une distance (euclidienne entre les positions des deux éléments) inférieure à 0.75 case de la cible subissent les mêmes dommages que sa cible.
 */
public class TourFireCaster extends Tours {
    private String type;
    private String nom;

    public TourFireCaster(String type, String nom) {
        super(30, 10, 0.5, 2.5, Element.Feu, 100);
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
    public void draw(double x, double y, int rayon) {
        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.filledCircle(x,y,rayon);
    }
}
