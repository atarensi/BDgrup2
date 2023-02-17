import java.sql.*;
import java.util.Calendar;

public class Select {
    public static int provincies(Connection con, String ca) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT comunitat_aut_id " +
                    " FROM comunitats_autonomes " +
                    "WHERE codi_ine = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, ca);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                x = rs.getInt("comunitat_aut_id");
            }
        }
        catch(Exception e){
            System.out.println(e);}

        return x;
    }
}
