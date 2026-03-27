package tpo_uade.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tpo_uade.e_commerce.model.Usuario;

public interface  UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
