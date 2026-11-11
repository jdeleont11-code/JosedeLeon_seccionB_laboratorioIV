package com.laboratorio.demoejercicio2.repository;

import com.laboratorio.demoejercicio2.model.Proveedor;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProveedorRepository {
    private final List<Proveedor> proveedores = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Proveedor save(Proveedor proveedor) {
        proveedor.setId(idGenerator.getAndIncrement());
        proveedores.add(proveedor);
        return proveedor;
    }

    public List<Proveedor> findAll() {
        return new ArrayList<>(proveedores);
    }

    public Optional<Proveedor> findById(Long id) {
        return proveedores.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
