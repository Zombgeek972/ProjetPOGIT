/**
 * Il vise l’ennemi qui se trouve à sa portée le plus avancé sur le chemin de la base.
 */
public class TourWaterCaster extends Tours {
    private String type;
    private String nom;

    public TourWaterCaster(String type, String nom, Joueur joueur) {
        super(30, 3, 1, 4, Element.Eau, 50, joueur);
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
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(x,y,rayon);
    }
}
