import java.sql.*;
import java.util.Calendar;
public class Insert {

    // Función que coge la información de las comunidades autónomas y lo inserta en la BBDD
    public static void comunitatsAutonomes(int id, String nom, String ine){

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con= DriverManager.getConnection("jdbc:mysql://10.2.112.28:3306/prova","perepi","pastanaga");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO comunitats_autonomes (comunitat_aut_id,nom,codi_ine)"
                    + " values (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt    (1, id);
            preparedStmt.setString (2, nom);
            preparedStmt.setString (3, ine);



            // execute the preparedstatement
            preparedStmt.execute();

            //Tanquem la connexió
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

}
