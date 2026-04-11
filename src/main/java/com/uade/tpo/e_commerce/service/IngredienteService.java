package com.uade.tpo.e_commerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.uade.tpo.e_commerce.dto.IngredienteDTO;
import com.uade.tpo.e_commerce.model.Ingrediente;
import com.uade.tpo.e_commerce.repository.IngredienteRepository;


@Service
@Transactional
public class IngredienteService {
 
    @Autowired
    private IngredienteRepository ingredienteRepository;
    
    public List<IngredienteDTO> getAllIngredientes() {
        return ingredienteRepository.findAll()
                .stream()
                .map(i -> new IngredienteDTO(i.getIdIngrediente(), i.getNombre(), i.getDescripcion()))
                .collect(Collectors.toList());
    }

    public IngredienteDTO getIngredienteById(Long id) {
        Ingrediente ingrediente = ingredienteRepository.findById(id).orElse(null);

        if (ingrediente == null) {
            throw new RuntimeException("Ingrediente no encontrado con id: " + id);
            // TODO: hacer las excepciones mas generalizadas
        }

        return new IngredienteDTO(
                ingrediente.getIdIngrediente(),
                ingrediente.getNombre(),
                ingrediente.getDescripcion()
        );
    }

    public void deleteIngredienteById(Long id) {
        ingredienteRepository.deleteById(id);
    }

    public IngredienteDTO saveIngrediente(IngredienteDTO ingredienteDTO) {

        Ingrediente ingrediente = Ingrediente.builder()
                .nombre(ingredienteDTO.getNombre())
                .descripcion(ingredienteDTO.getDescripcion())
                .build();

        Ingrediente ingredienteGuardado = ingredienteRepository.save(ingrediente);

        return new IngredienteDTO(
                ingredienteGuardado.getIdIngrediente(),
                ingredienteGuardado.getNombre(),
                ingredienteGuardado.getDescripcion()
        );
    }

    public IngredienteDTO updateIngrediente(Long id, IngredienteDTO ingredienteDTO) {
        Ingrediente ingrediente = ingredienteRepository.findById(id).orElse(null);

        if (ingrediente == null) {
           throw new RuntimeException("Ingrediente no encontrado con id: " + id);
           // TODO: mejorar excepciones
        }

        ingrediente.setNombre(ingredienteDTO.getNombre());

        Ingrediente actualizado = ingredienteRepository.save(ingrediente);

        return new IngredienteDTO(
                actualizado.getIdIngrediente(),
                actualizado.getNombre(),
                actualizado.getDescripcion()
        );
    }
}