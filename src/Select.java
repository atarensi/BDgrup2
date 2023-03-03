import java.sql.*;

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
    public static int provincies1(Connection con, int ine_prov) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT provincia_id " +
                    " FROM provincies " +
                    "WHERE provincia_id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, ine_prov);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                x = rs.getInt("provincia_id");
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

    public static int elecciones1(int any, Connection con) {
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
    public static int elecciones(int any, int mes, Connection con) {
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
    public static int municipis(String ine, Connection con) {
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

    // Método par hacer un Select e insertar la id de candidaturas según el codi_candidatura
    public static int candidatura(String codi_candidatura, Connection con) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT candidatura_id " +
                    " FROM candidatures " +
                    "WHERE codi_candidatura = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, codi_candidatura);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                x = rs.getInt("candidatura_id");
            }
        }
        catch(Exception e){
            System.out.println(e);}

        return x;
    }

    public static int candidatsCod(int cod_can, Connection con) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT candidatura_id " +
                    " FROM candidatures  " +
                    "WHERE codi_candidatura = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, cod_can);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                x = rs.getInt("candidatura_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return x;
    }
    public static int candidatsPersona(String dni,String nom,String cog1,String cog2, Connection con) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            if (!(dni.equals("null"))) {
                String query = "SELECT persona_id " +
                        " FROM persones " +
                        "WHERE dni = ?";

                PreparedStatement preparedStmt = con.prepareStatement(query);

                preparedStmt.setString(1, dni);

                ResultSet rs = preparedStmt.executeQuery();
                if (rs.next()) {
                    x = rs.getInt("persona_id");
                }
            }else{
                String query = "SELECT persona_id " +
                        " FROM   persones " +
                        "WHERE nom = ? && cog1 = ? && cog2 = ?" +
                        "LIMIT 1";

                PreparedStatement preparedStmt = con.prepareStatement(query);

                preparedStmt.setString(1, nom);
                preparedStmt.setString(2, cog1);
                preparedStmt.setString(3, cog2);

                ResultSet rs = preparedStmt.executeQuery();
                if (rs.next()) {
                    x = rs.getInt("persona_id");
                }
            }
            } catch(Exception e){
                System.out.println(e);
            }

            return x;
        }
    public static int candidatsProvincia(int ine, Connection con) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT provincia_id " +
                    " FROM provincies " +
                    "WHERE codi_ine = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, ine);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                x = rs.getInt("provincia_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return x;
    }
    // Método par hacer un Select e insertar la id de candidaturas según el codi_candidatura
    public static int comunitats_autonomes(String ine, Connection con) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT comunitat_aut_id " +
                    " FROM comunitats_autonomes " +
                    "WHERE codi_ine = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, ine);

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
