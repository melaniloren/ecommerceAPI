package com.uade.tpo.e_commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.e_commerce.model.RecetaDetalle;
import com.uade.tpo.e_commerce.repository.RecetaDetalleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecetaDetalleService {

    @Autowired
    private RecetaDetalleRepository recetaDetalleRepository;

    public List<RecetaDetalle> getAllRecetaDetalles() {
        return recetaDetalleRepository.findAll();
    }

    public RecetaDetalle getRecetaDetalleById(Long id) {
        return recetaDetalleRepository.findById(id).orElse(null);
    }

    public RecetaDetalle saveRecetaDetalle(RecetaDetalle recetaDetalle) {
        return recetaDetalleRepository.save(recetaDetalle);
    }

    public void deleteRecetaDetalleById(Long id) {
        recetaDetalleRepository.deleteById(id);
    }

}
