import java.util.List;
public class Ennemis extends Combattant {
    private double speed;
    private double reward;
    private List<Position<Integer, Integer>> chemin;
    private Cell[][] quadrillage;
    
    public Ennemis(int pv, int atk, double atkSpeed, double range, Element element,
            double speed, double reward, String nomfichier, List<Position<Integer, Integer>> chemin, Cell[][] quadrillage) {
        super(pv, atk, atkSpeed, range, element);
        this.speed = speed;
        this.reward = reward;
        this.chemin = chemin;
        this.quadrillage = quadrillage;
    }

    public double getSpeed() {
        return speed;
    }
    public double getReward() {
        return reward;
    }

    public void draw() {
        Integer posXSpawn = quadrillage[chemin.get(0).getY()][chemin.get(0).getX()].getCenterX();
        Integer posYSpawn = quadrillage[chemin.get(0).getY()][chemin.get(0).getX()].getCenterY();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(posXSpawn, posYSpawn, 15);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledRectangle(posXSpawn, posYSpawn + 25, 40, 8);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledRectangle(posXSpawn-getPv()/2, posYSpawn + 25, getPv()*35/10, 5);

    }

    @Override
    public void recoitDegats(Combattant t) {

    }

    public String toString() {
        return "PV : "+getPv()+", atk : "+getAtk()+", atkSpeed : "+getAtkSpeed()+", portée : "+getRange()+", element : "+getElement()+ "), speed : "+speed+", récompense : "+reward;
    }
}
