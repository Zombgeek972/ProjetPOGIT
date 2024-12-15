import java.awt.Color;
abstract class Cell extends Game{
    private char c;
    private boolean constructible;
    private int centerX;
    private int centerY;
    private int halfLength;
    private Color color;

    public Cell(char c, int centerX, int centerY, int halfLength, Color color) {
        this.c = c;
        this.centerX = centerX;
        this.centerY = centerY;
        this.halfLength = halfLength;
        this.color = color;
        constructible = c=='C';
    }

    public void setColor(Color color) {
        this.color = color;
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

    public Color getColor() {
        return color;
    }

    public void draw() {
        StdDraw.setPenColor(this.color);
        StdDraw.filledSquare (centerX, centerY, halfLength);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.square(centerX, centerY, halfLength);
    }

    /**
     * @apiNote les parametres sont les coordonnées de la souris, elles sont comparées avec les coordonnées de la case
     * @param x coordonnées x de la souris
     * @param y coordonnées y de la souris
     * @return vrai si la souris est sur la case lorsque l'on clique
     */
    public boolean isMouseOn(double x, double y) {
        if (centerX-halfLength < x && x < centerX+halfLength) {
            if (centerY-halfLength < y && y < centerY+halfLength) {
                return true;
            }
            return false;
        }
        return false;
    }

    public String toString() {
        return Character.toString(c);
    }
}
