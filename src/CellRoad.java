import java.awt.Color;

public class CellRoad extends Cell{
    private static int red = 194;
    private static int green = 178;
    private static int blue = 128;
    
    public CellRoad(int i, int j){
        super('R', new Color(red, green, blue), i, j);
    }

    public String toString() {
        return super.toString();
    }
}
