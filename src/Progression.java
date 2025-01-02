import java.util.List;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * cette classe initialise et gere les niveaux et les vagues d'ennemis au cours du jeu.
 */
public class Progression {
    private int nvCourant;
    private int nvMax;
    private int wveCourante;
    private int wveMax;
    private Queue<Queue<List<Pair<Double, Ennemis>>>> levels;
    private Queue<List<Pair<Double, Ennemis>>> vagues;
    private List<Pair<Double, Ennemis>> ennemis;
    private Queue<String> cartes;
    private String carteActuelle;

    public Progression() {
        levels = new LinkedList<>();
        vagues = new LinkedList<>();
        ennemis = new LinkedList<>();
        cartes = new LinkedList<>();
        chargeJeu("game.g");
        init();
    }

    public List<Pair<Double, Ennemis>> getVague() {
        return ennemis;
    }

    /**
     * lis le fichier à l'emplacement ressources/dossier/nomFichier
     * @param dossier le dossier dans lequel se trouve le fichier qu'on veut charger
     * @param nomFichier le fichier voulu
     * @return une LinkedList ou la ligne i dans le fichier correspond à l'element i dans la LinkedList.
     */
    private LinkedList<String> lisFichier(String dossier, String nomFichier) {

        Path path = FileSystems.getDefault().getPath("ressources/"+dossier, nomFichier);
        Queue<String> fichier = new LinkedList<String>();

        //enregistrement du fichier dans la queue fileNiveaux
        try (BufferedReader readerer = Files.newBufferedReader(path)) {
            BufferedReader reader = Files.newBufferedReader(path);
            String line = readerer.readLine();

            while (((line = reader.readLine()) != null) ) {
                fichier.add(line);
            }
            reader.close();
            return (LinkedList<String>) fichier;
        } 
        catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    private void setCarte() {
        carteActuelle = cartes.remove();
    }
    public String getCarte() {
        return carteActuelle;
    }

    private void init() {
        setCarte();
        vagues = levels.remove();

        nvCourant = 1;
        wveCourante = 1;
        wveMax = vagues.size();

        ennemis = vagues.remove();
    }

    public boolean vagueFini() {
        for (Pair<Double,Ennemis> pair : ennemis) {
            if (pair.getElt2().getProgresse() || !pair.getElt2().getASpawn()) {
                return false;
            }
        }
        return true;
    }
    public boolean lvlFini() {
        return vagues.size() == 0 && vagueFini();
    }
    public boolean jeuFini() {
        return levels.size() == 0 && lvlFini();
    }

    /**
     * cette methode permet de charger la vague d'après si celle en cours est fini et affiche les niveaux et les vagues en fonction
     */
    public void nouvelleVague() {
        if (vagueFini()) {
            // on regarde si il reste des vagues dans le niveau courant
            if (vagues.size() != 0) {
                ennemis = vagues.remove();
                wveCourante++;
                this.draw();
            }
            // si il n'y en a plus
            else {
                //on regarde si il reste des levels
                if (levels.size() != 0) {
                    vagues = levels.remove();
                    this.setCarte();
                    wveCourante = 1;
                    wveMax = vagues.size();
                    ennemis = vagues.remove();
                    nvCourant++;
                    this.draw();
                }
            }
        }
    }

    /**
     * génere la structure du déroulement du jeu.
     */
    private void chargeJeu(String nomFichier) {
        // lecture du fichier game avec tous les niveaux.
        Queue<String> niveauxStr = lisFichier("games",nomFichier);

        // lecture des fichiers de niveaux avec toutes les vagues différentes.
        for (String niveau : niveauxStr) {
            nvMax++;

            Queue<String> vaguesStr = lisFichier("levels", niveau+".lvl");
            Queue<List<Pair<Double, Ennemis>>> vaguesProvisoire = new LinkedList<>();

            cartes.add(vaguesStr.remove());

            // lecture des fichiers wave avec tous les ennemis à apparaître.
            for (String vague : vaguesStr) {

                Queue<String> ennemisStr = new LinkedList<>();
                ennemisStr = lisFichier("waves", vague+".wve");

                List<Pair<Double, Ennemis>> ennemis = new ArrayList<>();

                for (String ennemi : ennemisStr) {

                    String[] splitted = ennemi.split("[|]");

                    switch (splitted[1]) {
                        case "Minion":
                            ennemis.add( new Pair<Double, Ennemis> (Double.parseDouble(splitted[0]), new EnneMinion()));
                            break;

                        case "Earth Brute":
                            ennemis.add( new Pair<Double, Ennemis> (Double.parseDouble(splitted[0]), new EnnemiEarthBrute()));
                            break;
                        
                        case "Fire Grognard":
                            ennemis.add( new Pair<Double, Ennemis> (Double.parseDouble(splitted[0]), new EnnemiFireGrognard()));
                            break;

                        case "Water Brute":
                            ennemis.add( new Pair<Double, Ennemis> (Double.parseDouble(splitted[0]), new EnnemiWaterBrute()));
                            break;
                        
                        case "Wind Grognard":
                                ennemis.add( new Pair<Double, Ennemis> (Double.parseDouble(splitted[0]), new EnnemiWindGrognard()));
                                break;
                        
                        case "Boss":
                            ennemis.add( new Pair<Double, Ennemis> (Double.parseDouble(splitted[0]), new EnnemiBoss()));
                            break;
                        
                        default:
                            break;
                    }
                }
                vaguesProvisoire.add(ennemis);
            }
            levels.add(vaguesProvisoire);
        }
    }

    public void draw() {
        Font font = new Font("Arial", Font.BOLD, 25);
        StdDraw.setFont(font);

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(760, 688, 47, 11);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(760, 685, "LVL:"+nvCourant+"/"+nvMax);

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(947, 688, 52, 11);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(950, 685, "WVE:"+wveCourante+"/"+wveMax);
    }
}
