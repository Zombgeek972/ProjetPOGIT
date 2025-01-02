import java.awt.Color;

public class CellConstructible extends Cell{
    private static Color color = StdDraw.LIGHT_GRAY;
    private Tours tour;

    public CellConstructible(int i, int j){
        super('C', color, i, j);
    }

    public Tours getTour() {
        return tour;
    }
    public void setTour(Tours tour) {
        this.tour = tour;
        if (tour != null) {
            tour.setPosition(new Pair<Integer,Integer>(this.getCenterX(), this.getCenterY()));
        }
    }

    //pour dessiner la case et ensuite la tour pour la voir toujours sur la carte
    public void draw() {
        super.draw();
        if (tour != null) {
            tour.draw(getCenterX(), getCenterY(), tour.getCouleur(), getHalfLength() - 0.2*getHalfLength());
        }
    }

    public String toString() {
        return super.toString();
    }
}
