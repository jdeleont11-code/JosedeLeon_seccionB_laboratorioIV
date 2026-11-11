package com.laboratorio.demoejercicio2;

import com.laboratorio.demoejercicio2.exception.ResourceNotFoundException;
import com.laboratorio.demoejercicio2.model.Proveedor;
import com.laboratorio.demoejercicio2.repository.ProveedorRepository;
import com.laboratorio.demoejercicio2.service.ProveedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProveedorServiceTest {

    private ProveedorRepository repository;
    private ProveedorService service;

    @BeforeEach
    void setUp() {
        repository = mock(ProveedorRepository.class);
        service = new ProveedorService(repository);
    }

    @Test
    void testCrearProveedor() {
        Proveedor p = new Proveedor(null, "Ana", "Gomez", "ana@mail.com", "Tecnologia", "Activo");
        when(repository.save(p)).thenReturn(new Proveedor(1L, "Ana", "Gomez", "ana@mail.com", "Tecnologia", "Activo"));

        Proveedor creado = service.crear(p);
        assertNotNull(creado.getId());
        assertEquals("Ana", creado.getNombre());
    }

    @Test
    void testObtenerProveedorExistente() {
        Proveedor p = new Proveedor(1L, "Ana", "Gomez", "ana@mail.com", "Tecnologia", "Activo");
        when(repository.findById(1L)).thenReturn(Optional.of(p));

        Proveedor encontrado = service.buscarPorId(1L);
        assertEquals(1L, encontrado.getId());
    }

    @Test
    void testObtenerProveedorInexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99L);
        });
    }
}
