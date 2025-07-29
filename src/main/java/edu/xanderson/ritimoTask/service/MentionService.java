package edu.xanderson.ritimoTask.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.MentionDTO;
import edu.xanderson.ritimoTask.model.entity.MentionEntity;
import edu.xanderson.ritimoTask.model.repository.MentionRepository;
import jakarta.transaction.Transactional;

@Service
public class MentionService {
    @Autowired
    private MentionRepository mentionRepository;

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeaderOrMember(#userId, #mentionDTO.getBoardId())")
    public void createBoardColumnTaskCommentMention(MentionDTO mentionDTO, long userId) throws IOException, GeneralSecurityException{

        //TODO:Garantir Que somente o criador do comentario poderá mensionar 
        mentionRepository.save(new MentionEntity(mentionDTO));
    }
    

}
