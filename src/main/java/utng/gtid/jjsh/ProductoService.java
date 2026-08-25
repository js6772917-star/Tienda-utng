package utng.gtid.jjsh;

public class ProductoService {
    private final ProductoDAO dao;

    public ProductoService(ProductoDAO dao) {
        if (dao == null) {
            throw new IllegalArgumentException("DAO no puede ser null");
        }
        this.dao = dao;
    }

    public int registrar(Producto p) {
        validarProducto(p);
        return dao.insert(p);
    }

    public void vender(String codigo, int cantidad) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Codigo de producto requerido");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad debe ser positiva: " + cantidad);
        }

        Producto p = dao.findByCodigo(codigo);
        if (p == null) {
            throw new ProductoNoEncontradoException(codigo);
        }

        if (p.getStock() < cantidad) {
            throw new StockInsuficienteException(codigo, p.getStock(), cantidad);
        }

        dao.updateStock(codigo, -cantidad);
    }

    private void validarProducto(Producto p) {
        if (p == null) {
            throw new IllegalArgumentException("Producto no puede ser null");
        }
        if (p.getPrecio() < 0) {
            throw new PrecioInvalidoException(p.getPrecio());
        }
        if (p.getStock() < 0) {
            throw new IllegalArgumentException("Stock negativo");
        }
    }
}