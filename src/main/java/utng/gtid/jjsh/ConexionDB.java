package utng.gtid.jjsh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private Connection conexion;

    public ConexionDB() {
        try {
            String url = "jdbc:postgresql://localhost:5432/tienda_utng";
            conexion = DriverManager.getConnection(url, "postgres", "j3su511q");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}