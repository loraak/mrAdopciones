package com.mr.adopciones.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NosotrosController {
    @GetMapping("/about-us")
    public String sobreNosotros(Model model) {
        model.addAttribute("titulo", "Sobre Nosotros");
        return "about-us"; 
    }
}

