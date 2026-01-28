package com.mr.adopciones.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mr.adopciones.dto.UserRegistrationDto;
import com.mr.adopciones.models.User;
import com.mr.adopciones.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository; 
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(UserRegistrationDto dto) { 
        User user = new User(); 
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }

    public void updateResetPasswordToken(String token, String email) { 
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No encontramos tu correo electrónico :("));
        user.setResetPasswordToken(token);
        userRepository.save(user);
    }

    public User getByResetPasswordToken(String token) { 
        return userRepository.findByResetPasswordToken(token).orElse(null);
    }

    public void updatePassword(User user, String newPassword) { 
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }
}
