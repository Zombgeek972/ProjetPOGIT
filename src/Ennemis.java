import java.util.List;
public class Ennemis extends Combattant {
    private int x;
    private int y;
    private double speedCell;
    private int speedPix;
    private int indiceCellCourante;
    private int direction;
    private double reward;
    private List<Cell> chemin;
    private Cell[][] quadrillage;
    
    public Ennemis(int pv, int atk, double atkSpeed, double range, Element element,
            double speedCell, double reward, String nomfichier, List<Cell> chemin, Cell[][] quadrillage, int x, int y, Joueur joueur) {
        super(pv, atk, atkSpeed, range, element, joueur);
        this.speedCell = speedCell;
        this.speedPix = (int) speedCell*chemin.get(0).getHalfLength()*2;
        this.indiceCellCourante = 0;
        this.reward = reward;
        this.chemin = chemin;
        this.quadrillage = quadrillage;
        this.x = x;
        this.y = y;
    }

    /**
     * calcul la direction dans laquelle aller, on tourne dans le sens des aiguilles d'une montre, 0 : haut, 1 : droite, 2 : bas, 3 : gauche
     */
    private void setDirection() {
        //si on est entre l'avant derniere case et la base, on fait juste un tout droit , pas besoin de recalculer
        if (chemin.get(indiceCellCourante).getChar() != 'B') {
            int centreXCaseCourante = chemin.get(indiceCellCourante).getCenterX();
            int centreXCaseSuivante = chemin.get(indiceCellCourante+1).getCenterX();
            //a droite ?
            if (centreXCaseCourante - centreXCaseSuivante < 0) {
                direction = 1;
            }
            //a gauche ?
            else if (centreXCaseCourante - centreXCaseSuivante > 0) {
                direction = 3;
            }
            int centreYCaseCourante = chemin.get(indiceCellCourante).getCenterY();
            int centreYCaseSuivante = chemin.get(indiceCellCourante+1).getCenterY();
            //en haut ?
            if (centreYCaseCourante- centreYCaseSuivante < 0) {
                direction = 0;
            }
            //en bas ?
            else if (centreYCaseCourante- centreYCaseSuivante > 0) {
                direction = 2;
            }
        }
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

    private boolean isOnCell() {
        return chemin.get(indiceCellCourante).getCenterX() - chemin.get(indiceCellCourante).getHalfLength() <= x && x <= chemin.get(indiceCellCourante).getCenterX() + chemin.get(indiceCellCourante).getHalfLength() &&
        chemin.get(indiceCellCourante).getCenterY() - chemin.get(indiceCellCourante).getHalfLength() <= y && y <= chemin.get(indiceCellCourante).getCenterY() + chemin.get(indiceCellCourante).getHalfLength();
    }

    public Cell getCellCourante() {
        return chemin.get(indiceCellCourante);
    }
    private void setCellCourante() {
        if (!isOnCell()) {
            indiceCellCourante++;
        }
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    private void avanceHaut(int deplacement) {
        y += deplacement;
    }
    private void avanceBas(int deplacement) {
        y -= deplacement;
    }
    private void avanceGauche(int deplacement) {
        x -= deplacement;
    }
    private void avanceDroite(int deplacement) {
        x += deplacement;
    }
    
    public void avance(double deltaTimeSec) {
        int deplacement = (int) (speedPix*deltaTimeSec);
        setCellCourante();
        Cell celluleCourante = chemin.get(indiceCellCourante);

        if (chemin.get(chemin.size()-1).getCenterX() == x && chemin.get(chemin.size()-1).getCenterY() == y) {
            getJoueur().enleveVie(getAtk());
        }
        else {
            switch (direction) {
                //haut
                case 0:
                    //si on est en dessous du centre de notre case courante et qu'on va depasser le centre au prochain deplacement
                    if (celluleCourante.getCenterY() >= y && y + deplacement >= celluleCourante.getCenterY()) {
                        avanceHaut(celluleCourante.getCenterY()-y);
                        if (!(chemin.size() == indiceCellCourante+1)) {
                            setDirection();
                            System.out.println(direction);
                            //on finit le deplacement avec la bonne direction
                            switch (direction) {
                                case 0: avanceHaut(deplacement - (celluleCourante.getCenterY()-y)); break;
                                case 1: avanceDroite(deplacement - (celluleCourante.getCenterY()-y)); break;
                                case 2: avanceBas(deplacement - (celluleCourante.getCenterY()-y)); break;
                                case 3: avanceGauche(deplacement - (celluleCourante.getCenterY()-y)); break;
                            }
                        }
                        else {
                            getJoueur().enleveVie(getAtk());
                            break;
                        }
                    }
                    avanceHaut(deplacement);
                    break;
                //droite
                case 1:
                    //si on est en dessous du centre de notre case courante et qu'on va depasser le centre au prochain deplacement
                    if (celluleCourante.getCenterX() >= x && x + deplacement >= celluleCourante.getCenterX()) {
                        avanceDroite(celluleCourante.getCenterX()-x);
                        if (!(chemin.size() == indiceCellCourante+1)) {
                            setDirection();
                            System.out.println(direction);
                            //on finit le deplacement avec la bonne direction
                            switch (direction) {
                                case 0: avanceHaut(deplacement - (celluleCourante.getCenterY()-y)); break;
                                case 1: avanceDroite(deplacement - (celluleCourante.getCenterY()-y)); break;
                                case 2: avanceBas(deplacement - (celluleCourante.getCenterY()-y)); break;
                                case 3: avanceGauche(deplacement - (celluleCourante.getCenterY()-y)); break;
                            }
                        }
                        else {
                            //TODO enlever de la vie au joueur
                            break;
                        }
                    }
                    avanceDroite(deplacement);
                    break;
                //bas
                case 2:
                    if (celluleCourante.getCenterY() <= y && y - deplacement <= celluleCourante.getCenterY()) {
                        avanceBas(celluleCourante.getCenterY()-y);
                        if (!(chemin.size() == indiceCellCourante+1)) {
                            setDirection();
                            System.out.println(direction);
                            //on finit le deplacement avec la bonne direction
                            switch (direction) {
                                case 0: avanceHaut(deplacement - (celluleCourante.getCenterY()-y)); break;
                                case 1: avanceDroite(deplacement - (celluleCourante.getCenterY()-y)); break;
                                case 2: avanceBas(deplacement - (celluleCourante.getCenterY()-y)); break;
                                case 3: avanceGauche(deplacement - (celluleCourante.getCenterY()-y)); break;
                            }
                        }
                        else {
                            //TODO enlever de la vie au joueur
                            break;
                        }
                    }

                    avanceBas(deplacement);
                    break;
                //gauche
                case 3:
                    if (celluleCourante.getCenterX() <= x && x - deplacement <= celluleCourante.getCenterX()) {
                        avanceGauche(celluleCourante.getCenterX()-x);
                        if (!(chemin.size() == indiceCellCourante+1)) {
                            setDirection();
                            System.out.println(direction);
                            //on finit le deplacement avec la bonne direction
                            switch (direction) {
                                case 0: avanceHaut(deplacement - (celluleCourante.getCenterY()-y)); break;
                                case 1: avanceDroite(deplacement - (celluleCourante.getCenterY()-y)); break;
                                case 2: avanceBas(deplacement - (celluleCourante.getCenterY()-y)); break;
                                case 3: avanceGauche(deplacement - (celluleCourante.getCenterY()-y)); break;
                            }
                        }
                        else {
                            //TODO enlever de la vie au joueur
                            break;
                        }
                    }
                    avanceGauche(deplacement);
                    break;
                default:
                    break;
            }
        }
    }

    public void draw() {
        //skin de l'ennemi
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(x, y, quadrillage[chemin.get(0).getI()][chemin.get(0).getJ()].getHalfLength()*0.4);
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
        return "PV : "+getPv()+", atk : "+getAtk()+", atkSpeed : "+getAtkSpeed()+", portée : "+getRange()+", element : "+getElement()+ "), speed : "+speedCell+", speedPix : "+speedPix+", récompense : "+reward;
    }
}
