package br.com.fiap.tds2ps.spring_mvc.repository;

import br.com.fiap.tds2ps.spring_mvc.model.Consulta;
import br.com.fiap.tds2ps.spring_mvc.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    // Buscar consultas por paciente
    List<Consulta> findByPacienteOrderByDataConsultaDesc(Paciente paciente);
}