package com.laboratorio.demoejercicio2.service;

import com.laboratorio.demoejercicio2.exception.ResourceNotFoundException;
import com.laboratorio.demoejercicio2.model.Proveedor;
import com.laboratorio.demoejercicio2.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorService {
    private final ProveedorRepository repository;

    public ProveedorService(ProveedorRepository repository) {
        this.repository = repository;
    }

    public Proveedor crear(Proveedor proveedor) {
        return repository.save(proveedor);
    }

    public List<Proveedor> listarTodos(String categoria) {
        if (categoria != null && !categoria.isEmpty()) {
            return repository.findAll().stream()
                    .filter(p -> p.getCategoria() != null && p.getCategoria().equalsIgnoreCase(categoria))
                    .collect(Collectors.toList());
        }
        return repository.findAll();
    }

    public Proveedor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor con ID " + id + " no encontrado."));
    }
}
