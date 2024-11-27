import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDatos {

    private String maquina = "localhost";
    private String usuario = "root";
    private String clave = "Tnr240105";
    private int puerto = 3306;
    private String bd = "ajedrez";
    private String servidor = "";
    private Connection conexion = null;

    public AccesoDatos() {
        this.servidor = "jdbc:mysql://" + this.maquina + ":" + this.puerto + "/" + this.bd;

        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("ERROR AL REGISTRAR EL DRIVER");
            System.exit(0);
        }

        
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(this.servidor, this.usuario, this.clave);
                System.out.println("Conectado a ajedrez");
            }
        } catch (SQLException e) {
            System.err.println("ERROR AL CONECTAR CON EL SERVIDOR");
            e.printStackTrace();
            System.exit(0);
        }
    }

    
    public Connection getConexion() {
        return conexion;
    }

    
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
