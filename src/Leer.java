import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.sql.Connection;

public class Leer {

    // Función para leer el archivo 07 y buscar las  comunidades autónomas
    public static void comunitatsAutonomes(Connection con) {
        String nom;
        String ine;
        String ca;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(),"07021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {
                nom = strLinia.substring(14,64).trim();
                ine = strLinia.substring(11,13);
                ca = strLinia.substring(9,11);
                if (ine.equals("99") && !nom.equalsIgnoreCase("Total Nacional")) Insert.comunitatsAutonomes(nom, ca, con);

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
        String nom;
        String ca;
        String ine;
        int num_escons;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(),"07021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {
                nom = strLinia.substring(14,64).trim();
                ca = strLinia.substring(9,11);
                ine = strLinia.substring(11,13);
                num_escons = Integer.parseInt(strLinia.substring(149,155));
                if (!(ine.equals("99")))Insert.provincies(nom, ine, ca, num_escons, con);

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

    // Función para leer el archivo 05 y buscar los municipios
    public static void municipis (Connection con) {
        String nom;
        String codi;
        String ine;
        String ine_prov;
        String codi_districte;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(), "05021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {
                nom = strLinia.substring(18,118).trim();
                codi = strLinia.substring(16,18);
                ine = strLinia.substring(13,16);
                ine_prov = strLinia.substring(11,13);
                Insert.municipis(nom, ine, codi, ine_prov, con);
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
    public static void elecciones_municipales (Connection con) {
        int any;
        int mes;
        String ine;
        String ine_prov;
        String codi_districte;
        int num_taules;
        int cens;
        int vots_candidatures;
        int vots_blanc;
        int vots_nuls;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(), "05021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {
                any = Integer.parseInt(strLinia.substring(2,6));
                mes = Integer.parseInt(strLinia.substring(6,8));
                ine_prov = strLinia.substring(11,13);
                ine = strLinia.substring(13,16);
                codi_districte = strLinia.substring(16,18);
                num_taules = Integer.parseInt(strLinia.substring(136,141));
                cens = Integer.parseInt(strLinia.substring(149,157));
                vots_candidatures = Integer.parseInt(strLinia.substring(205,213));
                vots_blanc = Integer.parseInt(strLinia.substring(189,197));
                vots_nuls = Integer.parseInt(strLinia.substring(197,205));
                Insert.elecciones_municipales(any, mes,ine_prov, ine, codi_districte, num_taules, cens, vots_candidatures, vots_blanc, vots_nuls, con);
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
    public static void candidatures (Connection con) {
        String codi;
        String nomCurt;
        String nomLlarg;
        String codi_ac_prov;
        String codi_ac_ca;
        String codi_ac_nac;
        int any;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(), "03021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {
                codi = strLinia.substring(8, 14);
                nomCurt = strLinia.substring(14, 64);
                nomLlarg = strLinia.substring(64, 214);
                codi_ac_prov = strLinia.substring(214, 220);
                codi_ac_ca = strLinia.substring(220, 226);
                codi_ac_nac = strLinia.substring(226, 232);
                any = Integer.parseInt(strLinia.substring(2, 6));
                Insert.candidatures(codi, nomCurt, nomLlarg, codi_ac_prov, codi_ac_ca, codi_ac_nac, any, con);
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
    public static void personas (Connection con) {
        String nom;
        String cog1;
        String cog2;
        String sexe;
        String dob;
        String dia;
        String mes;
        String any;
        String dni;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(), "04021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {
                nom = strLinia.substring(25,50);
                cog1 = strLinia.substring(50,75);
                cog2 = strLinia.substring(75,100);
                sexe = strLinia.substring(100,101);
                dia = strLinia.substring(101,103);
                mes = strLinia.substring(103,105);
                any = strLinia.substring(105,109);
                if (dia.equals("00") || mes.equals("00") || any.equals("0000")) {
                    dob = null;
                }
                else {
                    dob = any + "-" + mes + "-" + dia;
                }
                if (strLinia.substring(109,119).equals("          ")) {
                    dni = null;
                }
                else dni = strLinia.substring(109,119);
                Insert.personas(nom, cog1, cog2, sexe, dob , dni, con);
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
    public static void candidats(Connection con) {
        int num;
        String tipus;
        String dni;
        String nom;
        String cog1;
        String cog2;
        int ine;
        int cod_can;

        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(), "04021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {

                ine = Integer.parseInt(strLinia.substring(9, 11));
                cod_can = Integer.parseInt(strLinia.substring(15, 21));
                num = Integer.parseInt(strLinia.substring(21, 24));
                tipus = strLinia.substring(24, 25);
                nom = strLinia.substring(25, 50);
                cog1 = strLinia.substring(50, 75);
                cog2 = strLinia.substring(75, 100);
                if (strLinia.substring(109, 119).equals("          ")) {
                    dni = "null";
                } else dni = strLinia.substring(109, 119);
                Insert.candidats(ine, cod_can, tipus, num, dni, nom, cog1, cog2, con);
            }
        }
        catch (IOException e) {
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
    // Función para leer el archivo 06 y buscar las personas
    public static void votos_municipales (Connection con) {
        int any;
        int mes;
        String codi_prov;
        String ine;
        String codi_dis;
        String codi_cand;
        int vots;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(), "06021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {
                any = Integer.parseInt(strLinia.substring(2,6));
                mes = Integer.parseInt(strLinia.substring(6,8));
                codi_prov = strLinia.substring(9,11);
                ine = strLinia.substring(11,14);
                codi_dis = strLinia.substring(14,16);
                codi_cand = strLinia.substring(16,22);
                vots = Integer.parseInt(strLinia.substring(22,30));
                Insert.votos_municipales(any, mes,codi_prov, ine,codi_dis,codi_cand ,vots, con);
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

    // Función para leer el archivo 08 y buscar las personas
    public static void votos_ca (Connection con) {
        String ine;
        String codi_cand;
        int vots;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(), "08021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {
                ine = strLinia.substring(9,11);
                codi_cand = strLinia.substring(14,20);
                vots = Integer.parseInt(strLinia.substring(20,28));
                if (!(ine.equals("99")))Insert.votos_ca(ine, codi_cand, vots, con);
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
    public static void votsProvincies(Connection con){
        String codi_cand;
        int vots;
        int candidats_obtinguts;
        int prov_id;

        BufferedReader bfLector = null;
        try{
            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(), "08021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null){
                prov_id = Integer.parseInt(strLinia.substring(11,13));
                codi_cand = strLinia.substring(14,20);
                vots = Integer.parseInt(strLinia.substring(20,28));;
                candidats_obtinguts = Integer.parseInt(strLinia.substring(28,33));
                if (!(prov_id == 99)) Insert.votsProvincials(prov_id, codi_cand, vots, candidats_obtinguts, con);
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                bfLector.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
