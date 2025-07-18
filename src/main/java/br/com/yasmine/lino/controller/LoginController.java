package br.com.yasmine.lino.controller;

import br.com.yasmine.lino.model.AutenticacaoRequest;
import br.com.yasmine.lino.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

        boolean verificacao = autenticacaoRequest.verificar(
                autenticacaoRequest.getUsuario(), autenticacaoRequest.getSenha());

        if(!verificacao) {
            return ResponseEntity.status(401).body("Credenciais inválidas!" );
        }
        String token = jwtService.generateToken(autenticacaoRequest.getUsuario());

        Map<String, String> response = new HashMap<>();
        response.put("accessToken", token);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/liberado")
    public ResponseEntity<String> liberado() {
        return ResponseEntity.ok("Você está autenticado!");
    }

    @GetMapping("/restrito")
    public ResponseEntity<String> restrito() {
        return ResponseEntity.ok("Acesso restrito autorizado!");
    }

    @GetMapping("/proibido")
    public ResponseEntity<String> proibido() {
        return ResponseEntity.ok("Acesso proibido!");
    }
}
