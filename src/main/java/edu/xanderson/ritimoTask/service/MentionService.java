package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.MentionDTO;
import edu.xanderson.ritimoTask.model.entity.MentionEntity;
import edu.xanderson.ritimoTask.model.repository.MentionRepository;

@Service
public class MentionService {
    @Autowired
    MentionRepository mentionRepository;

    public void createMention(MentionDTO mentionDTO){
        System.out.println(mentionDTO);
        mentionRepository.save(new MentionEntity(mentionDTO));
    }
}
