package com.mr.adopciones.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data 
public class UserRegistrationDto { 
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message="La contraseña debe tener al menos 6 caracteres")
    private String password;
}