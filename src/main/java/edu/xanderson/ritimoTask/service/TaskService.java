package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.AssignUsersDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskDTO;
import edu.xanderson.ritimoTask.model.entity.ColumnEntity;
import edu.xanderson.ritimoTask.model.entity.TaskAssignedUsersEntity;
import edu.xanderson.ritimoTask.model.entity.TaskEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.BoardRepository;
import edu.xanderson.ritimoTask.model.repository.ColumnRepository;
import edu.xanderson.ritimoTask.model.repository.TaskAssignedUsersRepository;
import edu.xanderson.ritimoTask.model.repository.TaskRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ColumnRepository columnRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    VerifyUserAutority verifyUserAutority;

    @Autowired
    TaskAssignedUsersRepository taskAssignedUsersRepository;

    public void createBoardColumnTask(TaskDTO taskDTO, long userId){
        UserEntity   user   = userRepository.getReferenceById(userId);
        ColumnEntity column = columnRepository.getReferenceById(taskDTO.getColumnId());

        if (verifyUserAutority.verifyUserAutorityBoard(user, column.getBoard().getId())) {
            TaskEntity task = new TaskEntity(taskDTO);
            taskRepository.save(task);
        }
    }

    public void editeTask(TaskDTO dto, long userId){
        if(dto.getId() == 0) return;     

        TaskEntity task = new TaskEntity(dto);

        taskRepository.save(task);
    }

    public void deleteTask(long taskId, long userId){
        TaskEntity task = taskRepository.getReferenceById(taskId);

        taskRepository.delete(task);
    }

    public void AssignUsersToTask(AssignUsersDTO dto, long userId){
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
        }

    }
}
