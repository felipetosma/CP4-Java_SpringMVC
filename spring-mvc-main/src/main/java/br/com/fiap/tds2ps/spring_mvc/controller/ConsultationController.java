package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import br.com.fiap.tds2ps.spring_mvc.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/start")
    public ModelAndView start(Model model, @ModelAttribute PersonDto paciente) {
        // Verifica se o paciente existe pelo CPF
        if (paciente.getCpf() != null && !paciente.getCpf().trim().isEmpty() &&
                pacienteService.existsPaciente(paciente.getCpf())) {

            ModelAndView mv = new ModelAndView("add-consultation");
            mv.addObject("paciente", pacienteService.getPacienteDto(paciente.getCpf()));
            // Adiciona objeto consulta vazio para o formulário
            mv.addObject("consulta", new PersonDto());
            return mv;
        }

        // Se o paciente não existe, redireciona para cadastro
        ModelAndView mv = new ModelAndView("add-patient");
        // Pré-preencher o CPF já informado para facilitar
        mv.addObject("paciente", paciente);
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute PersonDto consulta) {
        try {
            // Verificar se há dados da consulta
            if (consulta.getCpf() != null && !consulta.getCpf().trim().isEmpty()) {
                // Adicionar consulta usando o serviço
                pacienteService.addConsulta(
                        consulta.getCpf(),
                        consulta.getAnamnese(),
                        consulta.getPrescricao()
                );

                // Redirecionar para a página inicial com mensagem de sucesso
                ModelAndView mv = new ModelAndView("home");
                mv.addObject("loggedAs", "Logado como Médico");
                mv.addObject("mensagem", "Consulta salva com sucesso!");
                // Adicionar objeto vazio para evitar erro null em patientLazy
                mv.addObject("patientLazy", new PersonDto());
                return mv;
            } else {
                // Se não houver CPF, voltar para a página inicial com mensagem de erro
                ModelAndView mv = new ModelAndView("home");
                mv.addObject("loggedAs", "Logado como Médico");
                mv.addObject("mensagem", "Erro: CPF do paciente não informado!");
                mv.addObject("patientLazy", new PersonDto());
                return mv;
            }
        } catch (Exception e) {
            // Tratamento de erro
            ModelAndView mv = new ModelAndView("home");
            mv.addObject("loggedAs", "Logado como Médico");
            mv.addObject("mensagem", "Erro ao salvar consulta: " + e.getMessage());
            mv.addObject("patientLazy", new PersonDto());
            return mv;
        }
    }
}