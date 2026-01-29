package com.mr.adopciones.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mr.adopciones.models.Blog;

import jakarta.annotation.PostConstruct;

@Service
public class BlogService {
    private List<Blog> blogs;
    
    @PostConstruct
    public void init() {
        blogs = new ArrayList<>();
        
        blogs.add(new Blog("1", "Cómo preparar tu hogar para una nueva mascota", 
            "Recibir a un nuevo integrante en la familia es emocionante, pero antes de que cruce la puerta, es fundamental que el entorno sea seguro y acogedor. No importa si es un cachorro inquieto o un adulto rescatado; la organización previa facilitará mucho la transición.", 
            List.of("Identifica zonas de peligro: Revisa que no haya cables sueltos a la vista, productos de limpieza al alcance o plantas que puedan ser tóxicas (como los lirios o la azalea).","Asigna un espacio propio: Tu mascota necesitará un lugar donde se sienta segura. Prepara una cama cómoda en un rincón tranquilo de la casa.","Kit de bienvenida: Antes de su llegada, asegúrate de tener lo básico: recipientes para agua y comida, una correa resistente, juguetes adecuados para su tamaño y, si es un perro, bolsas para desechos o, si es un gato, su caja de arena."), 
            "Un hogar preparado reduce el estrés inicial de la mascota y te permite enfocarte en lo más importante: crear un vínculo de amor y confianza desde el primer segundo.",
            "fox-blog.png"));
            
        blogs.add(new Blog("2", "Alimentación balanceada para perros: Lo que debes saber", 
            "La nutrición es el pilar fundamental de la salud de tu perro. A menudo nos dejamos llevar por el marketing o el precio, pero entender qué hay en el plato de tu mejor amigo puede marcar la diferencia en su longevidad y nivel de energía.\n" + //
                                "\n" + //
            "¿Qué define una dieta equilibrada?", 
            List.of("Proteína de calidad: Debe ser el ingrediente principal. Busca opciones donde la carne (pollo, res, pescado) esté claramente identificada.", "Etapa de vida: Las necesidades nutricionales de un cachorro no son las mismas que las de un perro senior. Los cachorros requieren más energía y calcio, mientras que los adultos mayores necesitan cuidar sus articulaciones y digestión.","Cuidado con los extras: Los premios y la comida \"de humanos\" deben ser la excepción, no la regla. El exceso de calorías puede derivar en obesidad, un problema común que afecta la calidad de vida canina."), 
            "Siempre consulta con tu veterinario antes de realizar cambios drásticos en la dieta o si decides optar por alimentación natural (BARF), para asegurar que no haya deficiencias vitamínicas.",
            "dog-blog.png"));
            
        blogs.add(new Blog("3", "Primeros días con tu gato adoptado: Guía de paciencia",
        "Adoptar un gato es una experiencia gratificante, pero los felinos son animales de costumbres y muy territoriales. Es normal que al principio se sienta abrumado o decida esconderse bajo el sofá por unas horas (¡o días!).\n" + //
        "\n" + //
        "Consejos para una adaptación exitosa:", 
        List.of("La regla del espacio confinado: Al llegar, es recomendable dejarlo en una sola habitación con todas sus cosas (arena, comida, agua). Esto le permite reconocer olores sin sentirse expuesto en un espacio demasiado grande.","No fuerces el contacto: Deja que el gato se acerque a ti. Puedes sentarte cerca de él y hablarle con voz suave, pero evita cargarlo si notas que está tenso. El respeto a su espacio es la clave para ganar su corazón.","La importancia de la rutina: Los gatos aman la predictibilidad. Intenta servir sus comidas a la misma hora y dedicar momentos específicos para el juego."),
        "Cada gato tiene su propio ritmo. Algunos se sienten dueños de la casa en 24 horas, mientras que otros tardan semanas en salir de su escondite. ¡Se paciente!", 
        "colibri-blog.jpeg"));
    }
    
    public List<Blog> obtenerTodosBlogs() {
        return blogs;
    }
    
    public Blog obtenerBlog(String id) {
        return blogs.stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .orElse(null);
    }
}
