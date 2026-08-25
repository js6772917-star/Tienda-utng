package utng.gtid.jjsh;

import java.util.List;

public interface ProductoDAO {
    int insert(Producto p);
    Producto findByCodigo(String codigo);
    void updateStock(String codigo, int cantidad);
    List<Producto> obtenerTodos();
    void actualizar(Producto p);
    void guardar(Producto p);
}