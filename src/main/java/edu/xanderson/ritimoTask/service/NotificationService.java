package edu.xanderson.ritimoTask.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.NotificationDTO;
import edu.xanderson.ritimoTask.model.entity.NotificationEntity;
import edu.xanderson.ritimoTask.model.entity.NotificationsTypes;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.NotificationRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class NotificationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.username}")
    private String sender;

    @Async
    public void sendNotification(NotificationEntity notification){
        notification.setSender(notification.getSender() == null ? 
                                        sender :
                                        notification.getSender());
        notification.setSended(false);
        notificationRepository.save(notification);

        if (! verifyIfcanSendNotification(notification)) return;
        try {
            emailService.sendTextMail(
                notification.getRecipientEmail(), 
                notification.getSubject(), 
                notification.getContent());

            notification.setSended(true);
            System.out.println("email enviado");
            notificationRepository.save(notification);
        } catch (Exception e) {
            System.out.println("Erro ao enviar notificação \n" + e);
        }
    }

    public List<NotificationDTO> getNotificationsByUser(long userId){
        List<NotificationDTO> notifications = new ArrayList<>();

        for (NotificationEntity notification : notificationRepository.findByRecipientUserId(userId)) {
            notifications.add(new NotificationDTO(notification));
        }
        
        return notifications;
    }
    public void setNotificationAsRead(NotificationDTO dto, long userId){
        NotificationEntity notification = notificationRepository.getReferenceById(dto.getId());
        if (notification.getRecipientUser().getId() != userId) return;
        
        notification.setReaded(true);
        notificationRepository.save(notification);
    }

    private boolean verifyIfcanSendNotification(NotificationEntity notification){
        UserEntity user = (UserEntity) userRepository.findByEmail(notification.getRecipientEmail());
        if (user.getNotificationsPreferences() == NotificationsTypes.ALL) return true;
        if (notification.getNotificationsType() == NotificationsTypes.ESSENTIAL) return true;
        if (user.getNotificationsPreferences() == notification.getNotificationsType()) return true;
        
        return false;
    }
}
