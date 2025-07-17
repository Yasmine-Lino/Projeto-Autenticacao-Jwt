package br.com.yasmine.lino.controller;

import br.com.yasmine.lino.model.AutenticacaoRequest;
import br.com.yasmine.lino.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public LoginController(JwtService jwtService, AuthenticationManager authManager) {
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody AutenticacaoRequest autenticacaoRequest) {

        //TODO: Use Log4J que já tem no spring
        System.out.println("Requisição recebida: " + autenticacaoRequest.getUsuario());
        try {
            //TODO: Trocar o parametro para receber apenas as strings do usuario e da senha, porque a classe
            // UsernamePasswordAuthenticationToken não é usada para validar o usuario e senha
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(autenticacaoRequest.getUsuario(), autenticacaoRequest.getSenha())
            );
            String token = jwtService.generateToken(autenticacaoRequest.getUsuario());
            //TODO: Use Log4J que já tem no spring
            System.out.println("Token gerado: " + token);

            //TODO: Retorne como um json ex: { "accessToken": "[token gerado]" }
            return ResponseEntity.ok("Bearer " + token);

            //TODO: Atenção com a identação
            } catch (Exception e) {
                return ResponseEntity.status(401).body("Credenciais inválidas: " + e.getMessage());
        }
    }

    @GetMapping("/login")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Ação bem sucessida!");
    }
}
