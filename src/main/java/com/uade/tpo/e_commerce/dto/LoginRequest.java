package com.uade.tpo.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
    //no seria contrasenia? o password esta bien? no se si es mejor usar el mismo idioma en todo el proyecto o no, pero me parece que password es mas comun y entendible para los programadores
}
