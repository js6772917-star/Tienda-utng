package utng.gtid.jjsh;

public class ProductoNoEncontradoException extends RuntimeException {
    private final String codigo;

    /**
     * Constructor para ProductoNoEncontradoException.
     * @param codigo código del producto no encontrado
     */
    public ProductoNoEncontradoException(String codigo) {
        super("Producto no encontrado con codigo: " + codigo);
        this.codigo = codigo;
    }

    public String getCodigo() { return codigo; }
}