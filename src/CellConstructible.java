import java.awt.Color;

public class CellConstructible extends Cell{
    private static Color color = StdDraw.LIGHT_GRAY;
    private Tours tour;

    public CellConstructible(int i, int j){
        super('C', color, i, j);
    }

    public void setTour(Tours tour) {
        this.tour = tour;
    }

    //pour dessiner la case et ensuite la tour pour la voir toujours sur la carte
    public void draw() {
        super.draw();
        if (tour != null) {
            tour.draw(getCenterX(), getCenterY(), getHalfLength() - 0.2*getHalfLength());
        }
    }

    public String toString() {
        return super.toString();
    }
}
