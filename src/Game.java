import java.util.ArrayList;
import java.util.List;

/**
 * cette classe permet de faire tous les calculs à chaque image pour savoir quoi faire, afficher...
 */
public class Game {
    private Carte carte;
    private Joueur joueur;
    private Progression progression;
    private Magasin magasin;
    private List<Pair<Double, Ennemis>> vagueCourante;
    private double time;
    private List<Tours> lstTours;
    private boolean gagne;
    private Tours tourSelect;

    /**
     * constructeur de la classe Game, on initialise juste la liste qui va accueillir les tours que le joueur va placer sur la carte.
     */
    public Game(){
        lstTours = new ArrayList<>();
    }

    /**
     * methode permettant d'initialiser, de gerer les calculs et de dire si on a gagné ou perdu le jeu. Un simple message dans la console permet de le savoir.
     */
    public void launch(){
        init();
        long previousTime = System.currentTimeMillis();
        while ( isGameRunning () ){
            long currentTime = System . currentTimeMillis () ;
            double deltaTimeSec = ( double )( currentTime - previousTime ) /1000;
            previousTime = currentTime ;
            update ( deltaTimeSec );
        }
        if (joueur.getHp() == 0) {
            System.out.println("Perdu !");
        }
        else {
            System.out.println("Gagné !");
        }
    }

    private boolean isGameRunning (){
        return joueur.getHp() > 0 && !gagne;
    }

    private void init (){
        // création du canvas
        StdDraw.setCanvasSize(1024 , 720) ;
        StdDraw.setXscale( -12 , 1012) ;
        StdDraw.setYscale( -10 , 710) ;
        StdDraw.enableDoubleBuffering () ;

        // chargement de toutes les zones du canvas
        StdDraw.rectangle(350, 350, 350, 350); //zone map
        StdDraw.rectangle(856, 688, 144, 12); //zone level
        StdDraw.rectangle(856, 641, 144, 25); //zone joueur
        StdDraw.rectangle(856, 303, 144, 303);//zone magasin
        
        // création de la structure des vagues d'ennemis
        progression = new Progression();
        progression.nouvelleVague();
        vagueCourante = progression.getVague();

        // chargement de la carte
        carte = new Carte(progression.getCarte());
        carte.draw();

        // chargement du joueur
        joueur = new Joueur();
        joueur.draw();

        // chargement du magasin
        magasin = new Magasin();
        magasin.draw();

        // affichage
        StdDraw.show();
    }

    private void update ( double deltaTimeSec ) {
        time += deltaTimeSec;

        if (StdDraw.isMousePressed()) {
            double cooSourisX = StdDraw.mouseX();
            double cooSourisY = StdDraw.mouseY();

            if (magasin.clicked(cooSourisX, cooSourisY)) {
                tourSelect = magasin.getTourClicked(cooSourisX, cooSourisY).nouvelleInstance();
            }

            if (carte.clicked(cooSourisX, cooSourisY)) {
                if (tourSelect != null) {
                    Cell cellule = carte.getCell(cooSourisX, cooSourisY);
                    if (cellule.getChar() == 'C') {
                        CellConstructible celluleCastee = (CellConstructible) cellule;
                        if (celluleCastee.getTour() == null && joueur.getMoney()-tourSelect.getCout() >= 0) {
                            lstTours.add(tourSelect);
                            celluleCastee.setTour(tourSelect);
                            joueur.enleveMonnaie(tourSelect.getCout());
                            tourSelect = null;
                        }
                    }
                }
            }
        }

        carte.draw();

        // les tours attaquent
        for (int i = 0; i<lstTours.size(); i++) {
            Tours t = lstTours.get(i);
            t.addRechargement(deltaTimeSec);
            if (!t.enVie()) {
                CellConstructible c = (CellConstructible) carte.getCell(t.getPosition().getElt1(), t.getPosition().getElt2());
                c.setTour(null);
                lstTours.remove(i);
            }

            if (t.getRechargement() >= t.getAtkSpeed()) {
                t.envoieDegats(vagueCourante.stream()
                                            .map(p -> p.getElt2()) // récupere uniquement les énnemis, enlève leur moment de spawn.
                                            .filter(e -> e.getASpawn() && e.enVie() && e.getProgresse()) // garde que les ennemis "en forme".
                                            .map(e -> (Combattant) e)
                                            .toList(), carte.getChemin().getFirst().getHalfLength());
                
                t.resetRechargement();
            }
        }

        // les énnemis avancent et attaquent.
        for (Pair<Double, Ennemis> p : vagueCourante) {
            Ennemis ennemi = p.getElt2();

            if (!ennemi.getASpawn()) {
                if (time >= p.getElt1()) {
                    ennemi.setASpawn();
                    ennemi.setProgresse(true);
                    ennemi.setCarte(carte.getCarte());
                    ennemi.setChemin(carte.getChemin());
                    ennemi.setJoueur(joueur);
                    ennemi.setSpawn(carte.getChemin().get(0).getCenterX(), carte.getChemin().get(0).getCenterY());
                    ennemi.draw();
                }
            }
            else {
                if (ennemi.getProgresse()) {

                    if (!ennemi.enVie()) {
                        ennemi.setProgresse(false);
                        joueur.ajouteMonnaie(ennemi.getReward());
                    }

                    if (ennemi.arrive()) {
                        ennemi.setProgresse(false);
                        joueur.enleveVie(ennemi.getAtk());
                    }
                    else {
                        ennemi.avance(deltaTimeSec);
                        ennemi.addRechargement(deltaTimeSec);
                        if (ennemi.getRechargement() >= ennemi.getAtkSpeed() && ennemi.getAtkSpeed() > 0) {
                            ennemi.envoieDegats(lstTours.stream()
                                                        .map(e -> (Combattant) e)
                                                        .toList(), carte.getChemin().getFirst().getHalfLength());
                            ennemi.resetRechargement();
                        }
                        ennemi.draw();
                    }
                }
            }
        }

        progression.draw();
        joueur.draw();
        StdDraw.show();

        MAJvague();
    }

    /**
     * permet de savoir si le jeu est fini, si on a fini une vague et/ou un niveau. Change en conséquence ce qu'il faut.
     */
    private void MAJvague() {
        if (progression.jeuFini()) {
            gagne = true;
        }

        else if (progression.lvlFini()) {
            progression.nouvelleVague();
            vagueCourante = progression.getVague();
            carte = new Carte(progression.getCarte());
            time = 0;
            lstTours.clear();

            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledRectangle(350, 350, 350, 350);

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.rectangle(350, 350, 350, 350);
        }

        else if (progression.vagueFini()) {
            progression.nouvelleVague();
            vagueCourante = progression.getVague();
            time = 0;
        }
    }
}
