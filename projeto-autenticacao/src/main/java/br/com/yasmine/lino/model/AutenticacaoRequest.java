package br.com.yasmine.lino.model;

//TODO: Para começar a seguir nosso padrão utilize o Lombook para gerar os Getters e Setters
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
}
