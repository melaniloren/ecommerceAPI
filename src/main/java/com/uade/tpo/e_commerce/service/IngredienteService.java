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
                .map(i -> new IngredienteDTO(i.getId_Ingrediente(), i.getNombre(), i.getDescripcion()))
                .collect(Collectors.toList());
    }

    public IngredienteDTO getIngredienteById(Long id) {
        Ingrediente ingrediente = ingredienteRepository.findById(id).orElse(null);

        if (ingrediente == null) {
            // throw new RecetaNotFoundException("Ingrediente no encontrado con id: " + id); //hacer las excepciones mas generalizadas
        }

        return new IngredienteDTO(
                ingrediente.getId_Ingrediente(),
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
                ingredienteGuardado.getId_Ingrediente(),
                ingredienteGuardado.getNombre(),
                ingredienteGuardado.getDescripcion()
        );
    }

    public IngredienteDTO updateIngrediente(Long id, IngredienteDTO ingredienteDTO) {
        Ingrediente ingrediente = ingredienteRepository.findById(id).orElse(null);

        if (ingrediente == null) {
           // throw new IngredienteNotFoundException("Ingrediente no encontrado con id: " + id);
        }

        ingrediente.setNombre(ingredienteDTO.getNombre());

        Ingrediente actualizado = ingredienteRepository.save(ingrediente);

        return new IngredienteDTO(
                actualizado.getId_Ingrediente(),
                actualizado.getNombre(),
                actualizado.getDescripcion()
        );
    }
}