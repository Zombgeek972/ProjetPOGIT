import java.awt.Color;

/**
 * cellule rouge où les énnemis spawn.
 */
public class CellSpawn extends Cell{
    private static Color color = StdDraw.RED;

    /**
     * constructeur d'une cellule de spawn.
     * @param i ligne où se situe la cellule dans le tableau de tableau du fichier "carte.java".
     * @param j colonne ou se situe la cellule.
     */
    public CellSpawn(int i, int j){
        super('S', color, i, j);
    }

    public String toString() {
        return super.toString();
    }
}
