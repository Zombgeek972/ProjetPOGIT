import java.awt.Color;

public class CellBorder extends Cell{
    private static int red = 11;
    private static int green = 102;
    private static int blue = 35;

    public CellBorder(int centerX, int centerY, int halfLength){
        super('X', centerX, centerY, halfLength, new Color(red, green, blue));
    }

    public String toString() {
        return super.toString();
    }
}
