import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProgramaPrincipal {
    public static Connection con;
    public static void main(String[] args) throws SQLException {
        con= DriverManager.getConnection("jdbc:mysql://10.2.112.28:3306/prova","perepi","pastanaga");
        ImportarComunitatsAutonomes();
        ImportProvincies();
        ImportMunicipis();
        ImportEleccionesMunicipales();
        ImportCandidaturas();
        ImportPersonas();

        con.close();
    }

    // Importación de los datos de la tabla Comunitats Autonomes
    public static void ImportarComunitatsAutonomes() {
        Leer.comunitatsAutonomes(con);
    }


    // Importación de los datos de la tabla Provincies
    public static void ImportProvincies() {
        Leer.provincies(con);
    }

    // Importación de los datos de la tabla Municipis
    public static void ImportMunicipis() {
        Leer.municipis(con);
    }
    
     // Importacion de los datos de la tabla Eleccions Municipals
    public static void ImportEleccionesMunicipales() {
        Leer.elecciones_municipales(con);
    }

    // Importación de los datos de la tabla Candidaturas
    public static void ImportCandidaturas() {
        Leer.municipis(con);
    }
    
        // Importación de los datos de la tabla Personas
    public static void ImportPersonas() {
        Leer.personas(con);
    }
}
