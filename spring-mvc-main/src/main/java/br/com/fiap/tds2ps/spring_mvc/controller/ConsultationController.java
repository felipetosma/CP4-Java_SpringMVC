package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {

    // Em uma aplicação real, teríamos um serviço/repositório de pacientes
    // Por simplicidade, usaremos um exemplo codificado
    private static final PersonDto PACIENTE_EXEMPLO = criarPacienteExemplo();

    private static PersonDto criarPacienteExemplo() {
        PersonDto paciente = new PersonDto();
        paciente.setCpf("12345678900");
        paciente.setNomeCompleto("Jessica Hadassa Sales");
        paciente.setEmail("jessica@exemplo.com");
        paciente.setTelefone("633193850-89");
        paciente.setHistoricoMedico("Paciente com hipertensão. Última consulta em 12/01/2024.");
        return paciente;
    }

    @PostMapping("/start")
    public ModelAndView start(Model model, @ModelAttribute("patientLazy") PersonDto paciente) {
        // Se o paciente já existe - usamos CPF 12345678900 como existente
        if(paciente.getCpf().equals("12345678900")){
            ModelAndView mv = new ModelAndView("add-consultation");
            mv.addObject("paciente", PACIENTE_EXEMPLO);
            return mv;
        }
        return new ModelAndView("add-patient");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("consulta") PersonDto consulta) {
        // Em uma aplicação real, salvaríamos a consulta aqui
        // Mas para nosso exemplo, apenas retornamos à página inicial
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("loggedAs", "Logado como Médico");
        mv.addObject("patientLazy", new PersonDto());
        mv.addObject("mensagem", "Consulta salva com sucesso!");
        return mv;
    }
}