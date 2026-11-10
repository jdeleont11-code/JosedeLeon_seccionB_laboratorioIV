package com.laboratorio.ejercicio1.repository;

import com.laboratorio.ejercicio1.model.Profesor;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProfesorRepository {
    private final List<Profesor> profesores = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Profesor save(Profesor profesor) {
        profesor.setId(idGenerator.getAndIncrement());
        profesores.add(profesor);
        return profesor;
    }

    public List<Profesor> findAll() {
        return new ArrayList<>(profesores);
    }

    public Optional<Profesor> findById(Long id) {
        return profesores.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
