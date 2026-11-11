package com.laboratorio.demoejercicio2.controller;

import com.laboratorio.demoejercicio2.model.Proveedor;
import com.laboratorio.demoejercicio2.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {
    private final ProveedorService service;

    public ProveedorController(ProveedorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Proveedor> crearProveedor(@Valid @RequestBody Proveedor proveedor) {
        Proveedor nuevo = service.crear(proveedor);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Proveedor>> consultarProveedores(
            @RequestParam(required = false) String categoria) {
        return ResponseEntity.ok(service.listarTodos(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> consultarProveedorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
