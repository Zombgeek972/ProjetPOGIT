import java.awt.Font;
import java.util.LinkedList;
import java.util.List;


/**
 * La classe magasin permet de gerer les achats de tours au fil du jeu
 */
public class Magasin {
    List<Tours> lstTours;
    List<Pair<Integer, Integer>> centreCases;
    Pair<Integer, Integer> halfDistXY;

    public Magasin() {
        lstTours = new LinkedList<>();

        centreCases = new LinkedList<>();
        halfDistXY = new Pair<Integer,Integer>(72, 35);

        lstTours.add(new TourArcher());
        centreCases.add(new Pair<Integer,Integer>(856 - halfDistXY.getElt1(), 606 - halfDistXY.getElt2()));

        lstTours.add(new TourEarthCaster());
        centreCases.add(new Pair<Integer,Integer>(856 + halfDistXY.getElt1(), 606 - halfDistXY.getElt2()));

        lstTours.add(new TourFireCaster());
        centreCases.add(new Pair<Integer,Integer>(856 - halfDistXY.getElt1(), 606 - halfDistXY.getElt2() * 3));

        lstTours.add(new TourWaterCaster());
        centreCases.add(new Pair<Integer,Integer>(856 + halfDistXY.getElt1(), 606 - halfDistXY.getElt2() * 3));

        lstTours.add(new TourWindCaster());
        centreCases.add(new Pair<Integer,Integer>(856 - halfDistXY.getElt1(), 606 - halfDistXY.getElt2() * 5));
    }

    public Tours getTourClicked(double x, double y) {
        for (int i = 0; i<centreCases.size(); i++) {
            Pair<Integer, Integer> p = centreCases.get(i);
            if (p.getElt1() - halfDistXY.getElt1() < x && x < p.getElt1() + halfDistXY.getElt1()) {
                if (p.getElt2() - halfDistXY.getElt2() < y && y < p.getElt2() + halfDistXY.getElt2()) {
                    return lstTours.get(i);
                }
            }
        }
        return null;
    }

    public boolean clicked(double cooX, double cooY) {
        if (712 < cooX && cooX < 1000) {
            if (0 < cooY && cooY < 606) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        Font f = new Font("Arial", 0, 12);
        StdDraw.setFont(f);

        for (int i = 0; i<centreCases.size(); i++) {
            // affichage du rectangle qui délimite chaque zone
            Pair<Integer, Integer> p = centreCases.get(i);
            StdDraw.rectangle(p.getElt1(), p.getElt2(), halfDistXY.getElt1(), halfDistXY.getElt2());

            // affichage du skin
            Tours t = lstTours.get(i);
            t.drawSansBarreVie(p.getElt1() - halfDistXY.getElt1()+20, p.getElt2(), 15);

            // affichage des propriétés de la tour
            StdDraw.text(p.getElt1()+20, p.getElt2()+18, "PV:"+t.getPv()+" | ATK:"+t.getAtk());
            StdDraw.text(p.getElt1()+20, p.getElt2()+3, "speed:"+t.getAtkSpeed());
            StdDraw.text(p.getElt1()+20, p.getElt2()-12, "range:"+t.getRange());
            StdDraw.text(p.getElt1()+20, p.getElt2()-27, "coût:"+t.getCout());
        }
    }
}
