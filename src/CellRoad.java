import java.awt.Color;

/**
 * Cellule jaune/beige sur lesquelles les énnemis avancent.
 */
public class CellRoad extends Cell{
    private static int red = 194;
    private static int green = 178;
    private static int blue = 128;
    
    /**
     * constructeur d'une cellule road
     * @param i ligne où se situe la cellule dans le tableau de tableau du fichier "carte.java".
     * @param j colonne ou se situe la cellule.
     */
    public CellRoad(int i, int j){
        super('R', new Color(red, green, blue), i, j);
    }

    public String toString() {
        return super.toString();
    }
}
