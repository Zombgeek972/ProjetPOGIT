import java.awt.Color;

public class CellRoad extends Cell{
    private static int red = 194;
    private static int green = 178;
    private static int blue = 128;
    
    public CellRoad(int centerX, int centerY, int halfLength){
        super('R', centerX, centerY, halfLength, new Color(red, green, blue));
    }

    public String toString() {
        return super.toString()+"chemin.";
    }
}
