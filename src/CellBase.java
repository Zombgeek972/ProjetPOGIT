import java.awt.Color;

/**
 * cellule orange de la base du joueur
 */
public class CellBase extends Cell{
    private static Color color = StdDraw.ORANGE;

    /**
     * constructeur d'une cellule de la base du joueur
     * @param i ligne ou se situe la cellule dans le tableau de tableau du fichier "carte.java".
     * @param j colonne ou se situe la cellule.
     */
    public CellBase(int i, int j){
        super('B', color, i, j);
    }

    public String toString() {
        return super.toString();
    }
}
