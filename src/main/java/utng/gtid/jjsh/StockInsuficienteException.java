package utng.gtid.jjsh;

public class StockInsuficienteException extends RuntimeException {
    private final String codigoProducto;
    private final int stockActual;
    private final int cantidadSolicitada;

    /**
     * Constructor para StockInsuficienteException.
     * @param codigo código del producto
     * @param actual stock disponible actual
     * @param solicitado cantidad que se intentó vender
     */
    public StockInsuficienteException(String codigo, int actual, int solicitado) {
        super(String.format("Stock insuficiente para '%s': disponible=%d, solicitado=%d", codigo, actual, solicitado));
        this.codigoProducto = codigo;
        this.stockActual = actual;
        this.cantidadSolicitada = solicitado;
    }

    public String getCodigoProducto() { return codigoProducto; }
    public int getStockActual() { return stockActual; }
    public int getCantidadSolicitada() { return cantidadSolicitada; }
}