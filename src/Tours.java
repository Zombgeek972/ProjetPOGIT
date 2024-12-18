abstract class Tours extends Combattant {
    private int cout;

    public Tours(int pv, int atk, double atkSpeed, double range, Element element,
            int cout, Joueur joueur) {
        super(pv, atk, atkSpeed, range, element, joueur);
        this.cout = cout;
    }

    public int getCout() {
        return cout;
    }

    /**
     * @param c un combattant énnemi, d'un type différent
     */
    @Override
    public void recoitDegats(Combattant c) {
        
    }

    public String toString() {
        return "PV : "+getPv()+", atk : "+getAtk()+", atkSpeed : "+getAtkSpeed()+", portée : "+getRange()+", element : "+getElement()+"), cout : "+cout;
    }

    abstract void draw(double x, double y, double rayon);
}
