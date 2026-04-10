package com.uade.tpo.e_commerce.dto;

import lombok.Data;

@Data
public class UsuarioUpdateDTO {
    private String nombre;
    private String apellido;
    private String email;

    public UsuarioUpdateDTO(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }
}