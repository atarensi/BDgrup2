import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.sql.Connection;

public class Leer {

    // Función para leer el archivo 07 y buscar las  comunidades autónomas
    public static void comunitatsAutonomes(Connection con) {
        int id;
        String nom;
        String ine;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

        Path pathFitxer = Paths.get(pathActual.toString(), "02201904_MESA", "07021904.DAT");

        //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

        bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
        String strLinia;
        while ((strLinia = bfLector.readLine()) != null) {
            id = Integer.parseInt(strLinia.substring(9, 11));
            nom = strLinia.substring(14,64).trim();
            ine = strLinia.substring(11,13);
            if (ine.equals("99") && id != 99) Insert.comunitatsAutonomes(id, nom, ine, con);

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

    // Funcion para leer el archivo 07  y buscar las provincias
    public static void provincies (Connection con) {
        int id;
        String nom;
        String ine;
        int num_escons;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(), "02201904_MESA", "07021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {
                id = Integer.parseInt(strLinia.substring(9, 11));
                nom = strLinia.substring(14,64).trim();
                ine = strLinia.substring(11,13);
                num_escons = Integer.parseInt(strLinia.substring(149,155));
                if (!ine.equals("99")) Insert.provincies(id, nom, ine, num_escons, con);

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
