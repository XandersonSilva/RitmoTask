package edu.xanderson.ritimoTask.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.calendar.model.Event;

import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.CalendarService;

@RestController
public class CalendarController {
    @Autowired
    CalendarService calendarService;
    
    @PostMapping("/teste/calendar")
    public ResponseEntity<List<Event>> listarEventos(@AuthenticationPrincipal UserEntity user) throws IOException{
        List<Event> events = calendarService.listarEventos(user.getId());  
        return ResponseEntity.ok().body(events);
    }
}
