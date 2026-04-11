package com.uade.tpo.e_commerce.controller;

import java.util.List;

import com.uade.tpo.e_commerce.dto.RecetaDetalleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.e_commerce.service.RecetaDetalleService;

@RestController
@RequestMapping("/api/receta-detalles")
public class RecetaDetalleController {

    @Autowired
    private RecetaDetalleService recetaDetalleService;

    @GetMapping
    public List<RecetaDetalleDTO> getAllRecetaDetalles() {
        return recetaDetalleService.getAllRecetaDetalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaDetalleDTO> getRecetaDetalleById(@PathVariable Long id) {
        RecetaDetalleDTO detalle = recetaDetalleService.getRecetaDetalleById(id);
        if (detalle == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(detalle);
    }

    @PostMapping
    public ResponseEntity<RecetaDetalleDTO> createRecetaDetalle(@RequestBody RecetaDetalleDTO recetaDetalle) {
        RecetaDetalleDTO created = recetaDetalleService.saveRecetaDetalle(recetaDetalle);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecetaDetalle(@PathVariable Long id) {
        recetaDetalleService.deleteRecetaDetalleById(id);
        return ResponseEntity.noContent().build();
    }

}
