package br.com.fiap.tds2ps.spring_mvc.service;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import br.com.fiap.tds2ps.spring_mvc.model.Consulta;
import br.com.fiap.tds2ps.spring_mvc.model.Paciente;
import br.com.fiap.tds2ps.spring_mvc.repository.ConsultaRepository;
import br.com.fiap.tds2ps.spring_mvc.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Verifica se um paciente existe pelo CPF
     */
    public boolean existsPaciente(String cpf) {
        return pacienteRepository.existsById(cpf);
    }

    /**
     * Busca um paciente pelo CPF e converte para DTO
     */
    public PersonDto getPacienteDto(String cpf) {
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(cpf);
        if (pacienteOpt.isPresent()) {
            Paciente paciente = pacienteOpt.get();

            PersonDto dto = new PersonDto();
            dto.setCpf(paciente.getCpf());
            dto.setNomeCompleto(paciente.getNomeCompleto());
            dto.setEmail(paciente.getEmail());
            dto.setTelefone(paciente.getTelefone());
            dto.setCep(paciente.getCep());
            dto.setComplemento(paciente.getComplemento());
            dto.setGenero(paciente.getGenero());

            // Buscar consultas ordenadas por data (mais recentes primeiro)
            List<Consulta> consultas = consultaRepository.findByPacienteOrderByDataConsultaDesc(paciente);
            if (!consultas.isEmpty()) {
                // Montar histórico médico
                StringBuilder historico = new StringBuilder();
                for (Consulta c : consultas) {
                    historico.append("Data: ").append(c.getDataConsulta().format(formatter)).append("\n");
                    historico.append("Anamnese: ").append(c.getAnamnese()).append("\n");
                    historico.append("Prescrição: ").append(c.getPrescricao()).append("\n\n");
                }
                dto.setHistoricoMedico(historico.toString());
            } else {
                dto.setHistoricoMedico("Sem histórico médico.");
            }

            return dto;
        }
        return null;
    }

    /**
     * Salva um novo paciente a partir do DTO
     */
    @Transactional
    public void savePaciente(PersonDto pacienteDto) {
        Paciente paciente = new Paciente();
        paciente.setCpf(pacienteDto.getCpf());
        paciente.setNomeCompleto(pacienteDto.getNomeCompleto());
        paciente.setEmail(pacienteDto.getEmail());
        paciente.setTelefone(pacienteDto.getTelefone());
        paciente.setCep(pacienteDto.getCep());
        paciente.setComplemento(pacienteDto.getComplemento());
        paciente.setGenero(pacienteDto.getGenero());

        pacienteRepository.save(paciente);
    }

    /**
     * Adiciona uma consulta ao paciente
     */
    @Transactional
    public void addConsulta(String cpf, String anamnese, String prescricao) {
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(cpf);
        if (pacienteOpt.isPresent()) {
            Paciente paciente = pacienteOpt.get();

            Consulta consulta = new Consulta();
            consulta.setPaciente(paciente);
            consulta.setAnamnese(anamnese);
            consulta.setPrescricao(prescricao);

            consultaRepository.save(consulta);
        }
    }

    /**
     * Busca todos os pacientes (para listagem)
     */
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    /**
     * Busca um paciente pelo CPF
     */
    public Optional<Paciente> findByCpf(String cpf) {
        return pacienteRepository.findById(cpf);
    }

    /**
     * Remove um paciente pelo CPF
     */
    @Transactional
    public void delete(String cpf) {
        pacienteRepository.deleteById(cpf);
    }
}