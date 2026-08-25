package utng.gtid.jjsh;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando aplicación Tienda UTNG...");
        
        ProductoDAO dao = new ProductoDAOImpl();
        ProductoService servicio = new ProductoService(dao);
        
        Producto p = new Producto();
        p.setCodigo("P002");
        p.setNombre("Laptop hp");
        p.setPrecio(18500.0);
        p.setStock(8);
        
        servicio.registrar(p);
        
        System.out.println("Demo ejecutado correctamente.");
    }
}