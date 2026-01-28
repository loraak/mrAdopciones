package com.mr.adopciones.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mr.adopciones.dto.UserRegistrationDto;
import com.mr.adopciones.services.UserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) { 
        model.addAttribute("user", new UserRegistrationDto()); 
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto registrationDto, BindingResult result) { 
        if (result.hasErrors()) { 
            return "register"; 
        }
        userService.save(registrationDto); 
        return "redirect:/login?success"; 
    }
}
