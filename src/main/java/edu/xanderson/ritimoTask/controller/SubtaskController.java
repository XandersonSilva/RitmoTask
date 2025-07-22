package edu.xanderson.ritimoTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.ritimoTask.model.DTOs.SubTaskCreateDTO;
import edu.xanderson.ritimoTask.model.DTOs.SubTaskEditDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.SubtaskService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class SubtaskController {
    @Autowired
    private SubtaskService subtaskService;

    @PostMapping("/create/subtask")
    public ResponseEntity createSubtask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody SubTaskCreateDTO subtaskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
            
            subtaskService.createSubTask(subtaskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PutMapping("/edit/subtask")
    public ResponseEntity editSubtask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody SubTaskEditDTO subtaskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
            
            subtaskService.editSubTask(subtaskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @DeleteMapping("/delete/subtask")
    public ResponseEntity deleteSubtask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody SubTaskEditDTO subtaskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            subtaskService.deleteSubTask(subtaskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }
}
