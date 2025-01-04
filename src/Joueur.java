import java.awt.Color;
import java.awt.Font;

/**
 * cette classe permet de gerer les calculs et l'affichage du nombre de pièces et de coeurs.
 */
public class Joueur {
    private static int hp = 100;
    private static int money = 50;
    
    /**
     * constructeur de Joueur.
     */
    public Joueur() {
    }

    /**
     * permet de récuperer le nombre de points de vie restants du joueur.
     * @return son nombre de points de vie.
     */
    public int getHp() {
        return hp;
    }

    /**
     * permet de récuperer le nombre de monnaie que le joueur a.
     * @return la quantité de monnaie que le joueur a.
     */
    public int getMoney() {
        return money;
    }

    /**
     * permet de savoir si le joueur est en vie ou non
     * @return true si le joueur est en vie, false sinon.
     */
    public boolean enVie() {
        return (hp > 0);
    }

    /**
     * permet d'ajouter de la monnaie au joueur pour pouvoir acheter des tours.
     * @param montant le nombre de pièces à ajouter.
     */
    public void ajouteMonnaie(int montant) {
        money += montant;
    }

    /**
     * permet d'enlever de la monnaie au joueur lorsqu'il achete une tour.
     * @param montant la quantité de monnaie à enlever.
     */
    public void enleveMonnaie(int montant) {
        money -= montant;
    }

    /**
     * permet d'ajouter de la vie au joueur si besoin.
     * @param quantite la quantité de vie à ajouter.
     */
    public void ajouteVie(int quantite) {
        hp += quantite;
    }

    /**
     * permet d'enlever de la vie au joueur lorsqu'un énnemi atteint la base du joueur.
     * @param quantite la quantité de vie à enlever au joueur. Si on est en dessous de 0 après avoir enlevé la vie, on met la quantité de vie à 0.
     */
    public void enleveVie(int quantite) {
        if (hp - quantite >= 0) {
            hp -= quantite;
        }
        else {
            hp = 0;
        }
    }

    /**
     * permet d'afficher le nombre de coeur et de pièces qu'a le joueur à l'appel de cette fonction.
     */
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
