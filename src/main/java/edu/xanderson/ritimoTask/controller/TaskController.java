package edu.xanderson.ritimoTask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.ritimoTask.model.DTOs.AssignUsersDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskCreateDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskEditDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskSummaryDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.TaskService;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;


    @PostMapping("/create/boardcolumn/task")
    public ResponseEntity createTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskCreateDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.createBoardColumnTask(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PutMapping("/edite/task")
    public ResponseEntity editeTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.editeTask(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @GetMapping("/get/task")
    public ResponseEntity<TaskSummaryDTO> getTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @RequestParam(value = "taskId", required=true) long taskId) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            TaskSummaryDTO taskDTO = taskService.getTask(taskId, userId);

            return ResponseEntity.ok(taskDTO);

        }
        return (ResponseEntity<TaskSummaryDTO>) ResponseEntity.badRequest();
    }
    @GetMapping("/get/board/tasks")
    public ResponseEntity<List<TaskSummaryDTO>> getBoardTasks(@AuthenticationPrincipal UserEntity currentUser,
                                @RequestParam(value = "boardId", required=true) long boardId) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            List<TaskSummaryDTO> taskDTO = taskService.getBoardTasks(boardId, userId);

            return ResponseEntity.ok(taskDTO);

        }
        return (ResponseEntity<List<TaskSummaryDTO>>) ResponseEntity.badRequest();
    }


    @DeleteMapping("/delete/task")
    public ResponseEntity deleteTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @RequestParam(value = "taskId", required=true) long taskId) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.deleteTask(taskId, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PostMapping("/task/assignUserToTask")
    public ResponseEntity assignUserToTask(@AuthenticationPrincipal UserEntity currentUser, 
                                            @Validated @RequestBody AssignUsersDTO assignUsersDTO){
        if(currentUser != null){
            long userId = currentUser.getId();

            taskService.AssignUsersToTask(assignUsersDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);
        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");

    }

    @PostMapping("/block/task")
    public ResponseEntity blockTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.blockTask(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PostMapping("/unblock/task")
    public ResponseEntity unblockTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.unblockTask(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PostMapping("/cancel/task")
    public ResponseEntity cancelTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.cancelTask(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }
    @PostMapping("/set/duedate/task")
    public ResponseEntity setTaskDueDate(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.setTaskDueDate(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }


    @PostMapping("/move/task")
    public ResponseEntity moveTaskToCollumn(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.moveTaskToCollumn(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }
}