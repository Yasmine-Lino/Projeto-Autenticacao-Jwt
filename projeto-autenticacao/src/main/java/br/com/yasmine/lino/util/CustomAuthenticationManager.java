package br.com.yasmine.lino.util;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

//TODO: Essa classe poderia ser configurada como um bean anotando com @Component, não está errado, é apenas uma
// recomendação de boas práticas
public class CustomAuthenticationManager implements  AuthenticationManager{

    @Override
    public Authentication authenticate(Authentication authentication) {
        String nomeUsuario = authentication.getName();
        String senha = authentication.getCredentials().toString();


        System.out.println("Autenticacao: " + nomeUsuario + " - " + senha );

        if ("usuario".equals(nomeUsuario) && "senha123".equals(senha)) {
            return new UsernamePasswordAuthenticationToken(nomeUsuario, senha, null);
        }
        throw new RuntimeException("Usuário ou senha inválidos");
    }
}
