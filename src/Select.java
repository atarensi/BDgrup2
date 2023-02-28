import java.sql.*;
import java.util.Calendar;

public class Select {

    // Método para hacer un Select e insertar la id de comunitats autonomes según el código ine
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

    // Método para hacer un Select e insertar la id de provincias según el código ine

    public static int municipis(Connection con, String ine_prov) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT provincia_id " +
                    " FROM provincies " +
                    "WHERE codi_ine = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, ine_prov);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                x = rs.getInt("provincia_id");
            }
        }
        catch(Exception e){
            System.out.println(e);}

        return x;
    }

    // Método para hacer un Select e insertar la id de eleccions según el any

    public static int candidatures(int any, Connection con) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT eleccio_id " +
                    " FROM eleccions " +
                    "WHERE any = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, any);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                x = rs.getInt("eleccio_id");
            }
        }
        catch(Exception e){
            System.out.println(e);}

        return x;
    }
    
    // Método par hacer un Select e insertar la id de elecciones según el any y el mes
    public static int eleccions_muni_eleid(int any, int mes, Connection con) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT eleccio_id " +
                    " FROM eleccions " +
                    "WHERE any = ? AND mes = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, any);
            preparedStmt.setInt(2, mes);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                x = rs.getInt("eleccio_id");
            }
        }
        catch(Exception e){
            System.out.println(e);}

        return x;
    }

    // Método par hacer un Select e insertar la id de elecciones según el any y el mes
    public static int eleccions_muni_muniid(String ine, Connection con) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT municipi_id " +
                    " FROM municipis " +
                    "WHERE codi_ine = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, ine);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                x = rs.getInt("municipi_id");
            }
        }
        catch(Exception e){
            System.out.println(e);}

        return x;
    }
}
