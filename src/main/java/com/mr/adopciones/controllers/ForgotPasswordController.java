package com.mr.adopciones.controllers;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mr.adopciones.models.User;
import com.mr.adopciones.services.UserService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ForgotPasswordController {
    @Autowired private UserService userService; 
    @Autowired private JavaMailSender mailSender;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot_password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        try {
            userService.updateResetPasswordToken(token, email);
            String resetLink = "http://localhost:8081/reset-password?token=" + token; 
            sendEmail(email, resetLink);
            model.addAttribute("message", "Hemos enviado un enlace de restablecimiento de contraseña a su correo electrónico.");
        } catch (Exception e) { 
            model.addAttribute("error", e.getMessage());
        }
        return "forgot_password";
    }

    private void sendEmail(String email, String link) throws MessagingException, UnsupportedEncodingException { 
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("lorakarvizu@gmail.com", "Mr. Adopciones Soporte"); 
        helper.setTo(email);
        helper.setSubject("Restauración de contraseña");
        String content = "<p>Hola,</p>"
                + "<p>Has solicitado restablecer tu contraseña.</p>"
                + "<p>Haz clic en el siguiente enlace para restablecer tu contraseña:</p>"
                + "<p><a href=\"" + link + "\">Restablecer mi contraseña</a></p>"
                + "<br>"
                + "<p>Ignora este correo electrónico si recuerdas tu contraseña o no realizaste esta solicitud.</p>";
        helper.setText(content, true);
        mailSender.send(message);
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        User user = userService.getByResetPasswordToken(token);
        if (user == null) {
            model.addAttribute("error", "El enlace es inválido o ha expirado.");
            return "forgot_password";
        }
        model.addAttribute("token", token);
        return "reset_password_form";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getByResetPasswordToken(token);
        if (user == null) {
            model.addAttribute("error", "Ocurrió un error interno.");
            return "forgot_password";
        }
        
        userService.updatePassword(user, password);
        return "redirect:/login?reset_success"; 
}
}
