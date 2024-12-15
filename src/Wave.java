import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class Wave {
    private List<Ennemis> lstEnnemis;
    private List<Double> lstTimeToSpawn;

    public Wave(String nomFichier){
        Path path = FileSystems.getDefault().getPath("ressources/waves", nomFichier);
    }
}