import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import libraries.StdDraw;

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
public class Carte extends Niveau{
    private List<List<Cell>> quadrillage;
    private List<List<Boolean>> quadrillageB;
    private List<Position<Integer,Integer>> chemin;

    public Carte(String nomFichier) {
        quadrillage = new ArrayList<>();
        quadrillageB = new ArrayList<>();

        //création de la carte et de la carte booléenne
        Path cheminDuFichier = Path.of("ressources\\maps\\"+nomFichier);

        try (BufferedReader readerer = Files.newBufferedReader(cheminDuFichier)) {
            String line = readerer.readLine();

            BufferedReader reader = Files.newBufferedReader(cheminDuFichier);

            while (((line = reader.readLine()) != null) ) {
                List<Cell >ligneLST = new ArrayList<>();
                List<Boolean> ligneB = new ArrayList<>();
                for (int i=0; i<line.length(); i++) {
                    if (line.charAt(i) =='S') {
                        Cell celluleSpawn = new CellSpawn(0,0,0);
                        ligneLST.add(celluleSpawn);
                        ligneB.add(false);
                    }
                    else if (line.charAt(i) == 'B'){
                        Cell celluleBase = new CellBase(0,0,0);
                        ligneLST.add(celluleBase);
                        ligneB.add(false);
                    }
                    else if (line.charAt(i)=='R') {
                        Cell celluleRoad = new CellRoad(0,0,0);
                        ligneLST.add(celluleRoad);
                        ligneB.add(false);
                    }
                    else if (line.charAt(i)=='C') {
                        Cell celluleConstructible = new CellConstructible(0,0,0);
                        ligneLST.add(celluleConstructible);
                        ligneB.add(false);
                    }
                    else if (line.charAt(i)=='X') {
                        Cell celluleBorder = new CellBorder(0,0,0);
                        ligneLST.add(celluleBorder);
                        ligneB.add(false);
                    }
                }
                quadrillage.add(ligneLST);
                quadrillageB.add(ligneB);
            }
            reader.close();
        } 
        catch (IOException e) {
            System.out.println(e);
        }

        //calcul du chemin
        chemin = new ArrayList<>();
        Position<Integer,Integer> spawn = getPosCell('S');
        chemin.add(spawn);

        Position<Integer,Integer> base = getPosCell('B');

        Position<Integer,Integer> posCourante = getPosCell('S');

        /*
         * Je parcours la liste par rapport à ses coordonnées donc l'origine est en haut à gauche
         * et on descend pour incrémenter y, on va vers la gauche pour incrémenter x.
         */
        while ((posCourante.getX() != base.getX()) || (posCourante.getY() != base.getY())) {
            posCourante = chemin.get(chemin.size()-1);
            //indication de l'emplacement des cases comparées autout de la case courante
            //bas
            if ((quadrillage.get(posCourante.getY()+1).get(posCourante.getX()).getChar() == 'R' || quadrillage.get(posCourante.getY()+1).get(posCourante.getX()).getChar() == 'B') && (!quadrillageB.get(posCourante.getY()+1).get(posCourante.getX()))) {
                Position<Integer,Integer> nvPosCourante = new Position<>(posCourante.getX(),posCourante.getY()+1);
                chemin.add(nvPosCourante);
                quadrillageB.get(nvPosCourante.getY()).set(nvPosCourante.getX(),true);
            }
            //droit
            if ((quadrillage.get(posCourante.getY()).get(posCourante.getX()+1).getChar() == 'R' || quadrillage.get(posCourante.getY()).get(posCourante.getX()+1).getChar() == 'B') && (!quadrillageB.get(posCourante.getY()).get(posCourante.getX()+1))) {
                Position<Integer,Integer> nvPosCourante = new Position<>(posCourante.getX()+1,posCourante.getY());
                chemin.add(nvPosCourante);
                quadrillageB.get(nvPosCourante.getY()).set(nvPosCourante.getX(),true);
            }
            //haut
            if ((quadrillage.get(posCourante.getY()-1).get(posCourante.getX()).getChar() == 'R' || quadrillage.get(posCourante.getY()-1).get(posCourante.getX()).getChar() == 'B') && (!quadrillageB.get(posCourante.getY()-1).get(posCourante.getX()))) {
                Position<Integer,Integer> nvPosCourante = new Position<>(posCourante.getX(),posCourante.getY()-1);
                chemin.add(nvPosCourante);
                quadrillageB.get(nvPosCourante.getY()).set(nvPosCourante.getX(),true);
            }
            //gauche
            if ((quadrillage.get(posCourante.getY()).get(posCourante.getX()-1).getChar() == 'R' || quadrillage.get(posCourante.getY()).get(posCourante.getX()-1).getChar() == 'B') && (!quadrillageB.get(posCourante.getY()).get(posCourante.getX()-1))) {
                Position<Integer,Integer> nvPosCourante = new Position<>(posCourante.getX()-1,posCourante.getY());
                chemin.add(nvPosCourante);
                quadrillageB.get(nvPosCourante.getY()).set(nvPosCourante.getX(),true);
            }
        }
    }

    public List<List<Cell>> getCarte() {
        return quadrillage;
    }

    public List<Position<Integer,Integer>> getChemin() {
        return chemin;
    }

    /**
     * dessine la carte sur le canvas
     */
    public void draw() {
        //Zone de la carte
        StdDraw.setPenColor(StdDraw.BLACK);

        //dimensions de la zone
        int rectX = 350;
        int rectY = 350;
        int rectHalfWidth = 350;
        int rectHalfHeight = 350;
        StdDraw.rectangle(rectX, rectY, rectHalfWidth, rectHalfHeight);

        //calcul de la taille des cellules
        int maxLength = Math.max(quadrillage.size(), quadrillage.get(0).size()); //compare hauteur et longueur de la carte
        int ecart = Math.min(2*rectX/maxLength, 2*rectY/maxLength); //ecart entre le centre de 2 cases

        int halfLength = ecart/2;
        int centerX = halfLength;
        int centerY = halfLength;

        //affichage
        for (int i=0; i<quadrillage.size(); i++) {
            int hauteur = quadrillage.size()-1;
            for (int j=0; j<quadrillage.get(i).size(); j++) {
                quadrillage.get(hauteur-i).get(j).setCenterX(centerX);
                quadrillage.get(hauteur-i).get(j).setCenterY(centerY);
                quadrillage.get(hauteur-i).get(j).setHalfLength(halfLength);

                quadrillage.get(hauteur-i).get(j).draw();
                centerX += ecart;
            }
            centerX = halfLength;
            centerY += ecart;
        }
    }

    /**
     * Cette fonction sert seulement à trouver la case de spawn des ennemis et la case de la base du joueur dans la carte.
     * j'inverse les valeurs i et j car comme dit dans le constructeur pour créer le chemin, j = coordonnées x et i = coordonnées y.
     * @param c le caractere de la case recherchée.
     * @return la position(dans la liste) de la case.
     */
    private Position<Integer,Integer> getPosCell(char c) {
        for(int i=0; i<quadrillage.size(); i++) {
            for(int j=0; j<quadrillage.get(i).size(); j++) {
                if (quadrillage.get(i).get(j).getChar() == c) {
                    return new Position<Integer,Integer>(j, i);
                }
            }
        }
        return null;
    }

    public String toString() {
        String map = "";
        for (int i = 0; i < quadrillage.size(); i++) {
            for (int j = 0; j < quadrillage.get(i).size(); j++) {
                map += quadrillage.get(i).get(j).toString();
            }
            map+= "\n";
        } 
        return map;
    }
}
