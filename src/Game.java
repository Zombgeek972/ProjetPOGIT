public class Game{
    Carte carte;

    public Game(){
    }

    public void launch(){
        init();
        long previousTime = System.currentTimeMillis();
        while ( isGameRunning () ){
            long currentTime = System . currentTimeMillis () ;
            double deltaTimeSec = ( double )( currentTime - previousTime ) /1000;
            previousTime = currentTime ;
            update ( deltaTimeSec );
        }
    }

    private boolean isGameRunning (){
        //TODO
        return true;
    }

    private void init (){
        //création du canvas
        StdDraw.setCanvasSize(1024 , 720) ;
        StdDraw.setXscale( -12 , 1012) ;
        StdDraw.setYscale( -10 , 710) ;
        StdDraw.enableDoubleBuffering () ;

        //chargement de toutes les zones du canvas
        carte = new Carte("5-8.mtp");
        carte.draw();

        Joueur joueur = new Joueur();
        joueur.draw();
        Ennemis ennemi = new Ennemis(10, 5, 3.5, 2.0, Element.Air, 8, 50, "5-8.mtp", carte.getChemin(), carte.getCarte());
        ennemi.draw();
        StdDraw.show();
    }

    private void update ( double deltaTimeSec ){
        Tours t = new TourArcher("none", "kakou kakou");

        double cooSourisX = StdDraw.mouseX();
        double cooSourisY = StdDraw.mouseY();
        if (StdDraw.isMousePressed()) {
            Cell cellule = cellClicked(cooSourisX, cooSourisY);
            if (cellule.getChar() == 'C') {
                t.draw(cellule.getCenterX(), cellule.getCenterY(), cellule.getHalfLength());
                StdDraw.show();
            }
        }
    }

    /**
     * 
     * @param x coordonnées x de la souris
     * @param y coordonnées y de la souris
     * @return la cellule sur laquelle on a cliqué
     */
    public Cell cellClicked(double x, double y) {
        for (int i=0; i < carte.getCarte().length; i++) {
            for (int j=0; j < carte.getCarte()[i].length; j++) {
                if (carte.getCarte()[i][j].isMouseOn(x, y)) {
                    return carte.getCarte()[i][j];
                }
            }
        }
        return null;
    }
}
