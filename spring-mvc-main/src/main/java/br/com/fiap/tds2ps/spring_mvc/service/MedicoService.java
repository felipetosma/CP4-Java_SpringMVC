package br.com.fiap.tds2ps.spring_mvc.service;

import br.com.fiap.tds2ps.spring_mvc.model.Medico;
import br.com.fiap.tds2ps.spring_mvc.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    /**
     * Verifica se um médico existe pelo CPF
     */
    public boolean existsMedico(String cpf) {
        return medicoRepository.existsById(cpf);
    }

    /**
     * Busca um médico pelo CPF
     */
    public Optional<Medico> findByCpf(String cpf) {
        return medicoRepository.findById(cpf);
    }

    /**
     * Inicializa um médico no sistema se não existir
     */
    @Transactional
    public void initializeMedico(String cpf) {
        if (!existsMedico(cpf)) {
            Medico medico = new Medico();
            medico.setCpf(cpf);
            medico.setNome("Dr. " + cpf);
            medicoRepository.save(medico);
        }
    }
}