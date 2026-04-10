package com.uade.tpo.e_commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.e_commerce.model.Receta;
import com.uade.tpo.e_commerce.repository.RecetaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    public List<Receta> getAllRecetas() {
        return recetaRepository.findAll();
    }

    public Receta getRecetaById(Long id) {
        return recetaRepository.findById(id).orElse(null);
    }

    public Receta saveReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    public void deleteRecetaById(Long id) {
        recetaRepository.deleteById(id);
    }

}
