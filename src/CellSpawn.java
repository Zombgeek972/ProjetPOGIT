import java.awt.Color;

public class CellSpawn extends Cell{
    private static Color color = StdDraw.RED;

    public CellSpawn(int i, int j){
        super('S', color, i, j);
    }

    public void setTour(Tours tour){}

    public String toString() {
        return super.toString();
    }
}
