import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Dans StdDraw, les coordonnées x:0,y:0 du canvas sont en bas à gauche:
 * (x:0,y:100)                  (x:100,y:100)
 *      |
 *      |
 *      |
 *      |
 *      |
 *      |
 *      --------------------
 * (x:0,y:0)                    (x:100,y:0)
 */

 /**
  * La classe Carte génere et affiche la carte du jeu à partir du fichier donné en paramètre
  */
public class Carte {
    private Cell[][] quadrillage;
    private boolean[][] quadrillageB;
    private List<Cell> chemin;
    private Cell spawn;
    private Cell base;
    private int halfLength;

    /**
     * constructeur de la classe Carte, permet d'initialiser et de dessiner la carte, le chemin et les coordonnées des cellules.
     * @param nomFichier nom du fichier de carte que la classe doit instancier
     */
    public Carte(String nomFichier) {
        //récupération du fichier mis en paramètre
        Path path = FileSystems.getDefault().getPath("ressources/maps", nomFichier+".mtp");
        Queue<String> queue = new LinkedList<String>();
        //enregistrement du fichier dans une queue contenant toutes les lignes
        try (BufferedReader readerer = Files.newBufferedReader(path)) {
            BufferedReader reader = Files.newBufferedReader(path);
            String line = readerer.readLine();

            while (((line = reader.readLine()) != null) ) {
                queue.add(line);
            }
            reader.close();
        } 
        catch (IOException e) {
            System.out.println(e);
        }

        //on suppose que toutes les lignes du fichier font la même taille

        //création d'un tableau de booleen aux dimensions du cichier qui va nous servir pour calculer le chemin
        this.quadrillageB = new boolean[queue.size()][queue.peek().length()];

        //création et remplissage du tableau quadrillage grâce au fichier enregistré dans la queue
        quadrillage = new Cell[queue.size()][queue.peek().length()];

        int i = 0;
        while (!queue.isEmpty()) {
            String line = queue.remove();
            for(int j=0; j<line.length(); j++) {
                if (line.charAt(j) =='S') {
                    Cell celluleSpawn = new CellSpawn(i, j);
                    quadrillage[i][j] = celluleSpawn;
                }
                else if (line.charAt(j) == 'B'){
                    Cell celluleBase = new CellBase(i, j);
                    quadrillage[i][j] = celluleBase;
                }
                else if (line.charAt(j)=='R') {
                    Cell celluleRoad = new CellRoad(i, j);
                    quadrillage[i][j] = celluleRoad;
                }
                else if (line.charAt(j)=='C') {
                    Cell celluleConstructible = new CellConstructible(i, j);
                    quadrillage[i][j] = celluleConstructible;
                }
                else if (line.charAt(j)=='X') {
                    Cell celluleBorder = new CellBorder(i, j);
                    quadrillage[i][j] = celluleBorder;
                }
            }
            i++;
        }

        this.spawn = getPosCell('S');
        this.base = getPosCell('B');

        calculChemin();
        calulPosCell();
    }

    private List<Cell> calculChemin() {

        chemin = new LinkedList<>();
        chemin.add(spawn);

        Cell posCourante = spawn;
        Cell nvPosCourante = null;

        /*
        * Je parcours la liste par rapport à ses coordonnées donc l'origine est en haut à gauche
        * et on descend pour incrémenter y, on va vers la gauche pour incrémenter x.
        */
        while ((posCourante.getI() != base.getI()) || (posCourante.getJ() != base.getJ())) {
            posCourante = chemin.get(chemin.size()-1);
            //indication de l'emplacement des cases comparées autour de la case courante
            //bas
            if ((quadrillage[posCourante.getI()+1][posCourante.getJ()].getChar() == 'R' || quadrillage[posCourante.getI()+1][posCourante.getJ()].getChar() == 'B') && (!quadrillageB[posCourante.getI()+1][posCourante.getJ()])) {
                nvPosCourante = quadrillage[posCourante.getI()+1][posCourante.getJ()];
                chemin.add(nvPosCourante);
                quadrillageB[nvPosCourante.getI()][nvPosCourante.getJ()] = true;
            }
            //droite
            if ((quadrillage[posCourante.getI()][posCourante.getJ()+1].getChar() == 'R' || quadrillage[posCourante.getI()][posCourante.getJ()+1].getChar() == 'B') && (!quadrillageB[posCourante.getI()][posCourante.getJ()+1])) {
                nvPosCourante = quadrillage[posCourante.getI()][posCourante.getJ()+1];
                chemin.add(nvPosCourante);
                quadrillageB[nvPosCourante.getI()][nvPosCourante.getJ()] = true;
            }
            //haut
            if ((quadrillage[posCourante.getI()-1][posCourante.getJ()].getChar() == 'R' || quadrillage[posCourante.getI()-1][posCourante.getJ()].getChar() == 'B') && (!quadrillageB[posCourante.getI()-1][posCourante.getJ()])) {
                nvPosCourante = quadrillage[posCourante.getI()-1][posCourante.getJ()];
                chemin.add(nvPosCourante);
                quadrillageB[nvPosCourante.getI()][nvPosCourante.getJ()] = true;
            }
            //gauche
            if ((quadrillage[posCourante.getI()][posCourante.getJ()-1].getChar() == 'R' || quadrillage[posCourante.getI()][posCourante.getJ()-1].getChar() == 'B') && (!quadrillageB[posCourante.getI()][posCourante.getJ()-1])) {
                nvPosCourante = quadrillage[posCourante.getI()][posCourante.getJ()-1];
                chemin.add(nvPosCourante);
                quadrillageB[nvPosCourante.getI()][nvPosCourante.getJ()] = true;
            }
        }
        return chemin;
    }

