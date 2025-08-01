package edu.xanderson.ritimoTask.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.NotificationDTO;
import edu.xanderson.ritimoTask.model.entity.NotificationEntity;
import edu.xanderson.ritimoTask.model.repository.NotificationRepository;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    EmailService emailService;
    @Value("${spring.mail.username}")
    private String sender;

    @Async
    public void sendNotification(NotificationEntity notification){
        try {
            emailService.sendTextMail(
                notification.getRecipientEmail(), 
                notification.getSubject(), 
                notification.getContent());
                notification.setSender(notification.getSender() == null ? 
                                        sender :
                                        notification.getSender());

            notification.setSended(true);
            System.out.println("email enviado");
            notificationRepository.save(notification);
        } catch (Exception e) {
            notification.setSended(false);
            notificationRepository.save(notification);

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
}
