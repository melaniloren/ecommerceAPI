package com.uade.tpo.e_commerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
//este filtro se ejecuta antes de llamar al controller
//fue configurado en SecurityConfig en la instrucción `addFilterBefore`
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Este método se ejecuta en cada petición HTTP para verificar si existe un token JWT válido.
     * Dónde se utiliza:
     * - Se configura en `SecurityConfig` para que se ejecute antes que el filtro de autenticación de Spring Security.
     * - Intercepta todas las peticiones entrantes a la API.
     */
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Obtiene el token JWT desde la cookie "token" (antes se leía del header
        //    "Authorization: Bearer ..."). Ahora el token viaja como cookie HttpOnly,
        //    así que recorremos las cookies de la request buscando la llamada "token".
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // 2. Verifica si se encontró el token en la cookie.
        if (token != null && !token.isEmpty()) {
            // 4. Valida el token usando `jwtUtil.validateToken()`.
            if (jwtUtil.validateToken(token)) {
                // 5. Si el token es válido, extrae el nombre de usuario y los roles del token.
                String username = jwtUtil.getUsername(token);
                Set<String> roles = jwtUtil.getRoles(token);

                // transformar el conjunto de roles (cadenas de texto)  en la lista de autoridades (permisos) que Spring Security necesita para verificar si el usuario tiene acceso a un recurso.
                var authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                // usuario ya autenticado 
                // crea un objeto de autenticación con los detalles del usuario y sus roles
                var auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
                // 8. Finalmente, pasa la petición al siguiente filtro en la cadena.
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}