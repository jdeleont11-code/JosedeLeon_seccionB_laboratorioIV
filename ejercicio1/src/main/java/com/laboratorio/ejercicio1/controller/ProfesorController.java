package com.laboratorio.ejercicio1.controller;

import com.laboratorio.ejercicio1.model.Profesor;
import com.laboratorio.ejercicio1.service.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {
    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Profesor> crearProfesor(@Valid @RequestBody Profesor profesor) {
        Profesor nuevo = service.crear(profesor);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> consultarProfesores(
            @RequestParam(required = false) String especialidad) {
        return ResponseEntity.ok(service.listarTodos(especialidad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> consultarProfesorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
