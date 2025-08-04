package edu.xanderson.ritimoTask.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.AssignUsersDTO;
import edu.xanderson.ritimoTask.model.DTOs.TagEditDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskCreateDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskEditDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskSummaryDTO;
import edu.xanderson.ritimoTask.model.entity.ColumnEntity;
import edu.xanderson.ritimoTask.model.entity.NotificationEntity;
import edu.xanderson.ritimoTask.model.entity.NotificationsTypes;
import edu.xanderson.ritimoTask.model.entity.TaskAssignedUsersEntity;
import edu.xanderson.ritimoTask.model.entity.TaskEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.ColumnRepository;
import edu.xanderson.ritimoTask.model.repository.TaskAssignedUsersRepository;
import edu.xanderson.ritimoTask.model.repository.TaskRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class TaskService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private TaskAssignedUsersRepository taskAssignedUsersRepository;

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeaderOrMember(#userId, #taskDTO.getBoardId())")
    public void createBoardColumnTask(TaskCreateDTO taskDTO, long userId) throws IOException, GeneralSecurityException{
        TaskEntity task = new TaskEntity(taskDTO);
        taskRepository.save(task);
        calendarService.saveTaskOnGoogleCalendar(task.getId(), taskDTO.getBoardId());
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeaderOrMemberOrGuest(#userId, #taskDTO.getBoardId())")
    public TaskSummaryDTO getTask(TaskEditDTO taskDTO, long userId){

        TaskSummaryDTO task = new TaskSummaryDTO(taskRepository.getReferenceById(taskDTO.getId()));
        return task;
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeaderOrMemberOrGuest(#userId, #boardId)")
    public List<TaskSummaryDTO> getBoardTasks(long boardId, long userId){


        List<TaskSummaryDTO> tasksDTO = new ArrayList<>();
        for (TaskEntity task : taskRepository.findTasksByBoardId(boardId)) {
            tasksDTO.add(new TaskSummaryDTO(task));
        }
        return tasksDTO;
    }
    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeaderOrMemberOrGuest(#userId, #dto.getBoardId())")
    public List<TaskSummaryDTO> getTasksByTag(TagEditDTO dto, long userId){
        List<TaskSummaryDTO> tasksDTO = new ArrayList<>();
        for (TaskEntity task : taskRepository.findTasksByTagId(dto.getId())) {
            tasksDTO.add(new TaskSummaryDTO(task));
        }
        return tasksDTO;
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeaderOrMember(#userId, #taskDTO.getBoardId())")
    public void editeTask(TaskEditDTO taskDTO, long userId) throws IOException, GeneralSecurityException{
        TaskEntity taskDB = taskRepository.getReferenceById(taskDTO.getId());
        Instant originalBdDueDate = taskDB.getDueDate();
        if(taskDB == null) return; 

        TaskEntity task = new TaskEntity(taskDTO);

        taskRepository.save(task);
        
        if (task.getDueDate() != null && task.getDueDate() != originalBdDueDate) {
            ColumnEntity column = columnRepository.getReferenceById(task.getColumn().getId());
            long boardId = column.getId();
            calendarService.saveTaskOnGoogleCalendar(task.getId(), boardId);
        }
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeader(#userId, #taskDTO.getBoardId())")
    public void deleteTask(TaskEditDTO taskDTO, long userId){
        TaskEntity task = taskRepository.getReferenceById(taskDTO.getId());

        taskRepository.delete(task);
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeader(#userId, #taskDTO.getBoardId())")
    public void blockTask(TaskEditDTO taskDTO, long userId){
        if(taskDTO.getId() == 0) return;     

        TaskEntity task = taskRepository.getReferenceById(taskDTO.getId());
        task.setBlocked(true);

        taskRepository.save(task);
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeader(#userId, #taskDTO.getBoardId())")
    public void unblockTask(TaskEditDTO taskDTO, long userId){
        if(taskDTO.getId() == 0) return;     
        
        TaskEntity task = taskRepository.getReferenceById(taskDTO.getId());
        task.setBlocked(false);
        
        taskRepository.save(task);
    }
    
    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeader(#userId, #taskDTO.getBoardId())")
    public void cancelTask(TaskEditDTO taskDTO, long userId){
        if(taskDTO.getId() == 0) return;     

        TaskEntity task = taskRepository.getReferenceById(taskDTO.getId());
        task.setCanceled(true);

        taskRepository.save(task);
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeader(#userId, #taskDTO.getBoardId())")
    public void setTaskDueDate(TaskEditDTO taskDTO, long userId){
        if(taskDTO.getId() == 0) return;
        if(taskDTO.getDueDate() == null || taskDTO.getDueDate().isBefore(Instant.now().plusSeconds(2400))) return;

        TaskEntity task = taskRepository.getReferenceById(taskDTO.getId());
        task.setDueDate(taskDTO.getDueDate());

        taskRepository.save(task);
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeaderOrMember(#userId, #taskDTO.getBoardId())")
    public void moveTaskToCollumn(TaskEditDTO taskDTO, long userId){
        if(taskDTO.getId() == 0) return;
        if(taskDTO.getColumnId() == 0 || taskDTO.getColumnId() < 0) return;

        TaskEntity task = taskRepository.getReferenceById(taskDTO.getId());

        ColumnEntity column = new ColumnEntity();
        column.setId(taskDTO.getColumnId());

        task.setColumn(column);

        taskRepository.save(task);
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeader(#userId, #dto.getBoardId())")
    public void AssignUsersToTask(AssignUsersDTO dto, long userId) throws IOException, GeneralSecurityException{
        
        TaskAssignedUsersEntity assignedUsersEntity = new TaskAssignedUsersEntity();
        
        TaskEntity task = new TaskEntity();
        task.setId(dto.getTaskId());

        UserEntity userAssigned = new UserEntity();
        userAssigned.setId(dto.getUserId());

        assignedUsersEntity.setTask(task);
        assignedUsersEntity.setUser(userAssigned);

        taskAssignedUsersRepository.save(assignedUsersEntity);

        calendarService.createEvent(userId, task.getId());
    
        //Avisando ao usuário que ele é responsavel por uma tarefa

        UserEntity user = userRepository.getReferenceById(dto.getUserId());
        UserEntity leader = userRepository.getReferenceById(userId);
        
        NotificationEntity notification = new NotificationEntity();

        notification.setRecipientEmail(user.getEmail());
        notification.setRecipientUser(user);
        notification.setRecipientUsername(user.getUsername());
        notification.setNotificationsType(NotificationsTypes.ESSENTIAL_AND_UPDATES);
        
        notification.setSubject("Você foi atribuido a uma task por " + leader.getName());
        notification.setContent("Você foi atribuido a uma task por " + leader.getName());

        notificationService.sendNotification(notification);
    }
}
