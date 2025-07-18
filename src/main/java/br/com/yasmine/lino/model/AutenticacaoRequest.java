package br.com.yasmine.lino.model;

//@Data
public class AutenticacaoRequest {

    private String usuario;
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean verificar(String usuario, String senha) {
        return  ("usuario".equals(usuario) || "admin".equals(usuario)) && "senha123".equals(senha);
    }
}
