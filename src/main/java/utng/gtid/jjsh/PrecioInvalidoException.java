package utng.gtid.jjsh;

/**
 * Excepcion lanzada cuando se intenta asignar un precio negativo a un producto.
 */
public class PrecioInvalidoException extends RuntimeException {
    private final double precio;

    /**
     * @param precio Valor del precio invalido.
     */
    public PrecioInvalidoException(double precio) {
        super(String.format("El precio %.2f es invalido. Debe ser mayor o igual a 0.", precio));
        this.precio = precio;
    }

    public double getPrecio() { return precio; }
}