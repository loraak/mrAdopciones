package com.mr.adopciones;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mr.adopciones.models.Pet;
import com.mr.adopciones.repository.PetRepository;

@SpringBootApplication
public class AdopcionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdopcionesApplication.class, args);
	}
}


