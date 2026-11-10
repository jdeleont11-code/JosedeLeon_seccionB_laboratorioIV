package com.laboratorio.ejercicio1;

import com.laboratorio.ejercicio1.exception.ResourceNotFoundException;
import com.laboratorio.ejercicio1.model.Profesor;
import com.laboratorio.ejercicio1.repository.ProfesorRepository;
import com.laboratorio.ejercicio1.service.ProfesorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProfesorServiceTest {

    private ProfesorRepository repository;
    private ProfesorService service;

    @BeforeEach
    void setUp() {
        repository = mock(ProfesorRepository.class);
        service = new ProfesorService(repository);
    }

    @Test
    void testCrearProfesor() {
        Profesor p = new Profesor(null, "Juan", "Perez", "juan@mail.com", "Matematicas", "Activo");
        when(repository.save(p)).thenReturn(new Profesor(1L, "Juan", "Perez", "juan@mail.com", "Matematicas", "Activo"));

        Profesor creado = service.crear(p);
        assertNotNull(creado.getId());
        assertEquals("Juan", creado.getNombre());
    }

    @Test
    void testObtenerProfesorExistente() {
        Profesor p = new Profesor(1L, "Juan", "Perez", "juan@mail.com", "Matematicas", "Activo");
        when(repository.findById(1L)).thenReturn(Optional.of(p));

        Profesor encontrado = service.buscarPorId(1L);
        assertEquals(1L, encontrado.getId());
    }

    @Test
    void testObtenerProfesorInexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99L);
        });
    }
}
