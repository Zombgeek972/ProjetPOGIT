import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Wave {
    private List<Ennemis> lstEnnemis;
    private List<Double> lstTimeToSpawn;

    public Wave(String nomFichier){
        Path path = FileSystems.getDefault().getPath("ressources/waves", nomFichier);
        Queue<String> queue = new LinkedList<String>();
        //enregistrement du fichier dans une queue contenant toutes les lignes
        try (BufferedReader readerer = Files.newBufferedReader(path)) {
            BufferedReader reader = Files.newBufferedReader(path);
            String line = readerer.readLine();

            while (((line = reader.readLine()) != null) ) {
                queue.add(line);
            }
            reader.close();
        } 
        catch (IOException e) {
            System.out.println(e);
        }
        lstEnnemis = new ArrayList<>();
        lstTimeToSpawn = new ArrayList<>();
        int i = 0;
        while (queue.isEmpty()) {
            String line = queue.remove();
            String[] linepart = queue.split("|") ;
            i++;
        }
    }
}