package br.com.fiap.tds2ps.spring_mvc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    private String cpf;

    @Column(nullable = false)
    private String nome;

    private String especialidade;
    private String crm;

    // Construtores
    public Medico() {
    }

    public Medico(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}