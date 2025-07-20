package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.SubTaskCreateDTO;
import edu.xanderson.ritimoTask.model.DTOs.SubTaskEditDTO;
import edu.xanderson.ritimoTask.model.entity.SubTaskEntity;
import edu.xanderson.ritimoTask.model.repository.SubTaskRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class SubtaskService {
    @Autowired
    UserRepository userRepository;

    @Autowired 
    SubTaskRepository subtaskRepository;

    public void createSubTask(SubTaskCreateDTO subtaskDTO, long userId){
        //TODO: Verificar se o usuário tem autoridade para realizar essa ação
        SubTaskEntity subtask = new SubTaskEntity(subtaskDTO);

        subtaskRepository.save(subtask);
        
    }
    public void deleteSubTask(SubTaskEditDTO subtaskDTO, long userId){
        //TODO: Verificar se o usuário tem autoridade para realizar essa ação

        if (subtaskDTO.getId() == 0 || subtaskDTO.getId() < 0) return;

        subtaskRepository.deleteById(subtaskDTO.getId());
        
    }
}
