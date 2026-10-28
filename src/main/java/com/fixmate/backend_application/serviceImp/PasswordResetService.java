package com.fixmate.backend_application.serviceImp;
import com.fixmate.backend_application.entity.PasswordResetToken;
import com.fixmate.backend_application.entity.User;
import com.fixmate.backend_application.repository.PasswordResetTokenRepository;
import com.fixmate.backend_application.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HexFormat;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PasswordResetService {

    private static final Logger LOGGER = Logger.getLogger(PasswordResetService.class.getName());

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordResetEmailService emailService;
    private final int expirationMinutes;

    public PasswordResetService(
            UserRepository userRepository,
            PasswordResetTokenRepository tokenRepository,
            PasswordResetEmailService emailService,
            @Value("${app.password-reset.expiration-minutes:15}")
            int expirationMinutes) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.expirationMinutes = expirationMinutes;
    }

    @Transactional
    public String forgotPassword(String email) {

        String normalizedEmail = email.trim().toLowerCase();
        Optional<User> userOptional =
                userRepository.findByEmail(normalizedEmail);
        // Always return the same message if the account does not exist.
        if (userOptional.isEmpty()) {
            return "If an account exists for this email, "
                    + "a password reset link will be sent.";
        }

        User user = userOptional.get();
        // Invalidate previously issued reset tokens.
        tokenRepository.deleteByUser_Id(user.getId());

        // Generate a cryptographically secure random token.
        byte[] randomBytes = new byte[32];
        SECURE_RANDOM.nextBytes(randomBytes);

        String rawToken = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(randomBytes);

        // Store only the SHA-256 hash of the token.
        String tokenHash = hashToken(rawToken);

        LocalDateTime expiryDate =
                LocalDateTime.now().plusMinutes(expirationMinutes);

        PasswordResetToken resetToken = new PasswordResetToken(
                tokenHash,
                user,
                expiryDate
        );

        tokenRepository.save(resetToken);

        try {
            emailService.sendResetEmail(
                    user.getEmail(),
                    rawToken
            );
        } catch (MailException ex) {
            // Do not leave an active token if email delivery fails.
            tokenRepository.delete(resetToken);

            LOGGER.log(
                    Level.WARNING,
                    "Unable to send a password reset email."
            );
        }

        return "If an account exists for this email, "
                + "a password reset link will be sent.";
    }

    private String hashToken(String token) {
        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(
                    token.getBytes(StandardCharsets.UTF_8)
            );

            return HexFormat.of().formatHex(hash);

        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException(
                    "SHA-256 is unavailable", ex
            );
        }
    }
}
