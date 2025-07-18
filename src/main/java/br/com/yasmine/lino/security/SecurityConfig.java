package br.com.yasmine.lino.security;

import br.com.yasmine.lino.util.CustomAuthenticationManager;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAutenticacaoFilter jwtFilter;

    public SecurityConfig(JwtAutenticacaoFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                        .requestMatchers("/actuator/health").permitAll()
                        .requestMatchers("/liberado").authenticated()
                        .requestMatchers("/restrito").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/proibido").hasAuthority("ROLE_PROIBIDO")
                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex.authenticationEntryPoint((req, res, e) -> {
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    res.getWriter().write("Sem Autorização!");
                }))
                .build();
    }

    @Bean
    @Primary
    public AuthenticationManager authenticationManager() {
        return new CustomAuthenticationManager();
    }
}