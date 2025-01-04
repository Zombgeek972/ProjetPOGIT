import java.awt.Color;

abstract class Tours extends Combattant {
    private int cout;

    public Tours(int pv, int atk, double atkSpeed, double range, Element element,
            int cout, Color couleur) {
        super(pv, atk, atkSpeed, range, element, couleur);
        this.cout = cout;
    }

    /**
     * permet de savoir combien coute la tour.
     * @return son coùt.
     */
    public int getCout() {
        return cout;
    }

    /**
     * permet de generer une nouvelle instance d'une toue.
     * @return la même tour dupliquée. sert uniquement pour selectionner une tour dans le magasin.
     */
    abstract Tours nouvelleInstance();

    public String toString() {
        return "PV : "+getPv()+", atk : "+getAtk()+", atkSpeed : "+getAtkSpeed()+", portée : "+getRange()+", element : "+getElement()+", cout : "+cout;
    }

    /**
     * permet d'afficher à l'ecran la tour avec son nombre de vie etc.
     * @param x sa coordonnée x sur le canvas.
     * @param y sa coordonnée y sur le canvas.
     * @param couleur sa couleur, en fonction de son element.
     * @param rayon sa taille.
     */
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

    /**
     * permet d'afficher la tour dans le magasin sans sa barre de vie.
     * @param x sa coordonnée x sur le canvas.
     * @param y sa coordonnée y sur le canvas.
     * @param rayon sa taille.
     */
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
