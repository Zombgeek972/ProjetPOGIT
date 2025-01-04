/**
 * classe permettant de lancer le jeu.
 */
public class App {
    /**
     * je dois créer le constructeur pour satisfaire la javadoc.
     */
    public App() {
    }

    /**
     * la methode dans laquelle on lance le jeu.
     * @param args je ne sais pas ce que c'est, il faut que j'écrive pour que la javadoc soit contente.
     */
    public static void main (String[] args){
        Game g = new Game() ;
        g.launch();
    }
}
