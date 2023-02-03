import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import java.nio.file.*;

public class InsertDades {
    public static void main(String[] args) {
        ImportarComunitatsAutonomes();
    }

    public static void ImportarComunitatsAutonomes () {
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

        Path pathFitxer = Paths.get(pathActual.toString(), "02201904_MESA", "07021904.DAT");

        //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

        bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
        String strLinia;
        while ((strLinia = bfLector.readLine()) != null) {
            System.out.println(strLinia);
        }

    } catch (IOException e) {
        e.printStackTrace();
    } finally {
            try {
                if (bfLector != null)
                    bfLector.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
