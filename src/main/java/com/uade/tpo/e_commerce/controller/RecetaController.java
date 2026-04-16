package com.uade.tpo.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.e_commerce.dto.RecetaDTO;
import com.uade.tpo.e_commerce.service.RecetaService;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping
    public List<RecetaDTO> getAllRecetas() {
        return recetaService.getAllRecetas();
    }

    @GetMapping("/{id}")
    public RecetaDTO getRecetaById(@PathVariable Long id) {
        return recetaService.getRecetaById(id);
    }

    @PostMapping
    public ResponseEntity<RecetaDTO> createReceta(@RequestBody RecetaDTO dto) {
        return ResponseEntity.status(201).body(recetaService.saveReceta(dto));
    }

    @PutMapping("/{id}")
    public RecetaDTO updateReceta(@PathVariable Long id, @RequestBody RecetaDTO dto) {
        return recetaService.updateReceta(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceta(@PathVariable Long id) {
        recetaService.deleteRecetaById(id);
        return ResponseEntity.noContent().build();
    }

}
