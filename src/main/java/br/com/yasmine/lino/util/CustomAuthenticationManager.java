package br.com.yasmine.lino.util;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements  AuthenticationManager{

    @Override
    public Authentication authenticate(Authentication authentication) {
        String nomeUsuario = authentication.getName();
        String senha = authentication.getCredentials().toString();

        if ("usuario".equals(nomeUsuario) && "senha123".equals(senha)) {
            return new UsernamePasswordAuthenticationToken(nomeUsuario, senha, null);
        }
        throw new RuntimeException("Usuário ou senha inválidos");
    }
}
