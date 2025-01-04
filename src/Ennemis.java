import java.awt.Color;
import java.util.List;

/**
 * classe abstraite contenant toutes les informations nécessaires pour que tout marche bien.
 */
abstract class Ennemis extends Combattant {
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
    
    /**
     * constructeur d'un ennemi
     * @param pv son nombre de points de vie.
     * @param atk sa quantité d'attaque.
     * @param atkSpeed sa vitesse d'attaque.
     * @param range la portée de son attaque.
     * @param element le type de l'ennemi entre Neutre, Terre, Eau, Feu et Air.
     * @param speedCell sa vitesse de déplacement en nombre de cellules par seconde.
     * @param reward la quantité de pièces à donner au joueur lorsqu'il arrive à tuer l'ennemi.
     * @param couleur la couleur de n'ennemi en fonction de son element.
     */
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

    /**
     * permet de savoir à quelle vitesse se déplace le joueur.
     * @return sa vitesse en cellule/seconde.
     */
    public double getSpeedCell() {
        return speedCell;
    }

    /**
     * permet de savoir à quelle vitese se déplace le joueur.
     * @return sa vitesse en pixels/seconde.
     */
    public int getSpeedPix() {
        return speedPix;
    }

    /**
     * permet de savoir combien de pièces il faut ajouter au joueur si il arrive à touer l'ennemi.
     * @return ne nombre de pièces.
     */
    public int getReward() {
        return reward;
    }

    /**
     * permet de savoir si l'ennemi a spawn sur la carte ou non.
     * @return true si il a spawn, false sinon.
     */
    public boolean getASpawn() {
        return aSpawn;
    }

    /**
     * permet d'enregistrer le fait que l'ennemi a spawn sur la carte.
     */
    public void setASpawn() {
        aSpawn = true;
    }

    /**
     * permet d'enregistrer ce que doit faire l'énnemi.
     * @param b le booleen à enregistrer. true veut dire que l'énnemi progresse, false veut dire que l'énnemi ne progresse pas.
     */
    public void setProgresse(boolean b) {
        progresse = b;
    }

    /**
     * permet de savoir si l'ennemi progresse encore ou pas. Si il progresse, c'est qu'il a spawn et qu'il est toujours en vie.
     * @return true si il progresse, false sinon.
     */
    public boolean getProgresse() {
        return progresse;
    }

    /**
     * permet d'enregistrer le fait que l'énnemi est arrivé à la base du joueur.
     */
    public void setArrive() {
        arrive = true;
    }

    /**
     * permet de savoir si l'énnemi est arrivé.
     * @return true si il est arrivé, false sinon.
     */
    public boolean getArrive() {
        return arrive;
    }

    /**
     * permet de savoir sur quelle carte les énnemis progressent.
     * @param quadrillage la carte instanciée avec toutes les cellules.
     */
    public void setCarte(Cell[][] quadrillage) {
        this.quadrillage = quadrillage;
    }

    /**
     * permet de savoir sur quel chemin les énnemis doivent progresser.
     * @param chemin le chemin que les énnemis doivent suivre.
     */
    public void setChemin(List<Cell> chemin) {
        this.chemin = chemin;
        setSpeedPix();
    }
    private void setSpeedPix() {
        this.speedPix = (int) speedCell*chemin.get(0).getHalfLength()*2;
    }

    /**
     * permet d'enregistrer les coordonnées de spawn des énnemis.
     * @param x la coordonnée x de spawn.
     * @param y la coordonnée y de spawn.
     */
    public void setSpawn(int x, int y) {
        setPosition(new Pair<Integer, Integer> (x,y));
    }

    /**
     * permet de savoir sur quelle cellule du chemin se situe l'ennemi.
     * @return la cellule où se situe l'ennemi.
     */
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

    /**
     * permet d'afficher l'ennemi à l'ecran avec sa bonne position, couleur et nombre de PV.
     */
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
