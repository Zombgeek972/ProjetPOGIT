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

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColorBorder() {
        return colorBorder;
    }
    public void setColorBorderWhite() {
        colorBorder = StdDraw.WHITE;
    }
    public void setColorBorderBlack() {
        colorBorder = StdDraw.BLACK;
    }
    public boolean isBorderWhite() {
        return colorBorder == StdDraw.WHITE;
    }
    public boolean isBorderBlack() {
        return colorBorder == StdDraw.BLACK;
    }

    public char getChar() {
        return c;
    }

    public boolean estConstructible() {
        return constructible;
    }

    public int getCenterX() {
        return centerX;
    }
    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }
    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getHalfLength() {
        return halfLength;
    }
    public void setHalfLength(int halfLength) {
        this.halfLength = halfLength;
    }

    public int getI() {
        return i;
    }
    public int getJ() {
        return j;
    }
    
    public void draw() {
        StdDraw.setPenColor(this.color);
        StdDraw.filledSquare (centerX, centerY, halfLength);
        StdDraw.setPenColor(colorBorder);
        StdDraw.square(centerX, centerY, halfLength);
    }

    /**
     * @apiNote les parametres sont les coordonnées de la souris, elles sont comparées avec les coordonnées de la case
     * @param x coordonnées x de la souris
     * @param y coordonnées y de la souris
<<<<<<< HEAD
     * @return si la souris est sur la case lorsque l'on clique
=======
     * @return vrai si la souris est sur la case lorsque l'on clique
>>>>>>> 9367f7a2d41b40855332f1323d9d0071eff61125
     */
    public boolean isMouseOn(double x, double y) {
        return centerX-halfLength < x && x < centerX+halfLength && centerY-halfLength < y && y < centerY+halfLength;
    }

    public String toString() {
        return Character.toString(c);
    }
}
