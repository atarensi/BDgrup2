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
}
