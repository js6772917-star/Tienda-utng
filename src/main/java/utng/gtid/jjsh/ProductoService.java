package utng.gtid.jjsh;

import java.util.List;
import java.util.Optional;

public class ProductoService {
    private final ProductoDAO dao;

    public ProductoService(ProductoDAO dao) {
        if (dao == null) {
            throw new IllegalArgumentException("El DAO no puede ser nulo.");
        }
        this.dao = dao;
    }

    private void validarProducto(Producto p) {
        if (p == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (p.getPrecio() < 0) {
            throw new PrecioInvalidoException(p.getPrecio());
        }
        if (p.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
    }

    public void registrar(Producto p) {
        validarProducto(p);
        dao.guardar(p);
    }

    /**
     * @param codigo
     * @param cantidad
     */
    public void vender(String codigo, int cantidad) {
    if (codigo == null || codigo.trim().isEmpty()) {
        throw new IllegalArgumentException("El codigo no puede estar vacio.");
    }
    if (cantidad <= 0) {
        throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
    }

    Producto p = Optional.ofNullable(dao.findByCodigo(codigo))
            .orElseThrow(() -> new ProductoNoEncontradoException(codigo));

    if (p.getStock() < cantidad) {
        throw new StockInsuficienteException(codigo, p.getStock(), cantidad);
    }

    p.setStock(p.getStock() - cantidad);
    dao.actualizar(p);
}