package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.ColumnDTO;
import edu.xanderson.ritimoTask.model.DTOs.CommentDTO;
import edu.xanderson.ritimoTask.model.entity.ColumnEntity;
import edu.xanderson.ritimoTask.model.entity.CommentEntity;
import edu.xanderson.ritimoTask.model.entity.TaskEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.ColumnRepository;
import edu.xanderson.ritimoTask.model.repository.CommentRepository;
import edu.xanderson.ritimoTask.model.repository.TaskRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ColumnRepository columnRepository;

    @Autowired
    TaskRepository taskRepository;
    
    @Autowired
    VerifyUserAutority verifyUserAutority;

    public void createBoardColumnTaskComment(CommentDTO commentDTO, long userId){
        UserEntity   user   = userRepository.getReferenceById(userId);
        TaskEntity   task   = taskRepository.getReferenceById(commentDTO.getTaskId());
        ColumnEntity column = columnRepository.getReferenceById(task.getColumn().getId());

        //Garantindo que o usúario que será responsabilizado 
        //pelo comentario será o que estava informado na requisição
        commentDTO.setUser(user);
        commentDTO.setUserId(userId);
        
        if (verifyUserAutority.verifyUserAutorityBoard(user, column.getBoard().getId())) {
            commentRepository.save(new CommentEntity(commentDTO));
        }
    }

    public void deleteComment(long commentId, long userId){
        CommentEntity comment = commentRepository.getReferenceById(commentId);

        commentRepository.delete(comment);
    }
}
