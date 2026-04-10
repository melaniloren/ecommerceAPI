package com.uade.tpo.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.e_commerce.model.RecetaDetalle;
import com.uade.tpo.e_commerce.service.RecetaDetalleService;

@RestController
@RequestMapping("/api/receta-detalles")
public class RecetaDetalleController {

    @Autowired
    private RecetaDetalleService recetaDetalleService;

    @GetMapping
    public List<RecetaDetalle> getAllRecetaDetalles() {
        return recetaDetalleService.getAllRecetaDetalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaDetalle> getRecetaDetalleById(@PathVariable Long id) {
        RecetaDetalle detalle = recetaDetalleService.getRecetaDetalleById(id);
        if (detalle == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(detalle);
    }

    @PostMapping
    public ResponseEntity<RecetaDetalle> createRecetaDetalle(@RequestBody RecetaDetalle recetaDetalle) {
        RecetaDetalle created = recetaDetalleService.saveRecetaDetalle(recetaDetalle);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecetaDetalle(@PathVariable Long id) {
        recetaDetalleService.deleteRecetaDetalleById(id);
        return ResponseEntity.noContent().build();
    }

}
