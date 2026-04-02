package edu.xanderson.ritimoTask.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.ritimoTask.model.DTOs.AssignUsersDTO;
import edu.xanderson.ritimoTask.model.DTOs.TagEditDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskCreateDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskEditDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskFilterDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskSummaryDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @PostMapping("/create/boardcolumn/task")
    public ResponseEntity createTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskCreateDTO taskDTO) throws IOException, GeneralSecurityException {
        if (currentUser != null) {
            long userId = currentUser.getId();
            taskService.createBoardColumnTask(taskDTO, userId);
            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);
        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    // Corrigido typo (edite -> edit) e alterado para PATCH (atualização parcial)
    @PatchMapping("/edit/task")
    public ResponseEntity editTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) throws IOException, GeneralSecurityException {
        if (currentUser != null) {
            long userId = currentUser.getId();
            taskService.editeTask(taskDTO, userId); // Pode atualizar o nome do método no service para 'editTask' depois
            return ResponseEntity.ok().body("Tentativa de edição de recurso via user: " + userId);
        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    // Alterado de @RequestBody para @ModelAttribute (parâmetros de query)
    @GetMapping("/get/task")
    public ResponseEntity<TaskSummaryDTO> getTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @ModelAttribute TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId();
            TaskSummaryDTO task = taskService.getTask(taskDTO, userId);
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // Alterado de @RequestBody para @ModelAttribute (parâmetros de query)
    @GetMapping("/get/tag/task")
    public ResponseEntity<List<TaskSummaryDTO>> getTasksByTag(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @ModelAttribute TagEditDTO tagDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId();
            List<TaskSummaryDTO> task = taskService.getTasksByTag(tagDTO, userId);
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/get/board/tasks")
    public ResponseEntity<List<TaskSummaryDTO>> getBoardTasks(@AuthenticationPrincipal UserEntity currentUser,
                                @RequestParam(value = "boardId", required=true) long boardId) {
        if (currentUser != null) {
            long userId = currentUser.getId();
            List<TaskSummaryDTO> taskDTO = taskService.getBoardTasks(boardId, userId);
            return ResponseEntity.ok(taskDTO);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // Nota: DELETE com RequestBody funciona em alguns clients, mas não é recomendado. 
    // Mantive como está para não quebrar seu front-end atual, mas considere mudar para @RequestParam no futuro.
    @DeleteMapping("/delete/task")
    public ResponseEntity deleteTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId();
            taskService.deleteTask(taskDTO, userId);
            return ResponseEntity.ok().body("Tentativa de deleção de recurso via user: " + userId);
        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PutMapping("/task/assignUserToTask")
    public ResponseEntity assignUserToTask(@AuthenticationPrincipal UserEntity currentUser, 
                                            @Validated @RequestBody AssignUsersDTO assignUsersDTO) throws IOException, GeneralSecurityException{
        if(currentUser != null){
            long userId = currentUser.getId();
            taskService.AssignUsersToTask(assignUsersDTO, userId);
            return ResponseEntity.ok().body("Tentativa de atribuição de recurso via user: " + userId);
        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PutMapping("/block/task")
    public ResponseEntity blockTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId();
            taskService.blockTask(taskDTO, userId);
            return ResponseEntity.ok().body("Tentativa de bloqueio de recurso via user: " + userId);
        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PutMapping("/unblock/task")
    public ResponseEntity unblockTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId();
            taskService.unblockTask(taskDTO, userId);
            return ResponseEntity.ok().body("Tentativa de desbloqueio de recurso via user: " + userId);
        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PutMapping("/cancel/task")
    public ResponseEntity cancelTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId();
            taskService.cancelTask(taskDTO, userId);
            return ResponseEntity.ok().body("Tentativa de cancelamento via user: " + userId);
        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PutMapping("/set/duedate/task")
    public ResponseEntity setTaskDueDate(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId();
            taskService.setTaskDueDate(taskDTO, userId);
            return ResponseEntity.ok().body("Tentativa de alteração de data via user: " + userId);
        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PutMapping("/move/task")
    public ResponseEntity moveTaskToCollumn(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskEditDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId();
            taskService.moveTaskToCollumn(taskDTO, userId);
            return ResponseEntity.ok().body("Tentativa de mover task via user: " + userId);
        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    // --- ROTAS DE FILTRO: Alteradas de @RequestBody para @ModelAttribute ---

    @GetMapping("/filter/status")
    public ResponseEntity<List<TaskSummaryDTO>> findTasksByStatus(
            @AuthenticationPrincipal UserEntity currentUser,
            @ModelAttribute TaskFilterDTO filterDTO) {
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
            @ModelAttribute TaskFilterDTO filterDTO) {
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
            @ModelAttribute TaskFilterDTO filterDTO) {
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
            @ModelAttribute TaskFilterDTO filterDTO) {
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
            @ModelAttribute TaskFilterDTO filterDTO) {
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
            @ModelAttribute TaskFilterDTO filterDTO) {
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
            @ModelAttribute TaskFilterDTO filterDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId();
            List<TaskSummaryDTO> tasks = taskService.findByMemberships(filterDTO.getBoardId(), userId);
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.badRequest().build();
    }
}