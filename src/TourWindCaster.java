/**
 * Il vise l’ennemi qui se trouve à sa portée le plus proche de sa position.
 */
public class TourWindCaster extends Tours {
    private String type;
    private String nom;

    public TourWindCaster(String type, String nom, Joueur joueur) {
        super(30, 5, 1.5, 6, Element.Air, 50, joueur);
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
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(x,y,rayon);
    }
}
