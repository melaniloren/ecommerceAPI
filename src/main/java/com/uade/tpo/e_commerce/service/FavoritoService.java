package com.uade.tpo.e_commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.e_commerce.dto.RecetaDTO;
import com.uade.tpo.e_commerce.model.Receta;
import com.uade.tpo.e_commerce.model.Usuario;
import com.uade.tpo.e_commerce.repository.RecetaRepository;
import com.uade.tpo.e_commerce.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service @Transactional
public class FavoritoService {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private RecetaRepository recetaRepository;

    public List<RecetaDTO> getFavoritos(String email) {
        Usuario u = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        return u.getFavoritos().stream()
            .map(r -> RecetaDTO.builder()
                .id(r.getIdReceta())
                .nombre(r.getNombre())
                .descripcion(r.getDescripcion())
                .imagen(r.getImagen())
                .precio(r.getPrecioReceta())
                .build())
            .toList();
    }

    public void addFavorito(String email, Long recetaId) {
        Usuario u = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Receta r = recetaRepository.findById(recetaId)
            .orElseThrow(() -> new RuntimeException("Receta no encontrada"));
        
        if (!u.getFavoritos().contains(r)) {
            u.getFavoritos().add(r);
        }
    }

    public void removeFavorito(String email, Long recetaId) {
        Usuario u = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        u.getFavoritos().removeIf(r -> r.getIdReceta().equals(recetaId));
    }
}