package utng.gtid.jjsh;

public class ProductoNoEncontradoException extends RuntimeException {
    private final String codigo;

    public ProductoNoEncontradoException(String codigo) {
        super("Producto no encontrado con codigo: " + codigo);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}