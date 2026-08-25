package utng.gtid.jjsh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoServiceTest {
    private ProductoDAO dao;
    private ProductoService service;

    @BeforeEach
    void setUp() {
        dao = new ProductoDAOMemoria();
        service = new ProductoService(dao);
    }

    @Test
    void registrar_productoValido_retorna1() {
        Producto p = new Producto("P001", "Cuaderno", 35.0, 100, "Papeleria");
        assertEquals(1, service.registrar(p), "Debe retornar 1 fila afectada al registrar");
    }

    @Test
    void registrar_productoNulo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, 
            () -> service.registrar(null),
            "Registrar un producto nulo debe lanzar IllegalArgumentException"
        );
    }

    @Test
    void vender_stockSuficiente_reduceCantidad() {
        Producto p = new Producto("P001", "Cuaderno", 35.0, 100, "Papeleria");
        service.registrar(p);
        service.vender("P001", 10);
        assertEquals(90, dao.findByCodigo("P001").get().getStock(), "El stock restante debe ser 90");
    }

    @Test
    void vender_stockInsuficiente_lanzaExcepcion() {
        Producto p = new Producto("P001", "Cuaderno", 35.0, 5, "Papeleria");
        service.registrar(p);
        assertThrows(IllegalStateException.class, 
            () -> service.vender("P001", 10),
            "Vender más stock del disponible debe lanzar IllegalStateException"
        );
    }

    @Test
    void vender_productoNoEncontrado_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, 
            () -> service.vender("INEXISTENTE", 5),
            "Vender un código que no existe debe lanzar IllegalArgumentException"
        );
    }

    @Test
    void listarProductos_conElementos_retornaLista() {
        Producto p = new Producto("P001", "Cuaderno", 35.0, 100, "Papeleria");
        service.registrar(p);
        assertFalse(dao.findAll().isEmpty(), "La lista del DAO no debe estar vacía");
    }

    // Pruebas obligatorias de TDD para calcularDescuento
    @Test
    void calcularTotalConDescuento_valido_retornaMontoConDescuento() {
        Producto p = new Producto("P005", "Mochila", 100.0, 10, "Accesorios");
        double total = service.calcularTotalConDescuento(p, 0.10);
        assertEquals(90.0, total, 0.001, "El total con 10 por ciento de descuento sobre 100 debe ser 90.0");
    }

    @Test
    void calcularTotalConDescuento_productoNulo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, 
            () -> service.calcularTotalConDescuento(null, 0.10),
            "Producto nulo debe lanzar IllegalArgumentException"
        );
    }

    @Test
    void calcularTotalConDescuento_descuentoNegativo_lanzaExcepcion() {
        Producto p = new Producto("P005", "Mochila", 100.0, 10, "Accesorios");
        assertThrows(IllegalArgumentException.class, 
            () -> service.calcularTotalConDescuento(p, -0.1),
            "Descuento negativo debe lanzar IllegalArgumentException"
        );
    }

    @Test
    void calcularTotalConDescuento_descuentoMayorAUno_lanzaExcepcion() {
        Producto p = new Producto("P005", "Mochila", 100.0, 10, "Accesorios");
        assertThrows(IllegalArgumentException.class, 
            () -> service.calcularTotalConDescuento(p, 1.5),
            "Descuento mayor a 1 debe lanzar IllegalArgumentException"
        );
    }
}
