package com.uade.tpo.e_commerce.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.uade.tpo.e_commerce.exception.EmailNotFoundException;
import com.uade.tpo.e_commerce.model.Rol;
import com.uade.tpo.e_commerce.repository.UsuarioRepository;
import com.uade.tpo.e_commerce.security.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final UsuarioRepository usuarioRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new EmailNotFoundException(username));
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // AUTH
                        .requestMatchers("/api/auth/**").permitAll()

                        // GET públicos
                        .requestMatchers(HttpMethod.GET, "/api/recetas/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/receta-detalles/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categorias/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/ingredientes/**").permitAll()

                        // GET autenticados
                        .requestMatchers(HttpMethod.GET, "/api/pedidos/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/detalle-pedidos/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/perfil").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/carritos/**").authenticated()


                        // CARRITOS (usuario autenticado)
                        .requestMatchers(HttpMethod.GET, "/api/carritos/usuario/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/carritos/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/carritos/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/carritos").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/api/carritos/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/carritos/**").authenticated()

                        // POST
                        .requestMatchers(HttpMethod.POST, "/api/recetas")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.POST, "/api/ingredientes")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.POST, "/api/receta-detalles/**")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.POST, "/api/pedidos")
                            .authenticated()

                        .requestMatchers(HttpMethod.POST, "/api/detalle-pedidos/**")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.POST, "/api/categorias/**")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.POST, "/api/carritos/**" ).authenticated()

                        // PUT / PATCH
                        .requestMatchers(HttpMethod.PUT, "/api/recetas/**")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.PATCH, "/api/recetas/**")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.PUT, "/api/ingredientes/**")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.PUT, "/api/pedidos/**")
                            .authenticated()

                        .requestMatchers(HttpMethod.PUT, "/api/detalle-pedidos/**")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.PUT, "/api/categorias/**")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.PATCH, "/api/carritos/**").authenticated()

                        // DELETE
                        .requestMatchers(HttpMethod.DELETE, "/api/recetas/**")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.DELETE, "/api/ingredientes/**")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.DELETE, "/api/receta-detalles/**")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.DELETE, "/api/pedidos/**")
                            .authenticated()

                        .requestMatchers(HttpMethod.DELETE, "/api/detalle-pedidos/**")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.DELETE, "/api/categorias/**")
                            .hasRole(Rol.ADMIN.name())

                        // En Spring Security, gana la primera regla que hace match.
                        //No importa cuál aparezca “más específica” si esa especificidad
                        // no está definida antes: el orden importa mucho.
                        // Regla práctica:poner primero las rutas más específicas y después las más generales.

                        .requestMatchers(HttpMethod.DELETE, "/api/carritos/vaciar/**").authenticated()

                        .requestMatchers(HttpMethod.DELETE, "/api/carritos/**").hasRole(Rol.ADMIN.name())

                        // USUARIOS
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/**")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.POST, "/api/usuarios")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.PUT, "/api/usuarios/**")
                            .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.DELETE, "/api/usuarios/**")
                            .hasRole(Rol.ADMIN.name())

                        // cualquier otra request
                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("http://localhost:5173")
        );

        configuration.setAllowedMethods(
                List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        );

        configuration.setAllowedHeaders(List.of("*"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}