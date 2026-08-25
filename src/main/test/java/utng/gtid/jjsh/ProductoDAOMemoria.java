package utng.gtid.jjsh;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDAOMemoria implements ProductoDAO {
    private final List<Producto> almacen = new ArrayList<>();
    private int contadorId = 1;

    @Override
    public int insert(Producto p) {
        if (p == null) {
            throw new IllegalArgumentException("Producto nulo");
        }
        p.setId(contadorId++);
        almacen.add(p);
        return 1;
    }

    @Override
    public List<Producto> findAll() {
        return new ArrayList<>(almacen);
    }

    @Override
    public Optional<Producto> findByCodigo(String codigo) {
        return almacen.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst();
    }

    @Override
    public int updateStock(String codigo, int cantidad) {
        return findByCodigo(codigo).map(p -> {
            p.setStock(p.getStock() + cantidad);
            return 1;
        }).orElse(0);
    }

    @Override
    public int delete(int id) {
        return almacen.removeIf(p -> p.getId() == id) ? 1 : 0;
    }
}