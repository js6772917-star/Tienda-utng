package utng.gtid.jjsh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoServiceTest {
    private ProductoService service;
    private ProductoDAOMemoria dao;

    @BeforeEach
    void setUp() {
        dao = new ProductoDAOMemoria();
        service = new ProductoService(dao);
    }

    @Test
    void constructor_daoNulo_lanzaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ProductoService(null));
    }

    @Test
    void registrar_productoValido_registraCorrectamente() {
        Producto p = new Producto("P01", "Teclado", 250.0, 10);
        service.registrar(p);
        assertTrue(service.buscarPorCodigo("P01").isPresent());
    }

    @Test
    void registrar_productoNulo_lanzaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> service.registrar(null));
    }

    @Test
    void registrar_precioNegativo_lanzaPrecioInvalidoException() {
        Producto p = new Producto("P02", "Mouse", -50.0, 5);
        PrecioInvalidoException ex = assertThrows(PrecioInvalidoException.class, () -> service.registrar(p));
        assertEquals(-50.0, ex.getPrecio());
    }

    @Test
    void registrar_stockNegativo_lanzaIllegalArgumentException() {
        Producto p = new Producto("P03", "Monitor", 1500.0, -1);
        assertThrows(IllegalArgumentException.class, () -> service.registrar(p));
    }

    @Test
    void vender_exitoso_disminuyeStock() {
        service.registrar(new Producto("P01", "Teclado", 250.0, 10));
        service.vender("P01", 3);
        assertEquals(7, service.buscarPorCodigo("P01").get().getStock());
    }
    @Test
    void vender_productoInexistente_lanzaProductoNoEncontradoException() {
        ProductoNoEncontradoException ex = assertThrows(ProductoNoEncontradoException.class, 
                () -> service.vender("INEXISTENTE", 1));
        assertEquals("INEXISTENTE", ex.getCodigo());
    }

    @Test
    void vender_sinStock_verificaDetallesDeExcepcion() {
        service.registrar(new Producto("P01", "Teclado", 250.0, 2));
        StockInsuficienteException ex = assertThrows(StockInsuficienteException.class, 
                () -> service.vender("P01", 5));
        assertEquals("P01", ex.getCodigoProducto());
        assertEquals(2, ex.getStockActual());
        assertEquals(5, ex.getCantidadSolicitada());
    }

    @Test
    void vender_cantidadInvalida_lanzaIllegalArgumentException() {
        service.registrar(new Producto("P01", "Teclado", 250.0, 10));
        assertThrows(IllegalArgumentException.class, () -> service.vender("P01", 0));
    }

    @Test
    void buscarPorCodigo_codigoVacio_lanzaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> service.buscarPorCodigo(""));
    }
}