package com.example.agenda_pessoal.Model;

public class Compromisso {
    private String titulo;
    private String descricao;
    private String data_compromisso;
    private String hora_compromisso;
    private String local_compromisso;
    private int participante;
    private int recorrencia_compromisso;
    private int visibilidade_compromisso;

    public Compromisso (String novoTitulo, String novaDescricao, String novaData_compromisso,
                        String novaHora_compromisso, String novoLoca_compromisso, int novoParticipante,
                        int novaRecorrencia_compromisso, int novaVisibilidade_compromisso){
        titulo = novoTitulo;
        descricao = novaDescricao;
        data_compromisso = novaData_compromisso;
        hora_compromisso = novaHora_compromisso;
        local_compromisso = novoLoca_compromisso;
        participante = novoParticipante;
        recorrencia_compromisso = novaRecorrencia_compromisso;
        visibilidade_compromisso = novaVisibilidade_compromisso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String novoTitulo) {
        titulo = novoTitulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String novaDescricao) {
        descricao = novaDescricao;
    }

    public String getData_compromisso() {
        return data_compromisso;
    }

    public void setData_compromisso(String novaData_compromisso) {
        data_compromisso = novaData_compromisso;
    }

    public String getHora_compromisso() {
        return hora_compromisso;
    }

    public void setHora_compromisso(String novaHora_compromisso) {
        this.hora_compromisso = novaHora_compromisso;
    }

    public String getLocal_compromisso() {
        return local_compromisso;
    }

    public void setLocal_compromisso(String novoLoca_compromisso) {
        local_compromisso = novoLoca_compromisso;
    }

    public int getParticipante() {
        return participante;
    }

    public void setParticipante(int novoParticipante) {
        participante = novoParticipante;
    }

    public int getRecorrencia_compromisso() {
        return recorrencia_compromisso;
    }

    public void setRecorrencia_compromisso(int novaRecorrencia_compromisso) {
        recorrencia_compromisso = novaRecorrencia_compromisso;
    }

    public int getVisibilidade_cmpromisso() {
        return visibilidade_compromisso;
    }

    public void setVisibilidade_compromisso(int novaVisibilidade_compromisso) {
        visibilidade_compromisso = novaVisibilidade_compromisso;
    }
}