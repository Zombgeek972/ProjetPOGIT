import java.util.List;

/**
 * Un Minion ne peut pas attaquer.
 */
public class EnneMinion extends Ennemis{

    /**
     * constructeur d'un Minion.
     */
    public EnneMinion() {
        super(10, 3, 0, 0, Element.Neutre, 1, 1, StdDraw.YELLOW);
    }

    public String toString() {
        return "Minion";
    }

    @Override
    public void envoieDegats(List<Combattant> lst, int halfLengthCell){
        return;
    }
}
