package edu.xanderson.ritimoTask.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.AssignUsersDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskCreateDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskEditDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskSummaryDTO;
import edu.xanderson.ritimoTask.model.entity.ColumnEntity;
import edu.xanderson.ritimoTask.model.entity.TaskAssignedUsersEntity;
import edu.xanderson.ritimoTask.model.entity.TaskEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.ColumnRepository;
import edu.xanderson.ritimoTask.model.repository.TaskAssignedUsersRepository;
import edu.xanderson.ritimoTask.model.repository.TaskRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerifyUserAutority verifyUserAutority;

    @Autowired
    private TaskAssignedUsersRepository taskAssignedUsersRepository;

    @Autowired
    private CalendarService calendarService;

    public void createBoardColumnTask(TaskCreateDTO taskDTO, long userId) throws IOException, GeneralSecurityException{
        UserEntity   user   = userRepository.getReferenceById(userId);
        ColumnEntity column = columnRepository.getReferenceById(taskDTO.getColumnId());
        long boardId = column.getBoard().getId();

        if (verifyUserAutority.verifyUserAutorityBoard(user, boardId)) {
            TaskEntity task = new TaskEntity(taskDTO);
            taskRepository.save(task);
            calendarService.saveTaskOnGoogleCalendar(task.getId(), boardId);
        }
    }

    public TaskSummaryDTO getTask(long taskId, long userId){
        //TODO: Verificar se o usuário tem autoridade para realizar essa ação

        TaskSummaryDTO taskDTO = new TaskSummaryDTO(taskRepository.getReferenceById(taskId));
        return taskDTO;
    }

    public List<TaskSummaryDTO> getBoardTasks(long boardId, long userId){
        //TODO: Verificar se o usuário tem autoridade para realizar essa ação


        List<TaskSummaryDTO> tasksDTO = new ArrayList<>();
        for (TaskEntity task : taskRepository.findTasksByBoardId(boardId)) {
            tasksDTO.add(new TaskSummaryDTO(task));
        }
        return tasksDTO;
    }

    public void editeTask(TaskEditDTO dto, long userId) throws IOException, GeneralSecurityException{
        TaskEntity taskDB = taskRepository.getReferenceById(dto.getId());
        Instant originalBdDueDate = taskDB.getDueDate();
        if(taskDB == null) return; 

        TaskEntity task = new TaskEntity(dto);

        taskRepository.save(task);
        
        if (task.getDueDate() != null && task.getDueDate() != originalBdDueDate) {
            ColumnEntity column = columnRepository.getReferenceById(task.getColumn().getId());
            long boardId = column.getId();
            calendarService.saveTaskOnGoogleCalendar(task.getId(), boardId);
        }
    }

    public void deleteTask(long taskId, long userId){
        TaskEntity task = taskRepository.getReferenceById(taskId);

        taskRepository.delete(task);
    }

    public void blockTask(TaskEditDTO dto, long userId){
        if(dto.getId() == 0) return;     

        TaskEntity task = taskRepository.getReferenceById(dto.getId());
        task.setBlocked(true);

        taskRepository.save(task);
    }

    public void unblockTask(TaskEditDTO dto, long userId){
        if(dto.getId() == 0) return;     
        
        TaskEntity task = taskRepository.getReferenceById(dto.getId());
        task.setBlocked(false);
        
        taskRepository.save(task);
    }
    
    public void cancelTask(TaskEditDTO dto, long userId){
        if(dto.getId() == 0) return;     

        TaskEntity task = taskRepository.getReferenceById(dto.getId());
        task.setCanceled(true);

        taskRepository.save(task);
    }

    public void setTaskDueDate(TaskEditDTO dto, long userId){
        if(dto.getId() == 0) return;
        if(dto.getDueDate() == null || dto.getDueDate().isBefore(Instant.now().plusSeconds(2400))) return;

        TaskEntity task = taskRepository.getReferenceById(dto.getId());
        task.setDueDate(dto.getDueDate());

        taskRepository.save(task);
    }

    public void moveTaskToCollumn(TaskEditDTO dto, long userId){
        if(dto.getId() == 0) return;
        if(dto.getColumnId() == 0 || dto.getColumnId() < 0) return;

        TaskEntity task = taskRepository.getReferenceById(dto.getId());

        ColumnEntity column = new ColumnEntity();
        column.setId(dto.getColumnId());

        task.setColumn(column);

        taskRepository.save(task);
    }

    public void AssignUsersToTask(AssignUsersDTO dto, long userId) throws IOException, GeneralSecurityException{
        UserEntity user = userRepository.getReferenceById(userId);
        if (verifyUserAutority.verifyUserAutorityBoard(user, dto.getBoardId())) {
            TaskAssignedUsersEntity assignedUsersEntity = new TaskAssignedUsersEntity();
            
            TaskEntity task = new TaskEntity();
            task.setId(dto.getTaskId());

            UserEntity userAssigned = new UserEntity();
            userAssigned.setId(dto.getUserId());

            assignedUsersEntity.setTask(task);
            assignedUsersEntity.setUser(userAssigned);

            taskAssignedUsersRepository.save(assignedUsersEntity);

            calendarService.createEvent(userId, task.getId());
        }
    }
}
