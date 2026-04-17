package com.uade.tpo.e_commerce.model;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String contrasenia;

    private String direccion;

    private String telefono;

  

     /**
     * getAuthorities() devuelve la colección de roles/permisos del usuario
     * - Cada autoridad debe implementar GrantedAuthority
     * - En este caso, convertimos el enum Role a un formato "ROLE_X" (ej: ROLE_ADMIN, ROLE_USER)
     * - Spring Security utiliza estas autoridades para control de acceso y seguridad
     * - Si el rol es null, asigna por defecto "ROLE_USER"
     * ? extended GrantedAuthority cualquier clase que extienda de GrantedAuthority
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // resultado ROLE_USER o ROLE_ADMIN
        return List.of(new SimpleGrantedAuthority("ROLE_" + (rol != null ? rol.name() : "USER")));
    }

    /**
     * getUsername() retorna el identificador único del usuario para autenticación
     * - En este caso usamos el email como username
     * - Spring Security utiliza este método para identificar al usuario durante
     *   el proceso de autenticación y en el contexto de seguridad
     */
    
     @Override
    public String getUsername() {
        return email;
    }

    //estado de la cuenta
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public @Nullable String getPassword() {

        return contrasenia;
    }
}


