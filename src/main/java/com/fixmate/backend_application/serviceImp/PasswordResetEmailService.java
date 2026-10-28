package com.fixmate.backend_application.serviceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetEmailService {

    private final JavaMailSender mailSender;
    private final String fromEmail;
    private final String frontendUrl;

    public PasswordResetEmailService(JavaMailSender mailSender,
                                     @Value("${app.mail.from}") String fromEmail,
                                     @Value("${app.frontend-url}") String frontendUrl) {
        this.mailSender = mailSender;
        this.fromEmail = fromEmail;
        this.frontendUrl = frontendUrl;
    }

    public void sendResetEmail(String recipient, String token) {
        // your existing method stays same
        String resetLink = frontendUrl + "/reset-password?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(recipient);
        message.setSubject("FixMate - Reset Your Password");
        message.setText("Hello,\n\nWe received a request to reset your FixMate password.\n\nClick the link below to choose a new password:\n" + resetLink + "\n\nThis link expires in 15 minutes and can only be used once.\n\nIf you did not request this change, you can ignore this email.\n\nRegards,\nThe FixMate Team");
        mailSender.send(message);
    }
}
