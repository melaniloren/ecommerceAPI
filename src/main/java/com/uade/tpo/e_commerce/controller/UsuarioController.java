package com.uade.tpo.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.e_commerce.dto.UsuarioDTO;
import com.uade.tpo.e_commerce.dto.UsuarioNuevoDTO;
import com.uade.tpo.e_commerce.dto.UsuarioUpdateDTO;
import com.uade.tpo.e_commerce.model.Usuario;
import com.uade.tpo.e_commerce.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // https://localhost:8080/api/usuarios -> ejecutar este método y devolver un mensaje ejemplo
    @GetMapping
    public List<Usuario> getAllUsuario() {
        return usuarioService.getAllUsuarios();
    }

    //  http://localhost:8080/api/usuarios/1 -> devuelve el usuario con id 1
    @GetMapping("/{id}")
    public UsuarioDTO getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }

    // del http://localhost:8080/api/usuarios/1 -> elimina el usuario con id 1
    @DeleteMapping("/{id}")
    public void deleteUsuarioById(@PathVariable Long id) {
        usuarioService.deleteUsuarioById(id);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioNuevoDTO usuarioDTO) {
        UsuarioDTO created = usuarioService.createUsuario(usuarioDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        UsuarioDTO usuarioActualizado = usuarioService.updateUsuario(id, usuarioUpdateDTO);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }
    
    


}

