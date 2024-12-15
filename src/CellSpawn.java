import java.awt.Color;

public class CellSpawn extends Cell{
    private static Color color = StdDraw.RED;

    public CellSpawn(int centerX, int centerY, int halfLength){
        super('S', centerX, centerY, halfLength, color);
    }

    public String toString() {
        return super.toString()+"spawn.";
    }
}
