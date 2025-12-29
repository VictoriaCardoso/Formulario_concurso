package com.portalconcursos.demo.dto;

public class AprovadoResponse {

    private String id;
    private String nome;
    private String email;
    private String telefone;
    private String concursos;

    public AprovadoResponse(String id, String nome, String email, String telefone, String concursos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.concursos = concursos;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getConcursos() {
        return concursos;
    }
}
