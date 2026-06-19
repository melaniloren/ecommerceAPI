package com.uade.tpo.e_commerce.controller;

import java.util.List;

import com.uade.tpo.e_commerce.dto.RecetaDetalleDTO;
import com.uade.tpo.e_commerce.dto.RecetaDetalleRequestDTO;
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

    // http://localhost:8080/api/receta-detalles/receta/{idReceta}
    // Devuelve todos los detalles (ingredientes) de una receta concreta.
    @GetMapping("/receta/{idReceta}")
    public List<RecetaDetalleDTO> getRecetaDetallesByReceta(@PathVariable Long idReceta) {
        return recetaDetalleService.getRecetaDetallesByReceta(idReceta);
    }

    @PutMapping("/receta/{idReceta}")
    public List<RecetaDetalleDTO> replaceRecetaDetallesByReceta(
            @PathVariable Long idReceta,
            @RequestBody List<RecetaDetalleRequestDTO> detalles
    ) {
        return recetaDetalleService.replaceRecetaDetallesByReceta(idReceta, detalles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaDetalleDTO> getRecetaDetalleById(@PathVariable Long id) {
        RecetaDetalleDTO detalle = recetaDetalleService.getRecetaDetalleById(id);
        if (detalle == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(detalle);
    }

    @PostMapping
    public ResponseEntity<RecetaDetalleDTO> createRecetaDetalle(@RequestBody RecetaDetalleRequestDTO recetaDetalle) {
        RecetaDetalleDTO created = recetaDetalleService.saveRecetaDetalle(recetaDetalle);
        return ResponseEntity.status(201).body(created);
    }

    @PostMapping("/{id}")
    public ResponseEntity<RecetaDetalleDTO> updateRecetaDetalle(@PathVariable Long id, @RequestBody RecetaDetalleRequestDTO recetaDetalle) {
        RecetaDetalleDTO updated = recetaDetalleService.updateRecetaDetalle(id, recetaDetalle);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecetaDetalle(@PathVariable Long id) {
        recetaDetalleService.deleteRecetaDetalleById(id);
        return ResponseEntity.noContent().build();
    }

}
