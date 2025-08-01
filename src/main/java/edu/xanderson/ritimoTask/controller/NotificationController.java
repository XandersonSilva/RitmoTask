package edu.xanderson.ritimoTask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.ritimoTask.model.DTOs.NotificationDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.NotificationService;

@RestController
public class NotificationController {
    @Autowired
    NotificationService  notificationService;
    
    @GetMapping("/get/notifications")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByUser(@AuthenticationPrincipal UserEntity user){
        if (user != null) {
            List<NotificationDTO> notications = notificationService.getNotificationsByUser(user.getId());
            return ResponseEntity.ok().body(notications);
        }

        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping("/set/readed/notification")
    public ResponseEntity<String> setNotificationAsRead(@AuthenticationPrincipal UserEntity user,
                                                                        @Validated @RequestBody NotificationDTO notification){
        if (user != null) {
            notificationService.setNotificationAsRead(notification, user.getId());
            return ResponseEntity.ok().body("Notificação marcada como lida!");
        }

        return ResponseEntity.badRequest().body(null);
    }
}
