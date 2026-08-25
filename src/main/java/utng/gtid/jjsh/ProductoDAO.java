package utng.gtid.jjsh;

public interface ProductoDAO {
    int insert(Producto p);
    Producto findByCodigo(String codigo);
    void updateStock(String codigo, int cantidad);
}