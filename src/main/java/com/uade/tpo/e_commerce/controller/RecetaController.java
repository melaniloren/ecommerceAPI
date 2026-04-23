package com.uade.tpo.e_commerce.controller;

import java.util.List;

import com.uade.tpo.e_commerce.dto.RecetaRequestDTO;
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
    public ResponseEntity<List<RecetaDTO>> getAllRecetas() {
        return ResponseEntity.ok(recetaService.getAllRecetas());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<RecetaDTO>> buscarRecetas(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Double precio) {

        boolean sinNombre = (nombre == null || nombre.isBlank());
        boolean sinPrecio = (precio == null);

        if (sinNombre && sinPrecio) {
            return ResponseEntity.ok(recetaService.getAllRecetas());
        }

        if (!sinNombre && !sinPrecio) {
            return ResponseEntity.ok(recetaService.buscarRecetasPorNombreYPrecioMenorA(nombre, precio));
        }

        if (!sinNombre) {
            return ResponseEntity.ok(recetaService.buscarRecetasPorNombre(nombre));
        }

        return ResponseEntity.ok(recetaService.buscarRecetasPorPrecioMenorA(precio));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaDTO> getRecetaById(@PathVariable Long id) {
        return ResponseEntity.ok(recetaService.getRecetaById(id));
    }

    @PostMapping
    public ResponseEntity<RecetaDTO> createReceta(@RequestBody RecetaRequestDTO dto) {
        return ResponseEntity.status(201).body(recetaService.saveReceta(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaDTO> updateReceta(@PathVariable Long id, @RequestBody RecetaRequestDTO dto) {
        return ResponseEntity.ok(recetaService.updateReceta(id, dto));
    }

    @PatchMapping("/{id}/categorias")
    public ResponseEntity<RecetaDTO> addRecetaToCategoria(@PathVariable Long id, @RequestParam Long idCategoria) {
        return ResponseEntity.ok(recetaService.agregarACategoria(id, idCategoria));
    }

    @DeleteMapping("/{id}/categorias")
    public ResponseEntity<RecetaDTO> removeRecetaFromCategoria(@PathVariable Long id, @RequestParam Long idCategoria) {
        return ResponseEntity.ok(recetaService.eliminarDeCategoria(id, idCategoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceta(@PathVariable Long id) {
        recetaService.deleteRecetaById(id);
        return ResponseEntity.noContent().build();
    }

}
