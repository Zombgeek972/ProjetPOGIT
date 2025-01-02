public class Position<U,V> {
    private U x;
    private V y;
    public Position(U x, V y) {
        this.x = x;
        this.y = y;
    }
    public U getX() {
        return x;
    }
    public void setX(U x) {
        this.x = x;
    }
    public V getY() {
        return y;
    }
    public void setY(V y) {
        this.y = y;
    }
    
    public String toString() {
        return "(x:"+x+", y:"+y+")";
    }
}
