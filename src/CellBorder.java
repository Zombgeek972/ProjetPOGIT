import java.awt.Color;

/**
 * cellule verte permettant de délimiter le terain, elles entourent toutes les cartes du jeu.
 */
public class CellBorder extends Cell{
    private static int red = 11;
    private static int green = 102;
    private static int blue = 35;

    /**
     * constructeur de la cellule de base du joueur
     * @param i ligne où se situe la cellule dans le tableau de tableau du fichier "carte.java".
     * @param j colonne ou se situe la cellule.
     */
    public CellBorder(int i, int j){
        super('X', new Color(red, green, blue), i, j);
    }

    public String toString() {
        return super.toString();
    }
}
