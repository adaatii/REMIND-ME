package com.example.agendapessoal.model;

public class Usuario {
    private String nome;
    private String email;
    private String telefone;
    private String password;

    public Usuario(String novoNome, String novoEmail, String novoTelefone, String novoPassword){
        nome = novoNome;
        email = novoEmail;
        telefone = novoTelefone;
        password = novoPassword;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String novoNome) {
        nome = novoNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String novoEmail) {
        email = novoEmail;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String novoTelefone) {
        telefone = novoTelefone;
    }

    public String getPassword() { return password; }

    public void setPassword(String novoPassword) { password = novoPassword; }




}
