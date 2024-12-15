import java.awt.Color;

public class CellBase extends Cell{
    private static Color color = StdDraw.ORANGE;

    public CellBase(int centerX, int centerY, int halfLength){
        super('B', centerX, centerY, halfLength, color);
    }

    public String toString() {
        return super.toString()+"base.";
    }
}
