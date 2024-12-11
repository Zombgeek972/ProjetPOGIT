public class Progression extends Niveau{
    private int lvls;
    private int waves;
    private int currentlvl;
    private int currentwave;

    public Progression(int lvls, int waves, int currentlvl, int currentwave) {
        this.lvls = lvls;
        this.waves = waves;
        this.currentlvl = currentlvl;
        this.currentwave = currentwave;
    }

    public int getLvls() {
        return lvls;
    }

    public int getWaves() {
        return waves;
    }

    public int getCurrentlvl() {
        return currentlvl;
    }

    public int getCurrentwave() {
        return currentwave;
    }
}
