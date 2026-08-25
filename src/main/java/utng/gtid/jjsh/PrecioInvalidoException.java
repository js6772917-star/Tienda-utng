package utng.gtid.jjsh;

public class PrecioInvalidoException extends RuntimeException {
    private final double precio;

    /**
     * Constructor para PrecioInvalidoException.
     * @param precio valor de precio inválido ingresado
     */
    public PrecioInvalidoException(double precio) {
        super("Precio invalido: " + precio + ". Debe ser mayor o igual a 0.");
        this.precio = precio;
    }

    public double getPrecio() { return precio; }
}