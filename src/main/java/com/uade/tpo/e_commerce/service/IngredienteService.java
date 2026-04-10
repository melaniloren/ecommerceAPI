package com.uade.tpo.e_commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import com.uade.tpo.e_commerce.model.Ingrediente;
import com.uade.tpo.e_commerce.repository.IngredienteRepository;

@Service
@Transactional
public class IngredienteService {
 
    @Autowired
    private IngredienteRepository ingredienteRepository;
    
    public List<Ingrediente> getAllIngredientes() {
        return ingredienteRepository.findAll();
    }

    public void deleteIngredienteById(Long id) {
    ingredienteRepository.deleteById(id);
    }

    public Ingrediente getIngredienteById(Long id) {
        return ingredienteRepository.findById(id).orElse(null);
    }

    public Ingrediente saveIngrediente(Ingrediente ingrediente) {
    return ingredienteRepository.save(ingrediente);
    }


}
