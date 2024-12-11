import java.util.HashMap;
import java.util.Map;
abstract class Combattant {
    private int pv;
    private int atk;
    private double atkSpeed;
    private double range;
    private Element element;

    public Combattant(int pv, int atk, double atkSpeed, double range, Element element) {
        this.pv = pv;
        this.atk = atk;
        this.atkSpeed = atkSpeed;
        this.range = range;
        this.element = element;
    }

    public int getPv() {
        return pv;
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

    public double vulnérabitité(Combattant c) {
        Map<Element, Integer> elts = new HashMap<>();
        elts.put(Element.Feu, 0);
        elts.put(Element.Eau, 1);
        elts.put(Element.Air, 2);
        elts.put(Element.Terre, 3);
        
        if (elts.get(this.element) == elts.get(c.element) || Math.abs(elts.get(this.element)-elts.get(c.element)) > 1) {
            return 1.0;
        }
        else if (elts.get(this.element) > elts.get(c.element) || this.element == Element.Feu && c.element == Element.Terre){
            return 0.5;
        }
        else {
            return 1.5;
        }

    }

    abstract void recoitDegats(Combattant c);
    
}
