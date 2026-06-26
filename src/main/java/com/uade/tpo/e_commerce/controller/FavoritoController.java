package com.uade.tpo.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.e_commerce.dto.RecetaDTO;
import com.uade.tpo.e_commerce.service.FavoritoService;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    @Autowired private FavoritoService favoritoService;

    @GetMapping
    public ResponseEntity<List<RecetaDTO>> getFavoritos(@AuthenticationPrincipal String email) {
        return ResponseEntity.ok(favoritoService.getFavoritos(email));
    }

    @PostMapping("/{recetaId}")
    public ResponseEntity<Void> addFavorito(@AuthenticationPrincipal String email, @PathVariable Long recetaId) {
        favoritoService.addFavorito(email, recetaId);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{recetaId}")
    public ResponseEntity<Void> removeFavorito(@AuthenticationPrincipal String email, @PathVariable Long recetaId) {
        favoritoService.removeFavorito(email, recetaId);
        return ResponseEntity.noContent().build();
    }
}