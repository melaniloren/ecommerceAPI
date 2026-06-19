package com.uade.tpo.e_commerce.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.e_commerce.dto.LoginRequest;
import com.uade.tpo.e_commerce.dto.UsuarioNuevoDTO;
import com.uade.tpo.e_commerce.service.AuthenticationService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/auth")
//anotación de Lombok que genera automáticamente un constructor que incluye todos los campos marcados como final, es igual que usar @autowired
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    // Nombre de la cookie donde viaja el JWT
    private static final String TOKEN_COOKIE = "token";
    // 24 horas en segundos
    private static final long TOKEN_MAX_AGE = 24 * 60 * 60;

    //http://localhost:8080/api/auth/register con metodo post http, enviar un body -> crear un usuario
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UsuarioNuevoDTO request) {
        //request tiene los datos del usuario a registrar, como nombre, email y contraseña
        return ResponseEntity.ok(authenticationService.register(request));
    }

    //http://localhost:8080/api/auth/login con metodo post http, enviar un body -> loguear un usuario
    // En vez de devolver el token en el body, lo emitimos en una cookie HttpOnly.
    // Así el JavaScript del front NO puede leer el token (mitiga XSS) y el navegador
    // lo reenvía automáticamente en cada request.
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request) {
        // Generamos el token con la lógica existente del service (NO se toca authenticate()).
        String token = authenticationService.authenticate(request);

        ResponseCookie cookie = ResponseCookie.from(TOKEN_COOKIE, token)
                // No accesible desde JavaScript -> protege contra robo del token por XSS
                .httpOnly(true)
                // false en local porque una cookie Secure NO viaja por http://localhost.
                // true en producción/HTTPS.
                .secure(false)
                // La cookie solo se envía en requests del mismo sitio -> mitiga CSRF
                .sameSite("Strict")
                .path("/")
                .maxAge(TOKEN_MAX_AGE)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    //http://localhost:8080/api/auth/logout -> borra la cookie del token
    // Se emite la misma cookie pero vacía y con maxAge(0) para que el navegador la elimine.
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        ResponseCookie cookie = ResponseCookie.from(TOKEN_COOKIE, "")
                .httpOnly(true)
                // false en local; true en producción/HTTPS
                .secure(false)
                .sameSite("Strict")
                .path("/")
                // maxAge(0) -> el navegador borra la cookie inmediatamente
                .maxAge(0)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    //http://localhost:8080/api/auth/me -> datos del usuario autenticado
    // El front lo usa para rehidratar el estado de Redux tras un F5, ya que al ser
    // la cookie HttpOnly no puede leer el token ni decodificarlo por su cuenta.
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> me(Authentication authentication) {
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String, Object> body = Map.of(
                "email", authentication.getName(),
                "roles", roles);

        return ResponseEntity.ok(body);
    }
}