    private void calulPosCell() {
        //dimensions de la zone carte
        int rectX = 350;
        int rectY = 350;

        //calcul de la taille des cellules
        int maxLength = Math.max(quadrillage.length, quadrillage[0].length); //compare hauteur et longueur de la carte
        int ecart = Math.min(2*rectX/maxLength, 2*rectY/maxLength); //ecart entre le centre de 2 cases

        halfLength = ecart/2;
        int centerX = halfLength;
        int centerY = halfLength;

        //enregistrement des coordonnées des cellules sur le canvas StdDraw
        for (int i=0; i<quadrillage.length; i++) {
            int hauteur = quadrillage.length-1;
            for (int j=0; j<quadrillage[i].length; j++) {
                //enregistrement
                quadrillage[hauteur-i][j].setCenterX(centerX);
                quadrillage[hauteur-i][j].setCenterY(centerY);
                quadrillage[hauteur-i][j].setHalfLength(halfLength);

                centerX += ecart;
            }
            centerX = halfLength;
            centerY += ecart;
        }
    }

    /**
     * permet de récuperer la carte sur laquelle les énnemis se déplacent.
     * @return un tableau de tableau de cellules correspondant à la carte.
     */
    public Cell[][] getCarte() {
        return quadrillage;
    }
    /**
     * permet de récuperer le chemin que doivent parcourir les énnemis.
     * @return une liste de cellles, celle du chemin.
     */
    public List<Cell> getChemin() {
        return chemin;
    }
    /**
     * permet de récuperer la cellule de spawn des ennemis.
     * @return la cellule de spawn des énnemis.
     */
    public Cell getSpawn() {
        return spawn;
    }
    /**
     * permet de récuperer la base du joueur, la ou les énnemis déspawn.
     * @return la cellule de la base du joueur.
     */
    public Cell getBase() {
        return base;
    }

    /**
     * permet de savoir si on a cliqué sur la carte ou non
     * @param x coordonnées x de la souris
     * @param y coordonnées y de la souris
     * @return true si on a cliqué sur la carte, false sinon.
     */
    public boolean clicked(double x, double y) {
        if (0 < x && x < 700) {
            if (0 < y && y < 700) {
                return true;
            }
            return false;
        }
        return false;
    }
    /**
     * permet de récuperer la cellule présente aux coordonnées x,y, null si il n'y en a pas aux coordonnées.
     * @param x coordonnées x de la souris
     * @param y coordonnées y de la souris
     * @return la cellule présente aux coordonnées x,y
     */
    public Cell getCell(double x, double y) {
        for (int i=0; i < quadrillage.length; i++) {
            for (int j=0; j < quadrillage[i].length; j++) {
                if (quadrillage[i][j].isMouseOn(x, y)) {
                    return quadrillage[i][j];
                }
            }
        }
        return null;
    }

    /**
     * dessine la carte sur le canvas
     */
    public void draw() {
        for (int i=0; i<quadrillage.length; i++) {
            int hauteur = quadrillage.length-1;
            for (int j=0; j<quadrillage[i].length; j++) {
                quadrillage[hauteur-i][j].draw();
            }
        }
    }

    /**
     * Cette fonction sert seulement à trouver la case de spawn des ennemis et la case de la base du joueur dans la carte.
     * @param c le caractere de la case recherchée.
     * @return la position(dans le tableau) de la case.
     */
    private Cell getPosCell(char c) {
        for(int i=0; i<quadrillage.length; i++) {
            for(int j=0; j<quadrillage[i].length; j++) {
                if (quadrillage[i][j].getChar() == c) {
                    return quadrillage[i][j];
                }
            }
        }
        return null;
    }

    public String toString() {
        String map = "";
        for (int i = 0; i < quadrillage.length; i++) {
            for (int j = 0; j < quadrillage[i].length; j++) {
                map += quadrillage[i][j].toString();
            }
            map+= "\n";
        } 
        return map;
    }
}
