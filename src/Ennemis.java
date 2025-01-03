import java.awt.Color;
import java.util.List;

public class Ennemis extends Combattant {
    private double speedCell;
    private int speedPix;
    private int indiceCellCourante;
    private int direction;
    private int reward;
    private List<Cell> chemin;
    private Cell[][] quadrillage;
    private boolean aSpawn;
    private boolean progresse;
    private boolean arrive;
    
    public Ennemis(int pv, int atk, double atkSpeed, double range, Element element,
            double speedCell, int reward, Color couleur) {
        super(pv, atk, atkSpeed, range, element, couleur);
        this.speedCell = speedCell;
        this.indiceCellCourante = 0;
        this.reward = reward;
    }

    /**
     * calcul la direction dans laquelle aller, on tourne dans le sens des aiguilles d'une montre, 0 : haut, 1 : droite, 2 : bas, 3 : gauche
     */
    private void setDirection() {
        // si on est entre l'avant derniere case et la base, on fait juste un tout droit , pas besoin de recalculer
        // ici le ca ou on est pas devant la base
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
            if (centreYCaseCourante - centreYCaseSuivante < 0) {
                direction = 0;
            }
            //en bas ?
            else if (centreYCaseCourante - centreYCaseSuivante > 0) {
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
    public int getReward() {
        return reward;
    }
    public boolean getASpawn() {
        return aSpawn;
    }

    public void setASpawn() {
        aSpawn = true;
    }

    public void setProgresse(boolean b) {
        progresse = b;
    }
    public boolean getProgresse() {
        return progresse;
    }

    public void setArrive() {
        arrive = true;
    }
    public boolean getArrive() {
        return arrive;
    }

    public void setCarte(Cell[][] quadrillage) {
        this.quadrillage = quadrillage;
    }
    public void setChemin(List<Cell> chemin) {
        this.chemin = chemin;
        setSpeedPix();
    }
    private void setSpeedPix() {
        this.speedPix = (int) speedCell*chemin.get(0).getHalfLength()*2;
    }

    public void setSpawn(int x, int y) {
        setPosition(new Pair<Integer, Integer> (x,y));
    }

    public Cell getCellCourante() {
        return chemin.get(indiceCellCourante);
    }
    private void setCellCourante() {
        indiceCellCourante++;
    }

    private void avanceHaut(int deplacement) {
        setPosition(new Pair<Integer,Integer>(getPosition().getElt1(), getPosition().getElt2()+deplacement));
    }
    private void avanceBas(int deplacement) {
        setPosition(new Pair<Integer,Integer>(getPosition().getElt1(), getPosition().getElt2()-deplacement));
    }
    private void avanceGauche(int deplacement) {
        setPosition(new Pair<Integer,Integer>(getPosition().getElt1()-deplacement, getPosition().getElt2()));
    }
    private void avanceDroite(int deplacement) {
        setPosition(new Pair<Integer,Integer>(getPosition().getElt1()+deplacement, getPosition().getElt2()));
    }
    
    private void deplace(int d, Cell celluleCourante) {
        switch (direction) {
            case 0: avanceHaut(d - (celluleCourante.getCenterY()-getPosition().getElt2())); break;
            case 1: avanceDroite(d - (celluleCourante.getCenterY()-getPosition().getElt2())); break;
            case 2: avanceBas(d - (celluleCourante.getCenterY()-getPosition().getElt2())); break;
            case 3: avanceGauche(d - (celluleCourante.getCenterY()-getPosition().getElt2())); break;
        }
    }

    /**
     * calcul la distance euclidienne au carré entre l'ennemi et le centre de la case base.
     * @return vrai si la distance est en dessous de 280 pixels.
     */
    public boolean arrive() {
        return Math.sqrt(Math.pow(chemin.get(chemin.size()-1).getCenterX() - getPosition().getElt1(), 2) + Math.pow(chemin.get(chemin.size()-1).getCenterY() - getPosition().getElt2(), 2)) <= chemin.get(0).getHalfLength()/2;
    }
    
    /**
     * une fonction qui fait avancer l'ennemi en fonction de deltaTimeSec
     * @param deltaTimeSec le temps en seconde, determine la distance que va parcourir l'ennemi.
     * @return vrai si l'ennemi est toujours en vie.
     */
    public void avance(double deltaTimeSec) {

        int deplacement = (int) (speedPix*deltaTimeSec);

        if (deplacement < 1) {deplacement = 1;}

        Cell celluleCourante = chemin.get(indiceCellCourante);

        switch (direction) {
            //haut
            case 0:
                //si on est en dessous du centre de notre case courante et qu'on va depasser le centre au prochain deplacement
                if (celluleCourante.getCenterY() >= getPosition().getElt2() && getPosition().getElt2() + deplacement >= celluleCourante.getCenterY()) {
                    avanceHaut(celluleCourante.getCenterY()-getPosition().getElt2());
                    setDirection();
                    //on finit le deplacement avec la bonne direction
                    deplace(deplacement, celluleCourante);
                    break;
                }
                else {
                    avanceHaut(deplacement);
                    if (celluleCourante.getCenterY() + celluleCourante.getHalfLength() < this.getPosition().getElt2()) {
                        setCellCourante();
                    }
                    break;
                }
            //droite
            case 1:
                //si on est à gauche du centre de notre case courante et qu'on va depasser le centre au prochain deplacement
                if (celluleCourante.getCenterX() >= getPosition().getElt1() && getPosition().getElt1() + deplacement >= celluleCourante.getCenterX()) {
                    avanceDroite(celluleCourante.getCenterX()-getPosition().getElt1());
                    setDirection();
                    //on finit le deplacement avec la bonne direction
                    deplace(deplacement, celluleCourante);
                    break;
                }
                else {
                    avanceDroite(deplacement);
                    if (celluleCourante.getCenterX() + celluleCourante.getHalfLength() < this.getPosition().getElt1()) {
                        setCellCourante();
                    }
                    break;
                }
            //bas
            //si on est à gauche du centre de notre case courante et qu'on va depasser le centre au prochain deplacement
            case 2:
                if (celluleCourante.getCenterY() <= getPosition().getElt2() && getPosition().getElt2() - deplacement <= celluleCourante.getCenterY()) {
                    avanceBas(celluleCourante.getCenterY()-getPosition().getElt2());
                    setDirection();
                    //on finit le deplacement avec la bonne direction
                    deplace(deplacement, celluleCourante);
                    break;
                }
                else {
                    avanceBas(deplacement);
                    if (celluleCourante.getCenterY() - celluleCourante.getHalfLength() > this.getPosition().getElt2()) {
                        setCellCourante();
                    }
                    break;
                }
            //gauche
            case 3:
                if (celluleCourante.getCenterX() <= getPosition().getElt1() && getPosition().getElt1() - deplacement <= celluleCourante.getCenterX()) {
                    avanceGauche(celluleCourante.getCenterX()-getPosition().getElt1());
                    setDirection();
                    //on finit le deplacement avec la bonne direction
                    deplace(deplacement, celluleCourante);
                    break;
                }
                else {
                    avanceGauche(deplacement);
                    if (celluleCourante.getCenterX() - celluleCourante.getHalfLength() > this.getPosition().getElt1()) {
                        setCellCourante();
                    }
                    break;
                }

            default:
                break;
        }
    }

    public void draw() {
        //skin de l'ennemi
        double rayon = quadrillage[chemin.get(0).getI()][chemin.get(0).getJ()].getHalfLength()*0.3;
        StdDraw.setPenColor(getCouleur());
        StdDraw.filledCircle(getPosition().getElt1(), getPosition().getElt2(), rayon);
        //barre de vie
        double barreVieStatique = this.getMaxPv();
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledRectangle(getPosition().getElt1(), getPosition().getElt2() + 25, barreVieStatique + 2, 8);

        double affichagePv = this.getPv() * barreVieStatique / this.getMaxPv();
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledRectangle(getPosition().getElt1(), getPosition().getElt2() + 25, affichagePv, 5);

    }

    // j'ai eu besoin de l'implementer alors que je n'ai pas eu besoin pour Tours.java, c'est une methode pour les classes filles de Ennemis et Tours.
    @Override
    void envoieDegats(List<Combattant> lst, int halfLengthCell) {
        return;
    }
}
