package br.com.fiap.tds2ps.spring_mvc.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@SequenceGenerator(name = "consulta_seq", sequenceName = "consulta_seq", allocationSize = 1)
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consulta_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_cpf", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private LocalDateTime dataConsulta;

    @Column(columnDefinition = "CLOB")
    private String anamnese;

    @Column(columnDefinition = "CLOB")
    private String prescricao;

    // Construtores
    public Consulta() {
        this.dataConsulta = LocalDateTime.now();
    }

    public Consulta(Paciente paciente, String anamnese, String prescricao) {
        this.paciente = paciente;
        this.anamnese = anamnese;
        this.prescricao = prescricao;
        this.dataConsulta = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(String anamnese) {
        this.anamnese = anamnese;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }
}