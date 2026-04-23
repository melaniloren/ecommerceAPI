package com.uade.tpo.e_commerce.service;

import java.util.List;

import com.uade.tpo.e_commerce.dto.RecetaRequestDTO;
import com.uade.tpo.e_commerce.model.Categoria;
import com.uade.tpo.e_commerce.repository.CategoriaRepository;
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
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<RecetaDTO> getAllRecetas() {
        return recetaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RecetaDTO> buscarRecetasPorNombre(String nombre) {
        return recetaRepository.findByNombre(nombre)
            .stream()
            .map(this::toDTO)
            .toList();
    }

    @Transactional(readOnly = true)
    public List<RecetaDTO> buscarRecetasPorPrecioMenorA(Double precio) {
        return recetaRepository.findByPrecioRecetaLessThanEqual(precio)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List <RecetaDTO> buscarRecetasPorNombreYPrecioMenorA(String nombre, Double precio) {
        return recetaRepository.findByNombreContainingIgnoreCaseAndPrecioRecetaLessThanEqual(nombre, precio)
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
    public RecetaDTO saveReceta(RecetaRequestDTO dto) {
        Receta receta = toEntity(dto);
        return toDTO(recetaRepository.save(receta));
    }

    @Transactional
    public RecetaDTO updateReceta(Long id, RecetaRequestDTO dto) {
        Receta existing = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNotFoundException(id));
        existing.setNombre(dto.getNombre());
        existing.setDescripcion(dto.getDescripcion());
        existing.setPrecioReceta(dto.getPrecio());
        return toDTO(recetaRepository.save(existing));
    }

    @Transactional
    public RecetaDTO agregarACategoria(Long idReceta, Long idCategoria) {
        Receta receta = recetaRepository.getReferenceById(idReceta);
        Categoria categoria = categoriaRepository.getReferenceById(idCategoria);
        receta.getCategorias().add(categoria);
        return toDTO(recetaRepository.save(receta));
    }

    @Transactional
    public RecetaDTO eliminarDeCategoria(Long idReceta, Long idCategoria) {
        Receta receta = recetaRepository.getReferenceById(idReceta);
        Categoria categoria = categoriaRepository.getReferenceById(idCategoria);
        receta.getCategorias().remove(categoria);
        return toDTO(recetaRepository.save(receta));
    }

    @Transactional
    public void deleteRecetaById(Long id) {
        if (!recetaRepository.existsById(id)) {
            throw new RecetaNotFoundException(id);
        }
        recetaRepository.deleteById(id);
    }

    RecetaDTO toDTO(Receta receta) {
        return new RecetaDTO(
                receta.getIdReceta(),
                receta.getNombre(),
                receta.getDescripcion(),
                receta.getPrecioReceta(),
                receta.getCategorias()
                        .stream()
                        .map(categoriaService::toDTO)
                        .toList()
        );
}

    private Receta toEntity(RecetaRequestDTO dto) {
        return Receta.builder().
                nombre(dto.getNombre()).
                descripcion(dto.getDescripcion()).
                precioReceta(dto.getPrecio()).
                categorias(dto.getCategorias()
                                .stream()
                                .map(categoriaRepository::getReferenceById)
                                .toList()).
                build();
    }


}