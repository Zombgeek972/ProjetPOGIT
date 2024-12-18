/**
 * Une tour archer vise l’ennemi qui se trouve à sa portée le plus avancé sur le chemin de la base.
 */
public class TourArcher extends Tours {
    private String type;
    private String nom;

    public TourArcher(String type, String nom, Joueur joueur) {
        super(30, 5, 1, 2, Element.Neutre, 20, joueur);
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
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledCircle(x,y,rayon);
    }
}
