/**
 * Un Minion ne peut pas attaquer.
 */
public class EnneMinion extends Ennemis{

    public EnneMinion() {
        super(10, 3, 0, 0, Element.Neutre, 1, 1, StdDraw.YELLOW);
    }

    public String toString() {
        return "Minion";
    }
}