package edu.xanderson.ritimoTask.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.entity.NotificationEntity;
import edu.xanderson.ritimoTask.model.entity.NotificationsTypes;
import edu.xanderson.ritimoTask.model.entity.PasswordRecoveryEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.PasswordRecoveryRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class PasswordRecoveryService {
    @Autowired
    private PasswordRecoveryRepository passwordRecoveryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    public void recoverPassword(String email){
        UserEntity user = (UserEntity) userRepository.findByEmail(email);
        PasswordRecoveryEntity passwordRecovery = new PasswordRecoveryEntity();
        PasswordRecoveryEntity UserrecoverPasswordTry = passwordRecoveryRepository.findByUser(user);

        if (UserrecoverPasswordTry != null) {
            passwordRecoveryRepository.delete(UserrecoverPasswordTry);
        }



        //Adiciona o tempo de expiração de 10 min
        Instant expirationDate = Instant.now().plusSeconds(600);
        UUID token = UUID.randomUUID();

        passwordRecovery.setUser(user);
        passwordRecovery.setToken(token);
        passwordRecovery.setExpirationDate(expirationDate);

        passwordRecoveryRepository.save(passwordRecovery);

        //Enviando para o usuário o link para trocar a senha
        final String RECOVERY_LINK = String.format("http://localhost:8080/public/changePassword?token=%s",token);
        String subject = "Recuperar Senha";
        String content = String.format("""
            <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
            <div style="max-width: 600px; margin: auto; background-color: #ffffff; padding: 30px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">

                <h2 style="color: #333;">Recuperação de Senha</h2>
                <p style="font-size: 16px; color: #555;">
                Você solicitou a recuperação da sua senha. Para continuar, clique no botão abaixo ou copie o link manualmente.
                </p>

                <p style="font-size: 16px; color: #555;">
                ⚠️ <strong>Este link é válido por apenas 10 minutos</strong> a partir da geração. Após esse período, será necessário solicitar um novo.
                </p>

                <div style="margin: 30px 0; text-align: center;">
                <a href="%s" style="display: inline-block; padding: 12px 25px; background-color: #007BFF; color: white; text-decoration: none; border-radius: 5px; font-size: 16px;">
                    Recuperar Senha
                </a>
                </div>

                <p style="font-size: 14px; color: #555; text-align: center;">Ou copie o link abaixo:</p>

                <div style="background-color: #f1f1f1; padding: 10px; border-radius: 5px; word-break: break-all; font-size: 14px; color: #333;">
                <span id="recovery-link">%s</span>
                </div>

                
                <div style="text-align: center; margin-top: 10px;">
                <button onclick="navigator.clipboard.writeText(document.getElementById('recovery-link').innerText)" style="margin-top: 10px; padding: 10px 20px; background-color: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer;">
                    Copiar Link
                </button>
                </div>

                <p style="font-size: 14px; color: #888; margin-top: 30px;">
                Se você não solicitou a recuperação de senha, ignore este e-mail. Nenhuma ação será tomada sem sua autorização.
                </p>

                <p style="font-size: 12px; color: #aaa; text-align: center; margin-top: 40px;">
                &copy; 2025 RitmoTask. Todos os direitos reservados.
                </p>

            </div>
            </body>
            """, RECOVERY_LINK, RECOVERY_LINK);
        

        sendEmail(user, subject, content);


    }

    public void changePassword(UUID token, String newPassword){
        PasswordRecoveryEntity passwordRecovery = passwordRecoveryRepository.findByToken(token);
        if (passwordRecovery == null) return;
        if (passwordRecovery.getExpirationDate().isBefore(Instant.now())) return;
        if (! passwordRecovery.getToken().equals(token)) return;

        UserEntity user = passwordRecovery.getUser();

        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);

        passwordRecoveryRepository.delete(passwordRecovery);
        //Avisando o usuário que a senha foi modificada
        String subject = "Senha Recuperada";
        String content = """                
            <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
            <div style="max-width: 600px; margin: auto; background-color: #ffffff; padding: 25px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.05);">

            <h2 style="color: #333;">Sua senha foi alterada</h2>

            <p style="font-size: 16px; color: #555;">
            Informamos que a senha da sua conta foi alterada com sucesso.
            </p>

            <p style="font-size: 16px; color: #555;">
            ✅ Se essa alteração foi feita por você, nenhuma ação adicional é necessária.
            </p>

            <p style="font-size: 16px; color: #c0392b;">
            ⚠️ Se você <strong>não reconhece</strong> essa alteração, recomendamos que redefina sua senha imediatamente e entre em contato com nosso suporte.
            </p>

            <div style="margin-top: 30px; text-align: center;">
            <a href="http://localhost:8080/public/recoverPassword" style="display: inline-block; padding: 10px 20px; background-color: #e74c3c; color: white; text-decoration: none; border-radius: 5px;">
            Redefinir Senha
            </a>
            </div>

            <p style="font-size: 12px; color: #aaa; text-align: center; margin-top: 40px;">
            &copy; 2025 RitmoTask - Todos os direitos reservados.
            </p>

            </div>
            </body>

            """;
        sendEmail(user, subject, content);
    }

    private void sendEmail(UserEntity user, String subject, String content){
        NotificationEntity notification = new NotificationEntity();

        notification.setRecipientEmail(user.getEmail());
        notification.setRecipientUser(user);
        notification.setRecipientUsername(user.getUsername());
        notification.setNotificationsType(NotificationsTypes.ESSENTIAL);
        
        notification.setSubject(subject);
        notification.setContent(content);

        notificationService.sendNotification(notification);
    }
}
