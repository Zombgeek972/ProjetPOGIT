import java.awt.Color;

public class CellConstructible extends Cell{
    private static Color color = StdDraw.LIGHT_GRAY;

    public CellConstructible(int i, int j){
        super('C', color, i, j);
    }

    public String toString() {
        return super.toString();
    }
}
