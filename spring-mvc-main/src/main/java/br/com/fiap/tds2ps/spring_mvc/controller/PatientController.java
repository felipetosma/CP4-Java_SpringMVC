package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @PostMapping("/save")
    public ModelAndView addPatient(@ModelAttribute PersonDto paciente) {
        // Em uma aplicação real, salvaríamos o paciente aqui
        // Para este exemplo, apenas passamos os dados para a página de consulta
        ModelAndView mv = new ModelAndView("add-consultation");
        if (paciente == null) {
            paciente = new PersonDto(); // Garante que não seja nulo
        }
        mv.addObject("paciente", paciente);
        mv.addObject("consulta", new PersonDto()); // Adiciona um objeto para a consulta
        mv.addObject("mensagem", "Paciente salvo com sucesso!");
        return mv;
    }
}