import java.util.List;
public class Ennemis extends Combattant {
    private double x;
    private double y;
    private double speed;
    private double reward;
    private List<Cell> chemin;
    private Cell[][] quadrillage;
    
    public Ennemis(int pv, int atk, double atkSpeed, double range, Element element,
            double speed, double reward, String nomfichier, List<Cell> chemin, Cell[][] quadrillage, double x, double y) {
        super(pv, atk, atkSpeed, range, element);
        this.speed = speed;
        this.reward = reward;
        this.chemin = chemin;
        this.quadrillage = quadrillage;
        this.x = x;
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }
    public double getReward() {
        return reward;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void avanceHaut() {
        y += speed;
    }
    public void avanceBas() {
        y -= speed;
    }
    public void avanceGauche() {
        x -= speed;
    }
    public void avanceDroit() {
        x += speed;
    }

    public void draw() {
        //int posXSpawn = quadrillage[chemin.get(0).getX()][chemin.get(0).getX()].getCenterX();
        //int posYSpawn = quadrillage[chemin.get(0).getY()][chemin.get(0).getY()].getCenterY();
        //skin de l'ennemi
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(x, y, quadrillage[chemin.get(0).getI()][chemin.get(0).getJ()].getHalfLength()*0.2);
        //barre de vie
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledRectangle(x, y + 25, 40, 8);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledRectangle(x-getPv()/4, y + 25, getPv()*35/10, 5);

    }

    @Override
    public void recoitDegats(Combattant t) {

    }

    public String toString() {
        return "PV : "+getPv()+", atk : "+getAtk()+", atkSpeed : "+getAtkSpeed()+", portée : "+getRange()+", element : "+getElement()+ "), speed : "+speed+", récompense : "+reward;
    }
}
