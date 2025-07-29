package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.CommentDTO;
import edu.xanderson.ritimoTask.model.entity.CommentEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.ColumnRepository;
import edu.xanderson.ritimoTask.model.repository.CommentRepository;
import edu.xanderson.ritimoTask.model.repository.TaskRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import jakarta.transaction.Transactional;

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

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeaderOrMember(#userId, #commentDTO.getBoardId())")
    public void createBoardColumnTaskComment(CommentDTO commentDTO, long userId){
        UserEntity   user   = userRepository.getReferenceById(userId);
        
        //Garantindo que o usúario que será responsabilizado 
        //pelo comentario será o que estava informado na requisição
        commentDTO.setUser(user);
        commentDTO.setUserId(userId);
        
        commentRepository.save(new CommentEntity(commentDTO));
        
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeaderOrMember(#userId, #commentDTO.getBoardId())")
    public void deleteComment(CommentDTO commentDTO, long userId){
        CommentEntity comment = commentRepository.getReferenceById(commentDTO.getId());

        //TODO: Fazer com que apenas administradores lideres e o usuário que criou o comentario possa remove-lo

        commentRepository.delete(comment);
    }
}
