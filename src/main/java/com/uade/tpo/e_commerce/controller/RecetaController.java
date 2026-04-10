package com.uade.tpo.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.e_commerce.model.Receta;
import com.uade.tpo.e_commerce.service.RecetaService;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping
    public List<Receta> getAllRecetas() {
        return recetaService.getAllRecetas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> getRecetaById(@PathVariable Long id) {
        Receta receta = recetaService.getRecetaById(id);
        if (receta == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(receta);
    }

    @PostMapping
    public ResponseEntity<Receta> createReceta(@RequestBody Receta receta) {
        Receta created = recetaService.saveReceta(receta);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> updateReceta(@PathVariable Long id, @RequestBody Receta body) {
        Receta existing = recetaService.getRecetaById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setNombre(body.getNombre());
        existing.setDescripcion(body.getDescripcion());
        return ResponseEntity.ok(recetaService.saveReceta(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceta(@PathVariable Long id) {
        recetaService.deleteRecetaById(id);
        return ResponseEntity.noContent().build();
    }

}
