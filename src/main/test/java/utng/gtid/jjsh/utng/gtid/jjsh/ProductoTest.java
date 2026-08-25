package utng.gtid.jjsh;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    @Test
    void constructor_datosValidos_asignaCorrectamente() {
        Producto p = new Producto("P001", "Libreta", 25.5, 50, "Papeleria");
        
        assertAll("Verificando atributos del producto",
            () -> assertEquals("P001", p.getCodigo(), "El código debe coincidir"),
            () -> assertEquals("Libreta", p.getNombre(), "El nombre debe coincidir"),
            () -> assertEquals(25.5, p.getPrecio(), 0.001, "El precio debe coincidir"),
            () -> assertEquals(50, p.getStock(), "El stock debe coincidir")
        );
    }

    @Test
    void constructor_precioNegativo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Producto("P002", "Borrador", -5.0, 10, "Papeleria"),
            "Debe lanzar excepción si el precio es negativo"
        );
    }

    @Test
    void constructor_nombreNulo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Producto("P003", null, 15.0, 10, "Papeleria"),
            "Debe lanzar excepción si el nombre es nulo"
        );
    }

    @Test
    void isActivo_porDefecto_retornaTrue() {
        Producto p = new Producto("P004", "Pluma", 10.0, 20, "Papeleria");
        assertTrue(p.isActivo(), "El producto debe estar activo por defecto");
    }
}