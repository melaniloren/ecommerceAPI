package com.uade.tpo.e_commerce.service;

import java.util.List;

import com.uade.tpo.e_commerce.dto.*;
import com.uade.tpo.e_commerce.model.Categoria;
import com.uade.tpo.e_commerce.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    private RecetaService recetaService;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoriaDTO getCategoriaById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id: " + id));
        return toDTO(categoria);
    }

    @Transactional(readOnly = true)
    public List<RecetaDTO> getAllRecetasByCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id: " + id));
        return categoria.getRecetas()
                .stream()
                .map(recetaService::toDTO)
                .toList();
    }

    @Transactional
    public CategoriaDTO saveCategoria(CategoriaRequestDTO dto) {
        Categoria categoria = toEntity(dto);
        return toDTO(categoriaRepository.save(categoria));
    }

    @Transactional
    public CategoriaDTO updateCategoria(Long id, CategoriaRequestDTO dto) {
        Categoria existing = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id: " + id));
        existing.setNombre(dto.getNombre());

        return toDTO(categoriaRepository.save(existing));
    }

    @Transactional
    public void deleteCategoriaById(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoría no encontrada con id: " + id);
        }
        categoriaRepository.deleteById(id);
    }

    /*private CategoriaDTO toDTO(Categoria categoria) {
        return new CategoriaDTO(
                categoria.getIdCategoria(),
                categoria.getNombre(),
                categoria.getRecetas()
                        .stream()
                        .map(this::toDTO)
                        .toList()
        );
    }*/

    /*private RecetaDTO toDTO(Receta receta) {
        return recetaService.toDTO(receta);
    }*/

    CategoriaDTO toDTO(Categoria categoria) {
        return new CategoriaDTO(
                categoria.getIdCategoria(),
                categoria.getNombre()
        );
    }

    Categoria toEntity(CategoriaRequestDTO dto) {
        return Categoria.builder().
                nombre(dto.getNombre()).
                build();
    }

}