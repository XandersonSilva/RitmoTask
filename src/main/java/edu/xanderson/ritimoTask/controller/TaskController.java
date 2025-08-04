package edu.xanderson.ritimoTask.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import edu.xanderson.ritimoTask.model.DTOs.TagEditDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskCreateDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskEditDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskFilterDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskSummaryDTO;
import edu.xanderson.ritimoTask.model.entity.TaskEntity;
import edu.xanderson.ritimoTask.model.entity.TaskStatus;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.TaskService;
import jakarta.transaction.Transactional;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;


    @PostMapping("/create/boardcolumn/task")
    public ResponseEntity createTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskCreateDTO taskDTO) throws IOException, GeneralSecurityException {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.createBoardColumnTask(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PutMapping("/edite/task")
    public ResponseEntity editeTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) throws IOException, GeneralSecurityException {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.editeTask(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @GetMapping("/get/task")
    public ResponseEntity<TaskSummaryDTO> getTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            TaskSummaryDTO task = taskService.getTask(taskDTO, userId);

            return ResponseEntity.ok(task);

        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/get/tag/task")
    public ResponseEntity<List<TaskSummaryDTO>> getTasksByTag(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TagEditDTO tagDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            List<TaskSummaryDTO> task = taskService.getTasksByTag(tagDTO, userId);

            return ResponseEntity.ok(task);

        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/get/board/tasks")
    public ResponseEntity<List<TaskSummaryDTO>> getBoardTasks(@AuthenticationPrincipal UserEntity currentUser,
                                @RequestParam(value = "boardId", required=true) long boardId) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            List<TaskSummaryDTO> taskDTO = taskService.getBoardTasks(boardId, userId);

            return ResponseEntity.ok(taskDTO);

        }
        return ResponseEntity.badRequest().body(null);
    }


    @DeleteMapping("/delete/task")
    public ResponseEntity deleteTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.deleteTask(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PutMapping("/task/assignUserToTask")
    public ResponseEntity assignUserToTask(@AuthenticationPrincipal UserEntity currentUser, 
                                            @Validated @RequestBody AssignUsersDTO assignUsersDTO) throws IOException, GeneralSecurityException{
        if(currentUser != null){
            long userId = currentUser.getId();

            taskService.AssignUsersToTask(assignUsersDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);
        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");

    }

    @PutMapping("/block/task")
    public ResponseEntity blockTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.blockTask(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PutMapping("/unblock/task")
    public ResponseEntity unblockTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.unblockTask(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PutMapping("/cancel/task")
    public ResponseEntity cancelTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.cancelTask(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }
    @PutMapping("/set/duedate/task")
    public ResponseEntity setTaskDueDate(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.setTaskDueDate(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }


    @PutMapping("/move/task")
    public ResponseEntity moveTaskToCollumn(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            taskService.moveTaskToCollumn(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @GetMapping("/filter/status")
    public ResponseEntity<List<TaskSummaryDTO>> findTasksByStatus(
            @AuthenticationPrincipal UserEntity currentUser,
            @RequestBody TaskFilterDTO filterDTO) {

        if (currentUser != null) {
            long userId = currentUser.getId();
            List<TaskSummaryDTO> tasks = taskService.findTasksByStatus(filterDTO.getBoardId(), filterDTO.getStatus(), userId);
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/filter/canceled")
    public ResponseEntity<List<TaskSummaryDTO>> findCanceledTasks(
            @AuthenticationPrincipal UserEntity currentUser,
            @RequestBody TaskFilterDTO filterDTO) {

        if (currentUser != null) {
            long userId = currentUser.getId();
            List<TaskSummaryDTO> tasks = taskService.findCanceledTasks(filterDTO.getBoardId(), userId);
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/filter/blocked")
    public ResponseEntity<List<TaskSummaryDTO>> findBlockedTasks(
            @AuthenticationPrincipal UserEntity currentUser,
            @RequestBody TaskFilterDTO filterDTO) {

        if (currentUser != null) {
            long userId = currentUser.getId();
            List<TaskSummaryDTO> tasks = taskService.findBlockedTasks(filterDTO.getBoardId(), userId);
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/filter/duedate")
    public ResponseEntity<List<TaskSummaryDTO>> findByDueDateBetween(
            @AuthenticationPrincipal UserEntity currentUser,
            @RequestBody TaskFilterDTO filterDTO) {

        if (currentUser != null) {
            long userId = currentUser.getId();
            List<TaskSummaryDTO> tasks = taskService.findByDueDateBetween(
                filterDTO.getBoardId(), filterDTO.getDueDateStart(), filterDTO.getDueDateEnd(), userId);
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/filter/startdate")
    public ResponseEntity<List<TaskSummaryDTO>> findByStartDateBetween(
            @AuthenticationPrincipal UserEntity currentUser,
            @RequestBody TaskFilterDTO filterDTO) {

        if (currentUser != null) {
            long userId = currentUser.getId();
            List<TaskSummaryDTO> tasks = taskService.findByStartDateBetween(
                filterDTO.getBoardId(), filterDTO.getStartDateStart(), filterDTO.getStartDateEnd(), userId);
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/filter/overdue")
    public ResponseEntity<List<TaskSummaryDTO>> findByOverdue(
            @AuthenticationPrincipal UserEntity currentUser,
            @RequestBody TaskFilterDTO filterDTO) {

        if (currentUser != null) {
            long userId = currentUser.getId();
            List<TaskSummaryDTO> tasks = taskService.findByOverdue(
                filterDTO.getBoardId(), filterDTO.getToday(), userId);
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/filter/memberships")
    public ResponseEntity<List<TaskSummaryDTO>> findByMemberships(
            @AuthenticationPrincipal UserEntity currentUser,
            @RequestBody TaskFilterDTO filterDTO) {

        if (currentUser != null) {
            long userId = currentUser.getId();
            List<TaskSummaryDTO> tasks = taskService.findByMemberships(filterDTO.getBoardId(), userId);
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.badRequest().build();
    }
}