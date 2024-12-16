public class Game{
    Carte carte;
    Joueur joueur;
    Ennemis ennemis;
    Cell ancienneCell;
    Cell cellule;

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
        //TODO pas fini
        return joueur.getHp() > 0;
    }

    private void init (){
        //création du canvas
        StdDraw.setCanvasSize(1024 , 720) ;
        StdDraw.setXscale( -12 , 1012) ;
        StdDraw.setYscale( -10 , 710) ;
        StdDraw.enableDoubleBuffering () ;

        //chargement de toutes les zones du canvas
        //chargement de la carte
        carte = new Carte("5-8.mtp");
        carte.draw();

        //chargement du joueur
        joueur = new Joueur();
        joueur.draw();

        //chargement des ennemis
        int x = carte.getSpawn().getCenterX();
        int y = carte.getSpawn().getCenterY();
        ennemis = new Ennemis(10, 5, 3.5, 2.0, Element.Air, 1, 50, "5-8.mtp", carte.getChemin(), carte.getCarte(), x, y);
        //affichage de l'ennemi dans la cellule de spawn
        ennemis.draw();

        //affichage
        StdDraw.show();
    }

    Tours t = new TourArcher("none", "kakou kakou");

    private void update ( double deltaTimeSec ){

        double cooSourisX = StdDraw.mouseX();
        double cooSourisY = StdDraw.mouseY();
        //cellule = getCellCoo(cooSourisX, cooSourisY);

        //si il y a une ancienne cellule d'enregistrée et que la cellule survolée par la souris est différente
        /*if (ancienneCell != null && cellule != ancienneCell) {
            ancienneCell.setColorBorderBlack();
            ancienneCell.draw();
        }*/

        //si on survole la carte
        /*if (cellule != null) {
            cellule.setColorBorderWhite();

            if (StdDraw.isMousePressed()) {
                if (cellule.getChar() == 'C') {
                    cellule.setTour(t);
                }
            }
            ancienneCell = cellule;
            cellule.draw();
            StdDraw.show();
        }*/
        //si on sort de la carte on remet le bord de la derniere cellule en noir
        /*else if (ancienneCell != null) {
            ancienneCell.setColorBorderBlack();
            ancienneCell.draw();
            StdDraw.show();
            ancienneCell = null;
        }*/

        if (StdDraw.isMousePressed()) {
            cellule = getCellCoo(cooSourisX, cooSourisY);
            if (cellule != null) {
                if (cellule.getChar() == 'C') {
                    cellule.setTour(t);
                    cellule.draw();
                    StdDraw.show();
                }
            }
        }
        ennemis.avanceBas();
        ennemis.draw();
        StdDraw.show();
    }

    /**
     * 
     * @param x coordonnées x de la souris
     * @param y coordonnées y de la souris
     * @return la cellule présente aux coordonnées x,y
     */
    public Cell getCellCoo(double x, double y) {
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
