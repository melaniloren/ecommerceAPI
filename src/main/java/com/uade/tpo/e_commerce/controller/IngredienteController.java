package com.uade.tpo.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.e_commerce.model.Ingrediente;
import com.uade.tpo.e_commerce.service.IngredienteService;



@RestController
// para acceder a este controlador, la URL base será /api/ingredientes
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    // https://localhost:8080/api/ingredientes -> ejecutar este método y devolver un mensaje ejemplo
    @GetMapping
    public List<Ingrediente> getAllListaIngredientes() {
        return ingredienteService.getAllIngredientes();
    }

 
    @GetMapping("/{id}")
    public Ingrediente getIngredienteById(@PathVariable Long id) {
        return ingredienteService.getIngredienteById(id);
    }
 
    // del http://localhost:8080/api/ingredientes/1 -> elimina el ingrediente con id 1
    @DeleteMapping("/{id}")
    public void deleteIngredienteById(@PathVariable Long id) {
        ingredienteService.deleteIngredienteById(id);
    }

    // para crear un nuevo ingrediente, se envía un JSON con los datos del ingrediente al endpoint http://localhost:8080/api/ingredientes con el método POST
    @PostMapping
    public ResponseEntity<Ingrediente> createIngrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente created = ingredienteService.saveIngrediente(ingrediente);
        return ResponseEntity.status(201).body(created);
    }

    //para actualizar un ingrediente, se envía un JSON con los datos actualizados al endpoint http://localhost:8080/api/ingredientes/{id} con el método PUT
     @PutMapping("/{id}")
    public ResponseEntity<Ingrediente> updateIngrediente(@PathVariable Long id, @RequestBody Ingrediente body) {
        Ingrediente existing = ingredienteService.getIngredienteById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setNombre(body.getNombre());
        existing.setDescripcion(body.getDescripcion());
        return ResponseEntity.ok(ingredienteService.saveIngrediente(existing));
    }


}


