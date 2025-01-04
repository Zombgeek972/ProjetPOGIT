/**
 * Une petite classe permettant d'enregistrer un tuple de données.
 * @param <U> le type du premier element
 * @param <V> le type du deuxieme element
 */
public class Pair<U, V> {
    private U elt1;
    private V elt2;

    /**
     * le constructeur permettant de créer le tuple.
     * @param elt1 le premier element du tuple.
     * @param elt2 le deuxieme element du tuple.
     */
    public Pair(U elt1, V elt2) {
        this.elt1 = elt1;
        this.elt2 = elt2;
    }

    /**
     * permet de récuperer le premier element du tuple.
     * @return le premier element.
     */
    public U getElt1() {
        return elt1;
    }

    /**
     * permet de récuperer le deuxième element du tuple.
     * @return le deuxième element.
     */
    public V getElt2() {
        return elt2;
    }

    public String toString() {
        return "elt1:"+ elt1.toString() +" => elt2:"+elt2.toString();
    }
    
}
