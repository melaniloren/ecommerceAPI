package com.uade.tpo.e_commerce.exception;


public class EmailYaRegistradoException extends RuntimeException {
    
    // Pasamos el email al constructor para dar un mensaje de error más detallado
    public EmailYaRegistradoException(String email) {
        super("El email '" + email + "' ya se encuentra registrado en nuestra base de datos.");
    }
}