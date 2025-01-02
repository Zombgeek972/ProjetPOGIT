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
    private List<String> lstEnnemis;
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
        while (queue.isEmpty()) {
            String line = queue.remove();
            Double time = Double.valueOf(line.substring(0, line.indexOf('|'))) ;
            String name = line.substring(line.indexOf('|'), -1);
            lstEnnemis.add(name);
            lstTimeToSpawn.add(time);
        }
    }

    
}