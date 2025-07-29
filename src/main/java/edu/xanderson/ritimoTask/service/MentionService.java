package edu.xanderson.ritimoTask.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.MentionDTO;
import edu.xanderson.ritimoTask.model.entity.ColumnEntity;
import edu.xanderson.ritimoTask.model.entity.MentionEntity;
import edu.xanderson.ritimoTask.model.entity.TaskEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.ColumnRepository;
import edu.xanderson.ritimoTask.model.repository.CommentRepository;
import edu.xanderson.ritimoTask.model.repository.MentionRepository;
import edu.xanderson.ritimoTask.model.repository.TaskRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class MentionService {
    @Autowired
    private MentionRepository mentionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private VerifyUserAutority verifyUserAutority;



    public void createBoardColumnTaskCommentMention(MentionDTO mentionDTO, long userId) throws IOException, GeneralSecurityException{
        UserEntity   user   = userRepository.getReferenceById(userId);
        long taskId         = commentRepository.getReferenceById(mentionDTO.getCommentId()).getTask().getId();
        long columnId       = taskRepository.getReferenceById(taskId).getColumn().getId();
        ColumnEntity column = columnRepository.getReferenceById(columnId);
        long boardId        = column.getBoard().getId();

        //TODO:Garantir Que somente o criador do comentario poderá mensionar 
        if (verifyUserAutority.verifyUserAutorityBoard(user, boardId)) {
            mentionRepository.save(new MentionEntity(mentionDTO));
        }
    }
    

}
