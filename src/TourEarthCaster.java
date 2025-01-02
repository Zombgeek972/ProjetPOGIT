import java.awt.Color;
import java.util.List;

/**
 * Une tour Earth Caster vise l’ennemi qui se trouve à sa portée ayant le plus grand nombre de PV. Les ennemis à moins de 1.0 case de la cible subissent les mêmes dommages que sa cible.
 */
public class TourEarthCaster extends Tours {

    public TourEarthCaster() {
        super(50, 7, 0.5, 2.5, Element.Terre, 100, new Color(139, 69, 19));
    }
    
    public void draw(double x, double y, double rayon) {
        super.draw(x, y, getCouleur(), rayon);
    }

    @Override
    void envoieDegats(List<Combattant> lst, int halfLengthCell) {
        Ennemis maxPv = null;

        List<Ennemis> nvLst = lst.stream().map(e -> (Ennemis) e).toList();

        int indice = 0;
        for (int i = 0; i<nvLst.size(); i++) {
            Ennemis e = nvLst.get(i);

            if (this.distance(e) <= this.getRange() * halfLengthCell * 2) {
                if (maxPv == null) {
                    maxPv = e;
                    indice = i;
                }
                else {
                    if (e.getPv() > maxPv.getPv()) {
                        maxPv = e;
                        indice = i;
                    }
                }
            }
        }
        
        if (maxPv != null) {
            maxPv.enlevePV(this.getAtk() * maxPv.vulnerabitite(this));

            // on regarde les énnemis apres celui selectionné, on regarde si ils sont à moins de 1 case
            for (int i = indice; i<lst.size(); i++) {
                Ennemis e = nvLst.get(i);
                if (maxPv.distance(e) < halfLengthCell * 2) {
                    e.enlevePV(this.getAtk() * e.vulnerabitite(this));
                }
            }

            // pareil pour ceux avant celui selectionné
            for (int i = indice; i >= 0; i--) {
                Ennemis e = nvLst.get(i);
                if (maxPv.distance(e) < halfLengthCell * 2) {
                    e.enlevePV(this.getAtk() * e.vulnerabitite(this));
                }
            }
        }
    }

    @Override
    public Tours nouvelleInstance() {
        return new TourEarthCaster();
    }
}
