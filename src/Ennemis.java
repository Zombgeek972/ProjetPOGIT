import java.util.List;
public class Ennemis extends Combattant {
    private double x;
    private double y;
    private double speedCell; // nb de cell/seconde
    private int speedPix; // nb de pixels/sec
    private int indiceCellCourante; // indice de la cellule courante dans le chemin
    private int direction;
    private double reward;
    private List<Cell> chemin;
    private Cell[][] quadrillage;
    
    public Ennemis(int pv, int atk, double atkSpeed, double range, Element element,
            double speedCell, double reward, String nomfichier, List<Cell> chemin, Cell[][] quadrillage, double x, double y) {
        super(pv, atk, atkSpeed, range, element);
        this.speedCell = speedCell;
        this.speedPix = (int) speedCell * this.chemin.get(0).getHalfLength()*2;
        this.indiceCellCourante = 0;
        this.reward = reward;
        this.chemin = chemin;
        this.quadrillage = quadrillage;
        this.x = x;
        this.y = y;
    }

    /**
     * met la direction dans laquelle aller, fonctionne dans le sens des aiguilles d'une montre, 0 : haut, 1 : droite, 2 : bas, 3 : gauche.
     * @param direction la nouvelle diréction
     */
    private void setDirection(int direction) {
        switch (direction) {
            case 0:
                this.direction = direction;
                break;
            case 1:
                this.direction = direction;
                break;
            case 2:
                this.direction = direction;
                break;
            case 3:
                this.direction = direction;
                break;
            default:
                //TODO erreur mauvais param
                break;
        }
    }

    /**
     * prend en parametre la position courante de l'ennemi après qu'il ai bougé sur la carte
     * @return true si l'ennemi est dans la même case qu'avant, false sinon
     */
    private boolean isInCell() {
        Cell celluleCourante = chemin.get(indiceCellCourante);
        return celluleCourante.getCenterX() - celluleCourante.getHalfLength() < x && x < celluleCourante.getCenterX() + celluleCourante.getHalfLength() && celluleCourante.getCenterY() - celluleCourante.getHalfLength() < y && y < celluleCourante.getCenterY() + celluleCourante.getHalfLength();
    }

    private void setCellCourante() {
        if (isInCell()) {
            return;
        }
        else {
            // si l'ancienne cellule courante n'était pas l'avant derniere cellule
            if (indiceCellCourante < chemin.size()-1) {
                for (indiceCellCourante++; indiceCellCourante<chemin.size(); indiceCellCourante++) {
                    if (isInCell()) {
                        break;
                    }
                }
            }
            else {
                indiceCellCourante++;
            }
        }
    }

    /**
     * compare tous les cas possible lors du déplacement d'un ennemi, et le fait se déplacer
     * @param direction la direction dans laquelle il doir se diriger
     */
    private void deplacement(double deltaTimeSec, int direction) {
        //TODO
        Cell celluleCourante = chemin.get(indiceCellCourante);
        int deplacement = (int) deltaTimeSec*speedPix;
        //si y et y + deplacement sont dans la même case
        if (y > chemin.get(indiceCellCourante).getCenterY() && y + deplacement > chemin.get(indiceCellCourante+1).getCenterY()) {

        }
        //si si y et y + deplacement change de case, on change la cellule actuelle de l'ennemi
        /*si y et y + deplacement passe par le centre d'une case, on va jusqu'au centre, on enregistre ce qui nous reste a parcourir
        et on regarde si on est a la base, on regarde vers ou aller pour continuer le chemin*/
    }

    public double getSpeedCell() {
        return speedCell;
    }
    public int getSpeedPix() {
        return speedPix;
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

    private void avanceBas(int pix) {
        y -= pix;
    }
    private void avanceHaut(int pix) {
        y += pix;
    }
    private void avanceGauche(int pix) {
        x -= pix;
    }
    private void avanceDroite(int pix) {
        x += pix;
    }

    public void avance(double deltaTimeSec) {
        //TODO va appeler deplacement et setCellCourante
        }
    }

    public void draw() {
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
        return "PV : "+getPv()+", atk : "+getAtk()+", atkSpeed : "+getAtkSpeed()+", portée : "+getRange()+", element : "+getElement()+ "), speedCell : "+speedCell+", speedPix : "+speedPix+", récompense : "+reward;
    }
}
