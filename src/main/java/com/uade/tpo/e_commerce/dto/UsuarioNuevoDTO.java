package com.uade.tpo.e_commerce.dto;

import lombok.Data;

@Data
public class UsuarioNuevoDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;


    public UsuarioNuevoDTO(String nombre, String apellido, String email, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
    }
}
