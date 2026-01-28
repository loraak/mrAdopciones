package com.mr.adopciones.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && 
        !"anonymousUser".equals(auth.getPrincipal().toString())) {
            return "redirect:/"; 
        }
        
        return "login";
    }

    @GetMapping("/")
    public String index() { 
        return "index"; 
    }
    
}
