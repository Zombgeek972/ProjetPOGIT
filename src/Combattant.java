import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
abstract class Combattant {

    private Pair<Integer, Integer> position;
    private double pv;
    private double maxPV;
    private int atk;
    private double atkSpeed;
    private double rechargement;
    private double range;
    private Element element;
    private Color couleur;
    private Joueur joueur;

    public Combattant(double pv, int atk, double atkSpeed, double range, Element element, Color couleur) {
        this.pv = pv;
        this.maxPV = pv;
        this.atk = atk;
        this.atkSpeed = atkSpeed;
        this.range = range;
        this.element = element;
        this.couleur = couleur;
    }

    /**
     * permet de savoir combien de vie il reste à l'entité.
     * @return le nombre de PV de l'entité.
     */
    public double getPv() {
        return pv;
    }

    /**
     * permet de savoir quel est le nombre de points de vie max que l'entité a.
     * @return le nombre de PV maximum.
     */
    public double getMaxPv() {
        return maxPV;
    }

    /**
     * supprime des points de vie au joueur si un ennemi est arrivé a la base.
     * @param degats le nombre de points de vie à enlever, 0 si (PV-degats) est inférieur à 0.
     */
    public void enlevePV(double degats) {
        if (pv-degats < 0) {
            pv = 0;
        }
        else {
            pv -= degats;
        }
    }

    /**
     * permet de savoir si l'entité est en vie ou non
     * @return true si l'entité est en vie, false sinon.
     */
    public boolean enVie() {
        return pv > 0;
    }

    /**
     * permet de savoir le nombre de dégats que fait l'entité.
     * @return la quantité d'attaque infligée à un ennemi à chaque coup.
     */
    public int getAtk() {
        return atk;
    }

    /**
     * permet de savoir la vitesse à laquelle l'entité peut frapper.
     * @return le temps entre chaque coup en secondes.
     */
    public double getAtkSpeed() {
        return atkSpeed;
    }

    /**
     * permet de savoir jusqu'a quelle distance peut taper une entité.
     * @return la distance en nombre de cellules.
     */
    public double getRange() {
        return range;
    }

    /**
     * permet de savoir à quel element appartient l"entité. Pratique pour calculer le coefficient par lequel multiplier les dégats.
     * @return l'élément de l'entité.
     */
    public Element getElement() {
        return element;
    }

    /**
     * permet de savoir les infos du joueur comme son nombre de points de vieou son nombre de pieces.
     * @return le joueur instancié pour la partie en cours.
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * permet d'enregistrer le joueur de la partie en cours.
     * @param joueur le joueur ayant les données de vie et de monnaie de la partie en cours.
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    /**
     * permet de savoir de quelle vouleur est l'entitée courante.
     * @return la couleur de l'entité.
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * permet de recharger les entités combattantes avec deltaTimeSec calculé dans la methode update de "game.java".
     * @param r la quantité de temps à ajouter au rechargement.
     */
    public void addRechargement(double r) {
        rechargement += r;
    }

    /**
     * permet de remettre le rechargement à 0 si l'entité a attaqué.
     */
    public void resetRechargement() {
        rechargement = 0;
    }

    /**
     * permet de savoir où est rendu le rechargement de l'entité, si le rechargement est supérieur à la vitesse d'attaque, ça veut dire que l'entité peut attaquer.
     * @return le rechargement de l'entité en secondes.
     */
    public double getRechargement() {
        return rechargement;
    }

    /**
     * permet de savoir la position de l'entité
     * @return une paire contenant la position x et y sur le canvas.
     */
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    /**
     * permet d'enregistrer la position actuelle d'une entité.
     * @param position la position de l'entité.
     */
    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    abstract void envoieDegats(List<Combattant> lst, int halfLengthCell);

    /**
     * calcul le coefficient par lequel multiplier le nombre de degats
     * @param c un combattant Tours ou Ennemis
     * @return le coefficient de dégats à infliger à l'adversaire
     */
    public double vulnerabitite(Combattant c) {
        Map<Element, Integer> elts = new HashMap<>();
        elts.put(Element.Feu, 0);
        elts.put(Element.Eau, 1);
        elts.put(Element.Air, 2);
        elts.put(Element.Terre, 3);

        if (c instanceof Ennemis) {
            Ennemis combattant = (Ennemis) c;

            if (this.element == Element.Neutre || c.getElement() == Element.Neutre) {
                return 1.0;
            }

            if (elts.get(this.element) == elts.get(combattant.getElement()) || Math.abs(elts.get(this.element)-elts.get(combattant.getElement())) > 1) {
                return 1.0;
            }
            else if (elts.get(this.element) > elts.get(combattant.getElement()) || this.element == Element.Feu && combattant.getElement() == Element.Terre){
                return 0.5;
            }
            else {
                return 1.5;
            }
        }
        else {
            Tours combattant = (Tours) c;

            if (this.element == Element.Neutre || c.getElement() == Element.Neutre) {
                return 1;
            }
            if (elts.get(this.element) == elts.get(combattant.getElement()) ||
            Math.abs(elts.get(this.element)-elts.get(combattant.getElement())) > 1) {
                return 1.0;
            }
            else if (elts.get(this.element) > elts.get(combattant.getElement()) || this.element == Element.Feu && combattant.getElement() == Element.Terre){
                return 0.5;
            }
            else {
                return 1.5;
            }
        }

    }

    /**
     * distance euclidienne entre le combattant courant et celui mis en paramètre.
     * @param c un combattant.
     * @return la distance à laquelle se trouve l'entité avec laquelle on a appelé la fonction et celle mise en paramètre.
     */
    public Double distance(Combattant c) {
        if (this.getPosition() != null) {
            return Math.sqrt(Math.pow(this.getPosition().getElt1() - c.getPosition().getElt1(), 2) + Math.pow(this.getPosition().getElt2() - c.getPosition().getElt2(), 2));
        }
        return null;
    }
}
