import java.awt.Color;
import libraries.StdDraw;

public class CellConstructible extends Cell{
    private static Color color = StdDraw.LIGHT_GRAY;

    public CellConstructible(int centerX, int centerY, int halfLength){
        super('C', centerX, centerY, halfLength, color);
    }

    public String toString() {
        return super.toString()+"constructible.";
    }
}
