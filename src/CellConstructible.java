import java.awt.Color;

public class CellConstructible extends Cell{
    private static Color color = StdDraw.LIGHT_GRAY;

    public CellConstructible(int centerX, int centerY, int halfLength){
        super('C', centerX, centerY, halfLength, color);
    }

    public String toString() {
        return super.toString();
    }
}
