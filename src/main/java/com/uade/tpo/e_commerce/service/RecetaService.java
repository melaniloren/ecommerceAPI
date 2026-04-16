package com.uade.tpo.e_commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.e_commerce.dto.RecetaDTO;
import com.uade.tpo.e_commerce.model.Receta;
import com.uade.tpo.e_commerce.repository.RecetaRepository;
import com.uade.tpo.e_commerce.exception.RecetaNotFoundException;

import org.springframework.transaction.annotation.Transactional;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Transactional(readOnly = true)
    public List<RecetaDTO> getAllRecetas() {
        return recetaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public RecetaDTO getRecetaById(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNotFoundException(id));
        return toDTO(receta);
    }

    @Transactional
    public RecetaDTO saveReceta(RecetaDTO dto) {
        Receta receta = toEntity(dto);
        return toDTO(recetaRepository.save(receta));
    }

    @Transactional
    public RecetaDTO updateReceta(Long id, RecetaDTO dto) {
        Receta existing = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNotFoundException(id));
        existing.setNombre(dto.getNombre());
        existing.setDescripcion(dto.getDescripcion());
        return toDTO(recetaRepository.save(existing));
    }

    @Transactional
    public void deleteRecetaById(Long id) {
        if (!recetaRepository.existsById(id)) {
            throw new RecetaNotFoundException(id);
        }
        recetaRepository.deleteById(id);
    }

private RecetaDTO toDTO(Receta receta) {
    return new RecetaDTO(
            receta.getIdReceta(),
            receta.getNombre(),
            receta.getDescripcion(),
            receta.getPrecioReceta()
    );
}

    private Receta toEntity(RecetaDTO dto) {
        Receta receta = new Receta();
        receta.setNombre(dto.getNombre());
        receta.setDescripcion(dto.getDescripcion());
        return receta;
    }
}