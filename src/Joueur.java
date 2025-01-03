import java.awt.Color;
import java.awt.Font;

public class Joueur {
    private static int hp = 100;
    private static int money = 50;
    
    public Joueur() {
    }

    public int getHp() {
        return hp;
    }
    public int getMoney() {
        return money;
    }
    
    public void subitDegats(Ennemis e) {
        hp -= e.getAtk();
    }

    public boolean enVie() {
        return (hp > 0);
    }

    public void ajouteMonnaie(int montant) {
        money += montant;
    }
    public void enleveMonnaie(int montant) {
        money -= montant;
    }

    public void ajouteVie(int quantité) {
        hp += quantité;
    }
    public void enleveVie(int quantite) {
        if (hp - quantite >= 0) {
            hp -= quantite;
        }
        else {
            hp = 0;
        }
    }

    public void draw() {
        Font font = new Font("Arial", Font.BOLD, 25);
        StdDraw.setFont(font);

        //dimensions de la zone joueur
        int centerX = 856;
        int centerY = 641;
        int halfWidth = 144;
        int halfHeight = 25;

        //pièce
        int radius = 25;
        //permet d'ajuster la pièce a gauche du rectangle
        int decalagePiece = halfWidth-radius-5;

        //affichage de la piece
        StdDraw.setPenColor ( new Color (212, 175, 55) );
        StdDraw.filledCircle ( centerX - decalagePiece, centerY, radius );
        StdDraw.setPenColor ( new Color (192, 192, 192) );
        StdDraw.filledCircle ( centerX - decalagePiece, centerY, 0.7*radius );

        //affichage du nb de pieces
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(790, 644, 22, 12);
        StdDraw.setPenColor(new Color (212, 175, 55));
        StdDraw.text(790, 641, money+"");

        //coeur
        int decalageCoeur = halfWidth-30;

        StdDraw . setPenColor ( new Color (223 , 75 , 95) ) ;
        double [] listX = new double [] {
            centerX + decalageCoeur,
            centerX - halfHeight + decalageCoeur,
            centerX - halfHeight + decalageCoeur,
            centerX - 0.66 * halfHeight + decalageCoeur,
            centerX - 0.33 * halfHeight + decalageCoeur,
            centerX + decalageCoeur,
            centerX + 0.33 * halfHeight + decalageCoeur,
            centerX + 0.66 * halfHeight + decalageCoeur ,
            centerX + halfHeight + decalageCoeur ,
            centerX + halfHeight + decalageCoeur
        };
        double [] listY = new double [] {
            centerY - halfHeight ,
            centerY ,
            centerY + 0.5 * halfHeight ,
            centerY + halfHeight ,
            centerY + halfHeight,
            centerY + 0.5 * halfHeight ,
            centerY + halfHeight ,
            centerY + halfHeight ,
            centerY + 0.5 * halfHeight ,
            centerY
        };
        //affichage du coeur
        StdDraw . filledPolygon ( listX , listY ) ;

        //affichage du nombre de coeur
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(centerX+decalageCoeur-50, centerY+3, 22, 12);
        StdDraw.setPenColor(new Color (223 , 75 , 95));
        StdDraw.text(centerX+decalageCoeur-50, centerY, hp+"");
    }
}
