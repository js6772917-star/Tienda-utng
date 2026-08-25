package utng.gtid.jjsh;

import java.sql.*;

public class ProductoDAOImpl implements ProductoDAO {
    private Connection con;

    public ProductoDAOImpl() {
        ConexionDB db = new ConexionDB();
        con = db.getConexion();
    }

    @Override
    public int insert(Producto producto) {
        String sql = "INSERT INTO productos (codigo, nombre, precio, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar producto", e);
        }
    }

    @Override
    public Producto findByCodigo(String codigo) {
        String sql = "SELECT codigo, nombre, precio, stock FROM productos WHERE codigo = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Producto p = new Producto();
                    p.setCodigo(rs.getString("codigo"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setStock(rs.getInt("stock"));
                    return p;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar producto por código", e);
        }
        return null;
    }

    @Override
    public void updateStock(String codigo, int cantidadCambio) {
        String sql = "UPDATE productos SET stock = stock + ? WHERE codigo = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cantidadCambio);
            ps.setString(2, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el stock", e);
        }
    }
}