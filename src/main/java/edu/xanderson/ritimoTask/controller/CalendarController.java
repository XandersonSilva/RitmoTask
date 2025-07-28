package edu.xanderson.ritimoTask.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.CalendarService;

@RestController
public class CalendarController {
    @Autowired
    CalendarService calendarService;
    
    @PostMapping("/calendar/create/event")
    public ResponseEntity createEvent(@AuthenticationPrincipal UserEntity user) throws IOException, GeneralSecurityException{
        if (user != null) {
            calendarService.createEvent(user.getId());
            return ResponseEntity.ok().body("Evento criado com sucesso!");
        }
            return ResponseEntity.badRequest().body("Falha ao criar evento!");
    }
}
