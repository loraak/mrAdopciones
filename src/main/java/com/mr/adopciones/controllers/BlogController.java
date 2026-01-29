package com.mr.adopciones.controllers;

import java.util.List;

import com.mr.adopciones.services.BlogService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mr.adopciones.models.Blog;

@Controller
public class BlogController {

    private final BlogService blogService;

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
}
