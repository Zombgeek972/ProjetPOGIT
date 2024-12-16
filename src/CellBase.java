import java.awt.Color;

public class CellBase extends Cell{
    private static Color color = StdDraw.ORANGE;

    public CellBase(int i, int j){
        super('B', color, i, j);
    }

    public void setTour(Tours tour){}

    public String toString() {
        return super.toString();
    }
}
