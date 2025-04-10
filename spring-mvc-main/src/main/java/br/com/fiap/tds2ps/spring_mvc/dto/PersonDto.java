package br.com.fiap.tds2ps.spring_mvc.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonDto {
    private String cpf;
    private String nomeCompleto;
    private String email;
    private String telefone;
    private String cep;
    private String complemento;
    private String genero;
    private String historicoMedico;
    private String anamnese;
    private String prescricao;

    // Método para adicionar nova consulta ao histórico médico
    public void adicionarAoHistoricoMedico(String novaAnamnese, String novaPrescricao) {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataAtual.format(formatter);

        String novoRegistro = "\n\nData: " + dataFormatada +
                "\nAnamnese: " + novaAnamnese +
                "\nPrescrição: " + novaPrescricao;

        if (this.historicoMedico == null || this.historicoMedico.trim().isEmpty()) {
            this.historicoMedico = "Primeira consulta em " + dataFormatada + "." + novoRegistro;
        } else {
            this.historicoMedico += novoRegistro;
        }
    }

    // Getter e setter existente
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Novos getters e setters
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

    public String getHistoricoMedico() {
        return historicoMedico;
    }

    public void setHistoricoMedico(String historicoMedico) {
        this.historicoMedico = historicoMedico;
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