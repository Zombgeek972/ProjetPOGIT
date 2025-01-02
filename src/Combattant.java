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

    public double getPv() {
        return pv;
    }
    public double getMaxPv() {
        return maxPV;
    }
    public void enlevePV(double degats) {
        if (pv-degats < 0) {
            pv = 0;
        }
        else {
            pv -= degats;
        }
    }
    public boolean enVie() {
        return pv > 0;
    }
    public int getAtk() {
        return atk;
    }
    public double getAtkSpeed() {
        return atkSpeed;
    }
    public double getRange() {
        return range;
    }
    public Element getElement() {
        return element;
    }
    public Joueur getJoueur() {
        return joueur;
    }
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    public Color getCouleur() {
        return couleur;
    }
    public void addRechargement(double r) {
        rechargement += r;
    }
    public void resetRechargement() {
        rechargement = 0;
    }
    public double getRechargement() {
        return rechargement;
    }
    public Pair<Integer, Integer> getPosition() {
        return position;
    }
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
     */
    public Double distance(Combattant c) {
        if (this.getPosition() != null) {
            return Math.sqrt(Math.pow(this.getPosition().getElt1() - c.getPosition().getElt1(), 2) + Math.pow(this.getPosition().getElt2() - c.getPosition().getElt2(), 2));
        }
        return null;
    }
}
