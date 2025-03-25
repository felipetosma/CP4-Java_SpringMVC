package br.com.fiap.tds2ps.spring_mvc.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    private String cpf;

    @Column(nullable = false)
    private String nomeCompleto;

    private String email;
    private String telefone;
    private String cep;
    private String complemento;
    private String genero;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consulta> consultas = new ArrayList<>();

    // Construtores
    public Paciente() {
    }

    public Paciente(String cpf, String nomeCompleto) {
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
    }

    // Métodos de conveniência para relacionamentos bidirecionais
    public void addConsulta(Consulta consulta) {
        consultas.add(consulta);
        consulta.setPaciente(this);
    }

    public void removeConsulta(Consulta consulta) {
        consultas.remove(consulta);
        consulta.setPaciente(null);
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    // Método para obter o histórico médico completo
    public String getHistoricoMedico() {
        if (consultas == null || consultas.isEmpty()) {
            return "Sem histórico médico.";
        }

        StringBuilder historicoBuilder = new StringBuilder();
        for (Consulta consulta : consultas) {
            historicoBuilder.append("Data: ").append(consulta.getDataConsulta()).append("\n");
            historicoBuilder.append("Anamnese: ").append(consulta.getAnamnese()).append("\n");
            historicoBuilder.append("Prescrição: ").append(consulta.getPrescricao()).append("\n\n");
        }

        return historicoBuilder.toString();
    }
}