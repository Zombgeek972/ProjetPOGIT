public class Pair<U, V> {
    private U elt1;
    private V elt2;
    public Pair(U elt1, V elt2) {
        this.elt1 = elt1;
        this.elt2 = elt2;
    }
    public U getElt1() {
        return elt1;
    }
    public V getElt2() {
        return elt2;
    }

    public String toString() {
        return "elt1:"+ elt1.toString() +" => elt2:"+elt2.toString();
    }
    
}
