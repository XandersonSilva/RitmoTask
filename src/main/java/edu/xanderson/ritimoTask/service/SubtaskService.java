package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.SubTaskCreateDTO;
import edu.xanderson.ritimoTask.model.DTOs.SubTaskEditDTO;
import edu.xanderson.ritimoTask.model.entity.SubTaskEntity;
import edu.xanderson.ritimoTask.model.repository.SubTaskRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class SubtaskService {
    @Autowired
    UserRepository userRepository;

    @Autowired 
    SubTaskRepository subtaskRepository;

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeaderOrMember(#userId, #subtaskDTO.getBoardId())")
    public void createSubTask(SubTaskCreateDTO subtaskDTO, long userId){
        SubTaskEntity subtask = new SubTaskEntity(subtaskDTO);

        subtaskRepository.save(subtask);
        
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeaderOrMember(#userId, #subtaskDTO.getBoardId())")
    public void editSubTask(SubTaskEditDTO subtaskDTO, long userId){
        //TODO: Fazer ajustes necessarios pra evitar perda de dados
        
        SubTaskEntity subtask = new SubTaskEntity(subtaskDTO);

        subtaskRepository.save(subtask);
        
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeaderOrMember(#userId, #subtaskDTO.getBoardId())")
    public void deleteSubTask(SubTaskEditDTO subtaskDTO, long userId){

        if (subtaskDTO.getId() == 0 || subtaskDTO.getId() < 0) return;

        subtaskRepository.deleteById(subtaskDTO.getId());
        
    }
}
