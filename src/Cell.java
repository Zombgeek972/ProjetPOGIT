import java.awt.Color;
abstract class Cell extends Game{
    private char c;
    private boolean constructible;
    private int centerX;
    private int centerY;
    private int halfLength;
    private Color color;
    private Color colorBorder;
    //position i,j de la cellule dans le quadrillage de la classe carte
    private int i;
    private int j;

    public Cell(char c, Color color, int i, int j) {
        this.c = c;
        this.color = color;
        this.constructible = c=='C';
        this.colorBorder = StdDraw.BLACK;
        this.i = i;
        this.j = j;
    }

    /**
     * permet de récuperer la couleur associée à la cellule avec laquelle on a appelé la fonction.
     * @return la couleur de la cellule.
     */
    public Color getColor() {
        return color;
    }

    /**
     * permet d'initialiser la couleur de la cellule en fonction de son caractere.
     * @param color la couleur que nous voulons donner à la cellule.
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * permet de récuperer la couleur de la bordure de la cellule, cela permet de donner cet effet de grille à l'afichage.
     * @return la couleur du contour de la cellule.
     */
    public Color getColorBorder() {
        return colorBorder;
    }

    /**
     * permet de récuperer le caractere utilisé pour pour créer la cellule.
     * @return c, le caractere de la cellule.
     */
    public char getChar() {
        return c;
    }

    /**
     * permet de savoir si on peut placer une tour sur la cellule ou non.
     * @return true si c'est une cellule constructible false sinon.
     */
    public boolean estConstructible() {
        return constructible;
    }

    /**
     * permet de récuperer la coordonnée x du centre de la cellule dans le canvas.
     * @return la coordonnée x du centre de la case.
     */
    public int getCenterX() {
        return centerX;
    }

    /**
     * permet d'initialiser la coodronnée x de la cellule sur le canvas.
     * @param centerX la coordonnée x que nous voulons donner à la cellule.
     */
    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    /**
     * permet de récuperer la coordonnée y du centre de la cellule dans le canvas.
     * @return la coordonnée y du centre de la case.
     */
    public int getCenterY() {
        return centerY;
    }

    /**
     * permet d'initialiser la coodronnée y de la cellule sur le canvas.
     * @param centerY la coordonnée y que nous voulons donner à la cellule.
     */
    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    /**
     * permet de récuperer la moitié de la taille d'une cellule.
     * @return la moitié de la longueur de la cellule.
     */
    public int getHalfLength() {
        return halfLength;
    }

    /**
     * permet d'initialiser la moitié de longueur d'une cellule.
     * @param halfLength la moitié de longueur que nous voulons lui donner.
     */
    public void setHalfLength(int halfLength) {
        this.halfLength = halfLength;
    }

    /**
     * permet de récuperer la ligne où se trouve la cellule dans le quadrillage.
     * @return la ligne du quadrillage.
     */
    public int getI() {
        return i;
    }

    /**
     * permet de récuperer la colonne où se trouve la cellule dans le quadrillage.
     * @return la colonne du quadrillage.
     */
    public int getJ() {
        return j;
    }
    
    /**
     * methode de dessin d'une cellule.
     */
    public void draw() {
        // carré
        StdDraw.setPenColor(this.color);
        StdDraw.filledSquare (centerX, centerY, halfLength);
        // contour du carré
        StdDraw.setPenColor(colorBorder);
        StdDraw.square(centerX, centerY, halfLength);
    }

    /**
     * les parametres sont les coordonnées de la souris, elles sont comparées avec les coordonnées de la case
     * @param x coordonnées x de la souris
     * @param y coordonnées y de la souris
     * @return si la souris est sur la case lorsque l'on clique
     */
    public boolean isMouseOn(double x, double y) {
        return centerX-halfLength < x && x < centerX+halfLength && centerY-halfLength < y && y < centerY+halfLength;
    }

    public String toString() {
        return Character.toString(c);
    }
}
