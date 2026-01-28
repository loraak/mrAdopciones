package com.mr.adopciones.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mr.adopciones.models.Pet;
import com.mr.adopciones.repository.PetRepository;

@Controller
public class CatalogController {
    @Autowired
    private PetRepository petRepository;

    @GetMapping("/catalog")
    public String showCatalog(Model model) {
        model.addAttribute("pets", petRepository.findByAdoptedFalse());
        model.addAttribute("title", "Catálogo");
        model.addAttribute("breadcrumb", List.of("Catálogo")); 
        return "catalog"; 
    }

    @GetMapping("/catalog/{category}")
    public String showCategory(@PathVariable("category") String category, Model model) { 
            String categoryDb = switch (category.toLowerCase()) {
            case "perros" -> "Perro";
            case "gatos" -> "Gato";
            default -> "Otro";
        };

        model.addAttribute("pets", petRepository.findByCategoryAndAdoptedFalse(categoryDb));
        model.addAttribute("title", categoryDb + "s en Adopción");
        
        // Breadcrumb dinámico: Catálogo > Categoría
        model.addAttribute("breadcrumb", List.of("Catálogo", categoryDb + "s"));
        return "catalog";
    }

    @GetMapping("/pet/{id}")
    public String showPetDetails(@PathVariable("id") Long id, Model model) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        model.addAttribute("pet", pet);
        return "pet_details";
    }

    @PostMapping("/pet/adopt/{id}")
    public String adoptPet(@PathVariable("id") Long id) {
        Pet pet = petRepository.findById(id).orElseThrow();
        pet.setAdopted(true); 
        petRepository.save(pet); 
        return "redirect:/catalog?adopted_success";
    }
}; 

