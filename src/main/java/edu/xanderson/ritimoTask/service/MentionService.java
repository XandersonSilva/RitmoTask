package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.BoardDTO;
import edu.xanderson.ritimoTask.model.DTOs.MentionDTO;
import edu.xanderson.ritimoTask.model.DTOs.TagDTO;
import edu.xanderson.ritimoTask.model.entity.BoardEntity;
import edu.xanderson.ritimoTask.model.entity.BoardMembership;
import edu.xanderson.ritimoTask.model.entity.ColumnEntity;
import edu.xanderson.ritimoTask.model.entity.MentionEntity;
import edu.xanderson.ritimoTask.model.entity.RoleType;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.BoardMembershipRepository;
import edu.xanderson.ritimoTask.model.repository.BoardRepository;
import edu.xanderson.ritimoTask.model.repository.ColumnRepository;
import edu.xanderson.ritimoTask.model.repository.CommentRepository;
import edu.xanderson.ritimoTask.model.repository.MentionRepository;
import edu.xanderson.ritimoTask.model.repository.TaskRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class MentionService {
    @Autowired
    MentionRepository mentionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ColumnRepository columnRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    VerifyUserAutority verifyUserAutority;


    public void createBoardColumnTaskCommentMention(MentionDTO mentionDTO, long userId){
        UserEntity   user   = userRepository.getReferenceById(userId);
        long taskId         = commentRepository.getReferenceById(mentionDTO.getCommentId()).getTask().getId();
        long columnId       = taskRepository.getReferenceById(taskId).getColumn().getId();
        ColumnEntity column = columnRepository.getReferenceById(columnId);

        
        if (verifyUserAutority.verifyUserAutorityBoard(user, column.getBoard().getId())) {
            mentionRepository.save(new MentionEntity(mentionDTO));
        }
    }
    

}
