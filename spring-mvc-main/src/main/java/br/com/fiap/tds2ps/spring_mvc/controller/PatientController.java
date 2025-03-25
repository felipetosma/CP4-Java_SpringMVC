package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import br.com.fiap.tds2ps.spring_mvc.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/register")
    public ModelAndView registerForm() {
        ModelAndView mv = new ModelAndView("add-patient");
        mv.addObject("paciente", new PersonDto());
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView addPatient(@ModelAttribute PersonDto paciente) {
        // Validação básica
        if (paciente.getCpf() == null || paciente.getCpf().trim().isEmpty()) {
            ModelAndView mv = new ModelAndView("add-patient");
            mv.addObject("erro", "CPF é obrigatório");
            return mv;
        }

        // Salvar o paciente no banco de dados
        pacienteService.savePaciente(paciente);

        // Preparar a página de consulta
        ModelAndView mv = new ModelAndView("add-consultation");
        mv.addObject("paciente", paciente);
        mv.addObject("consulta", new PersonDto()); // Adiciona um objeto para a consulta
        mv.addObject("mensagem", "Paciente cadastrado com sucesso!");
        return mv;
    }
}