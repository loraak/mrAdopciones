package com.mr.adopciones.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;  

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Column(unique = true) 
    private String username;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "El formato del correo es inválido")
    private String email;

    @NotBlank
    @Size(min = 6, message="La contraseña debe tener al menos 6 caracteres")
    private String password;

    private String role = "ROLE_USER";

    private String resetPasswordToken;
}
