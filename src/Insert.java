import java.sql.*;
import java.util.Calendar;
public class Insert {

    // Función que coge la información de las comunidades autónomas y lo inserta en la BBDD
    public static void comunitatsAutonomes(String nom, String ca, Connection con){

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO comunitats_autonomes (nom,codi_ine)"
                    + " values (?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, nom);
            preparedStmt.setString (2, ca);



            // execute the preparedstatement
            preparedStmt.execute();

        }catch(Exception e){
            System.out.println(e);
        }
    }


    // Función que coge la información de las provincias y la inserta en la BBDD
    public static void provincies (String nom, String ine, String ca, int num_escons, Connection con) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO provincies (comunitat_aut_id,nom,codi_ine,num_escons)"
                    + " values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Select.provincies(con, ca));
            preparedStmt.setString (2, nom);
            preparedStmt.setString (3, ine);
            preparedStmt.setInt(4,num_escons);



            // execute the preparedstatement
            preparedStmt.execute();

        }catch(Exception e){
            System.out.println(e);
        }

    }

    // Función que coge la info de los municipio y la introduce en la BBDD
    public static void municipis (String nom, String ine, String codi, String ine_prov, Connection con) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO municipis (nom,codi_ine,provincia_id,districte)"
                    + " values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, nom);
            preparedStmt.setString (2, ine);
            preparedStmt.setInt (3, Select.municipis(con, ine_prov));
            preparedStmt.setString(4, codi);



            // execute the preparedstatement
            preparedStmt.execute();

        }catch(Exception e){
            System.out.println(e);
        }

    }

    // Función que coge la info de las candidaturas y la introduce en la BBDD
    public static void candidatures(String codi, String nomCurt, String nomLlarg, String codi_ac_prov, String codi_ac_ca, String codi_ac_nac, int any, Connection con) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO candidatures (eleccio_id,codi_candidatura,nom_curt,nom_llarg," +
                    "codi_acumulacio_provincia,codi_acumulacio_ca,codi_acumulario_nacional)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Select.candidatures(any, con));
            preparedStmt.setString (2, codi);
            preparedStmt.setString (3, nomCurt);
            preparedStmt.setString(4, nomLlarg);
            preparedStmt.setString(5, codi_ac_prov);
            preparedStmt.setString(6, codi_ac_ca);
            preparedStmt.setString(7, codi_ac_nac);



            // execute the preparedstatement
            preparedStmt.execute();

        }catch(Exception e){
            System.out.println(e);
        }

    }
    
    // Función que coge la info de las personas y la introduce en la BBDD
    public static void personas (String nom, String cog1, String cog2, String sexe, String dob, String dni, Connection con) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO persones (nom,cog1,cog2,sexe,data_naixement,dni)"
                    + " values (?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, nom);
            preparedStmt.setString (2, cog1);
            preparedStmt.setString (3, cog2);
            preparedStmt.setString(4, sexe);
            preparedStmt.setString(5, dob);
            preparedStmt.setString(6, dni);



            // execute the preparedstatement
            preparedStmt.execute();

        }catch(Exception e){
            System.out.println(e);
        }

    }
}
