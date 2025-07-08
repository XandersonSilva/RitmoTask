package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.OrganizationDTO;
import edu.xanderson.ritimoTask.model.DTOs.TagDTO;
import edu.xanderson.ritimoTask.model.entity.ColumnEntity;
import edu.xanderson.ritimoTask.model.entity.OrganizationEntity;
import edu.xanderson.ritimoTask.model.entity.TagEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.ColumnRepository;
import edu.xanderson.ritimoTask.model.repository.TagRepository;
import edu.xanderson.ritimoTask.model.repository.TaskRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ColumnRepository columnRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    VerifyUserAutority verifyUserAutority;

    public void createBoardColumnTaskTag(TagDTO tagDTO, long userId){
        UserEntity   user   = userRepository.getReferenceById(userId);
        long columnId       = taskRepository.getReferenceById(tagDTO.getTaskId()).getColumn().getId();
        ColumnEntity column = columnRepository.getReferenceById(columnId);

        
        if (verifyUserAutority.verifyUserAutorityBoard(user, column.getBoard().getId())) {
            tagRepository.save(new TagEntity(tagDTO));
        }
    }

    public void editeTag(TagDTO dto, long userId){
        if(dto.getId() == 0) return;     

        TagEntity tag = new TagEntity(dto);

        tagRepository.save(tag);
    }

    public void deleteTag(long tagId, long userId){
        TagEntity tag = tagRepository.getReferenceById(tagId);

        tagRepository.delete(tag);
    }
}
