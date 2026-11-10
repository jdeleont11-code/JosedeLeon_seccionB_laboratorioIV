package com.laboratorio.ejercicio1.service;

import com.laboratorio.ejercicio1.exception.ResourceNotFoundException;
import com.laboratorio.ejercicio1.model.Profesor;
import com.laboratorio.ejercicio1.repository.ProfesorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesorService {
    private final ProfesorRepository repository;

    public ProfesorService(ProfesorRepository repository) {
        this.repository = repository;
    }

    public Profesor crear(Profesor profesor) {
        return repository.save(profesor);
    }

    public List<Profesor> listarTodos(String especialidad) {
        if (especialidad != null && !especialidad.isEmpty()) {
            return repository.findAll().stream()
                    .filter(p -> p.getEspecialidad() != null && p.getEspecialidad().equalsIgnoreCase(especialidad))
                    .collect(Collectors.toList());
        }
        return repository.findAll();
    }

    public Profesor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor con ID " + id + " no encontrado."));
    }
}
