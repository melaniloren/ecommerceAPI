package com.uade.tpo.e_commerce.service;

import java.util.List;

import com.uade.tpo.e_commerce.dto.UsuarioDTO;
import com.uade.tpo.e_commerce.dto.UsuarioNuevoDTO;
import com.uade.tpo.e_commerce.dto.UsuarioUpdateDTO;
import com.uade.tpo.e_commerce.exception.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import com.uade.tpo.e_commerce.model.Usuario;
import com.uade.tpo.e_commerce.repository.UsuarioRepository;

@Service
@Transactional

public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public void deleteUsuarioById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            throw new UsuarioNotFoundException(id);
        }
        return new UsuarioDTO(usuario.getId_Usuario(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail());
    }

    public UsuarioDTO createUsuario(UsuarioNuevoDTO usuarioNuevoDTO) {
        /*if (usuarioDTO.getIdUsuario() == null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }*/
        if (usuarioNuevoDTO.getApellido() == null) {
            throw new IllegalArgumentException("El apellido no puede ser nulo");
        }
        if (usuarioNuevoDTO.getNombre() == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo.");
        }
        if (usuarioNuevoDTO.getEmail() == null) {
            throw new IllegalArgumentException("El email no puede ser nulo.");
        }
        if (usuarioNuevoDTO.getContrasenia() == null) {
            throw new IllegalArgumentException("La contraseña no puede ser nula.");
        }
        Usuario usuario = Usuario.builder().
                nombre(usuarioNuevoDTO.getNombre()).
                apellido(usuarioNuevoDTO.getApellido()).
                email(usuarioNuevoDTO.getEmail()).
                contrasenia(usuarioNuevoDTO.getContrasenia()).
                build();

        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(nuevoUsuario.getId_Usuario(), nuevoUsuario.getNombre(), nuevoUsuario.getApellido(), nuevoUsuario.getEmail());
    }

    public UsuarioDTO updateUsuario(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            throw new UsuarioNotFoundException(id);
        }
        if (usuarioUpdateDTO.getApellido() == null) {
            throw new IllegalArgumentException("El apellido no puede ser nulo");
        }
        if (usuarioUpdateDTO.getNombre() == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo.");
        }
        if (usuarioUpdateDTO.getEmail() == null) {
            throw new IllegalArgumentException("El email no puede ser nulo.");
        }
        usuario.setNombre(usuarioUpdateDTO.getNombre());
        usuario.setApellido(usuarioUpdateDTO.getApellido());
        usuario.setEmail(usuarioUpdateDTO.getEmail());
        return new UsuarioDTO(usuario.getId_Usuario(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail());
    }
    
}
