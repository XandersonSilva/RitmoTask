package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.TagDTO;
import edu.xanderson.ritimoTask.model.DTOs.TagEditDTO;
import edu.xanderson.ritimoTask.model.DTOs.TagSummaryDTO;
import edu.xanderson.ritimoTask.model.entity.TagEntity;
import edu.xanderson.ritimoTask.model.repository.ColumnRepository;
import edu.xanderson.ritimoTask.model.repository.TagRepository;
import edu.xanderson.ritimoTask.model.repository.TaskRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import jakarta.transaction.Transactional;

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

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeaderOrMember(#userId, #tagDTO.getBoardId())")
    public TagSummaryDTO getTag(TagEditDTO tagDTO, long userId){        
        return new TagSummaryDTO(tagRepository.getById(tagDTO.getId()));
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeaderOrMember(#userId, #tagDTO.getBoardId())")
    public void createTag(TagDTO tagDTO, long userId){        
        tagRepository.save(new TagEntity(tagDTO));
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeaderOrMember(#userId, #tagDTO.getBoardId())")
    public void editeTag(TagEditDTO tagDTO, long userId){
        if(tagDTO.getId() == 0) return;     

        TagEntity tag = new TagEntity(tagDTO);

        tagRepository.save(tag);
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsLeaderOrMember(#userId, #tagDTO.getBoardId())")
    public void deleteTag(TagEditDTO tagDTO, long userId){
        TagEntity tag = tagRepository.getReferenceById(tagDTO.getId());

        tagRepository.delete(tag);
    }
}
