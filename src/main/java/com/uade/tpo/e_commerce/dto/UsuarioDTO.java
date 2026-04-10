package com.uade.tpo.e_commerce.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long idUsuario;
    private String nombre;
    private String apellido;


    public UsuarioDTO(Long id, String nombre, String apellido) {
        this.idUsuario = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
