import java.awt.Color;

public class CellBorder extends Cell{
    private static int red = 11;
    private static int green = 102;
    private static int blue = 35;

    public CellBorder(int i, int j){
        super('X', new Color(red, green, blue), i, j);
    }

    public String toString() {
        return super.toString();
    }
}
