package com.uade.tpo.e_commerce.service;

import java.util.List;
import java.util.stream.Collectors;

import com.uade.tpo.e_commerce.dto.RecetaRequestDTO;
import com.uade.tpo.e_commerce.exception.CategoriaNotFoundException;
import com.uade.tpo.e_commerce.model.Categoria;
import com.uade.tpo.e_commerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.e_commerce.dto.RecetaDTO;
import com.uade.tpo.e_commerce.exception.RecetaNotFoundException;
import com.uade.tpo.e_commerce.model.Receta;
import com.uade.tpo.e_commerce.repository.RecetaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaService categoriaService;

    public List<RecetaDTO> getAllRecetas() {
        return recetaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public RecetaDTO getRecetaById(Long id) {
        Receta receta = recetaRepository.findById(id).orElseThrow(() -> new RecetaNotFoundException(id));
        return toDTO(receta);
    }

    public RecetaDTO saveReceta(RecetaRequestDTO dto) {
        List<Categoria> categorias = dto.getCategorias().stream()
                .map(id -> categoriaRepository.findById(id).orElseThrow(() -> new CategoriaNotFoundException(id)))
                .collect(Collectors.toList());

        Receta receta = Receta.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precioReceta(dto.getPrecio())
                .categorias(categorias)
                .build();

        Receta guardado = recetaRepository.save(receta);
        return toDTO(guardado);
    }

    public RecetaDTO updateReceta(Long id, RecetaRequestDTO dto) {
        Receta receta = recetaRepository.findById(id).orElseThrow(() -> new RecetaNotFoundException(id));

        List<Categoria> categorias = dto.getCategorias().stream()
                .map(idCategoria -> categoriaRepository.findById(idCategoria).orElseThrow(() -> new CategoriaNotFoundException(idCategoria)))
                .collect(Collectors.toList());

        receta.setNombre(dto.getNombre());
        receta.setDescripcion(dto.getDescripcion());
        receta.setPrecioReceta(dto.getPrecio());
        receta.setCategorias(categorias);

        Receta actualizado = recetaRepository.save(receta);
        return toDTO(actualizado);
    }

    public void deleteRecetaById(Long id) {
        recetaRepository.deleteById(id);
    }

    public List<RecetaDTO> buscarRecetasPorNombre(String nombre) {
        return recetaRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<RecetaDTO> buscarRecetasPorPrecioMenorA(Double precio) {
        return recetaRepository.findByPrecioRecetaLessThanEqual(precio)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<RecetaDTO> buscarRecetasPorNombreYPrecioMenorA(String nombre, Double precio) {
        return recetaRepository.findByNombreContainingIgnoreCaseAndPrecioRecetaLessThanEqual(nombre, precio)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public RecetaDTO agregarACategoria(Long idReceta, Long idCategoria) {
        Receta receta = recetaRepository.findById(idReceta).orElseThrow(() -> new RecetaNotFoundException(idReceta));
        Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(() -> new CategoriaNotFoundException(idCategoria));

        receta.getCategorias().add(categoria);
        Receta actualizado = recetaRepository.save(receta);
        return toDTO(actualizado);
    }

    public RecetaDTO eliminarDeCategoria(Long idReceta, Long idCategoria) {
        Receta receta = recetaRepository.findById(idReceta).orElseThrow(() -> new RecetaNotFoundException(idReceta));
        receta.getCategorias().removeIf(c -> c.getIdCategoria().equals(idCategoria));
        Receta actualizado = recetaRepository.save(receta);
        return toDTO(actualizado);
    }

    RecetaDTO toDTO(Receta receta) {
        return new RecetaDTO(
                receta.getIdReceta(),
                receta.getNombre(),
                receta.getDescripcion(),
                receta.getPrecioReceta(),
                receta.getCategorias().stream().map(categoriaService::toDTO).collect(Collectors.toList())
        );
    }
}