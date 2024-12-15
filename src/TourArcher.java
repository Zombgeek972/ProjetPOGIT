/**
 * Une tour archer vise l’ennemi qui se trouve à sa portée le plus avancé sur le chemin de la base.
 */
public class TourArcher extends Tours {
    private String type;
    private String nom;

    public TourArcher(int pv, int atk, double atkSpeed, double range, int cout, String type,
            String nom) {
        super(pv, atk, atkSpeed, range, Element.Neutre, cout);
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
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledCircle(x,y,rayon);
    }
}
