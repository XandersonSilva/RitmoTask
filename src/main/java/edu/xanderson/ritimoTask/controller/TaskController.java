package edu.xanderson.ritimoTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.ritimoTask.model.DTOs.BoardDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.TaskService;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;


    @PostMapping("/create/boardcolumn/task")
    public ResponseEntity createTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.createBoardColumnTask(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PostMapping("/edite/task")
    public ResponseEntity editeTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.editeTask(taskDTO, userId);

            return ResponseEntity.ok().body("Criando quadro para o usuário com ID: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PostMapping("/delete/task")
    public ResponseEntity deleteTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @RequestParam(value = "taskId", required=true) long taskId) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.deleteTask(taskId, userId);

            return ResponseEntity.ok().body("Deletando quadro para o usuário com ID: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }
}