package com.mr.adopciones.controllers;

import java.util.List;
import java.util.Map;

import com.mr.adopciones.services.BlogService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import com.mr.adopciones.models.Blog;

@Controller
public class BlogController {

    private final BlogService blogService;

    @Value("${serpapi.key}")

    private String apiKey;

    BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
    @GetMapping("/blog")
    public String showBlog(Model model) {
        model.addAttribute("title", "Blog de Cuidados");
        model.addAttribute("breadcrumb", List.of("Blog")); 
        return "blog";
    }
    
    @GetMapping("/blog/{id}")
    public String verBlog(@PathVariable String id, Model model) {
        Blog blog = blogService.obtenerBlog(id);
    
        model.addAttribute("title", "Blog");
        model.addAttribute("breadcrumb", List.of("Blog", blog.getTitulo())); 
        model.addAttribute("blog", blog);
        return "detalle-blog";
    }

    @GetMapping("/blog/search-external")
    public String searchExternal(@RequestParam("q") String query, Model model) {
        String url = "https://serpapi.com/search.json?engine=google&q=" + query + " pets care&api_key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        List<Map<String, Object>> organicResults = (List<Map<String, Object>>) response.get("organic_results");

        model.addAttribute("externalResults", organicResults);
        model.addAttribute("title", "Resultados de búsqueda");
        model.addAttribute("breadcrumb", List.of("Blog", "Búsqueda Externa"));
        
        return "blog";
    }
}
