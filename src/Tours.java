import java.awt.Color;

abstract class Tours extends Combattant {
    private int cout;

    public Tours(int pv, int atk, double atkSpeed, double range, Element element,
            int cout, Color couleur) {
        super(pv, atk, atkSpeed, range, element, couleur);
        this.cout = cout;
    }

    public int getCout() {
        return cout;
    }

    abstract Tours nouvelleInstance();

    public String toString() {
        return "PV : "+getPv()+", atk : "+getAtk()+", atkSpeed : "+getAtkSpeed()+", portée : "+getRange()+", element : "+getElement()+", cout : "+cout;
    }

    public void draw(double x, double y, Color couleur, double rayon) {
        double[] skinX = {
            rayon * 1 + x,
            rayon * 0.7 + x,
            0+ x,
            rayon * -0.7 + x,
            rayon * -1 + x,
            rayon * -0.7 + x,
            0 + x,
            rayon * 0.7 + x};

        double[] skinY = {
            0 + y,
            rayon * 0.7 + y,
            rayon * 1 + y,
            rayon * 0.7 + y,
            0 + y,
            rayon * -0.7 + y,
            rayon * -1 + y,
            rayon * -0.7 + y};
        StdDraw.setPenColor(getCouleur());
        StdDraw.filledPolygon(skinX,skinY);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.polygon(skinX, skinY);

        // affichage de la vie
        StdDraw.setPenColor(StdDraw.GRAY);
        double barreVieStatique = this.getMaxPv();
        StdDraw.filledRectangle(x, y+10, barreVieStatique + 2, 8);

        StdDraw.setPenColor(StdDraw.GREEN);
        double affichagePv = this.getPv() * barreVieStatique / this.getMaxPv();
        StdDraw.filledRectangle(x, y+10, affichagePv, 5);
    }

    // pour afficher la carte dans le magasin
    public void drawSansBarreVie(double x, double y, double rayon) {
        double[] skinX = {
            rayon * 1 + x,
            rayon * 0.7 + x,
            0+ x,
            rayon * -0.7 + x,
            rayon * -1 + x,
            rayon * -0.7 + x,
            0 + x,
            rayon * 0.7 + x};

        double[] skinY = {
            0 + y,
            rayon * 0.7 + y,
            rayon * 1 + y,
            rayon * 0.7 + y,
            0 + y,
            rayon * -0.7 + y,
            rayon * -1 + y,
            rayon * -0.7 + y};
        StdDraw.setPenColor(getCouleur());
        StdDraw.filledPolygon(skinX,skinY);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.polygon(skinX, skinY);
    }
}
