import java.awt.Color;

/**
 * cellule grise où le joueur peut poser ses tours.
 */
public class CellConstructible extends Cell{
    private static Color color = StdDraw.LIGHT_GRAY;
    private Tours tour;

    /**
     * constructeur d'une cellule constructible.
     * @param i ligne où se situe la cellule dans le tableau de tableau du fichier "carte.java".
     * @param j colonne ou se situe la cellule.
     */
    public CellConstructible(int i, int j){
        super('C', color, i, j);
    }

    /**
     * permet de savoir quelle tour est posée sur la cellule avec laquelle on a appelée la methode.
     * @return la tour posée sur la cellule, null si il y en a pas.
     */
    public Tours getTour() {
        return tour;
    }

    /**
     * permet d'ajouter ou d'enlever une tour sur la cellule.
     * @param tour la tour à ajouter, null si on veut l'enlever.
     */
    public void setTour(Tours tour) {
        this.tour = tour;
        if (tour != null) {
            tour.setPosition(new Pair<Integer,Integer>(this.getCenterX(), this.getCenterY()));
        }
    }

    //pour dessiner la case et ensuite la tour sur la carte.
    public void draw() {
        super.draw();
        if (tour != null) {
            tour.draw(getCenterX(), getCenterY(), tour.getCouleur(), getHalfLength() - 0.2*getHalfLength());
        }
    }

    public String toString() {
        return super.toString();
    }
}
