package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import br.com.fiap.tds2ps.spring_mvc.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("user", new PersonDto()); // Garante que 'user' não seja nulo
        return mv;
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("loggedAs", "Logado como Médico");
        mv.addObject("patientLazy", new PersonDto()); // Adicionar objeto vazio
        return mv;
    }

    @PostMapping("/sign-in")
    public ModelAndView signIn(@ModelAttribute("user") PersonDto usuario) {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("loggedAs", "Logado como Médico");
        mv.addObject("patientLazy", new PersonDto()); // Adicionar objeto vazio
        return mv;
    }
}