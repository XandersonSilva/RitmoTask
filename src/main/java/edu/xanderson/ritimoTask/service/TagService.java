package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.TagDTO;
import edu.xanderson.ritimoTask.model.entity.TagEntity;
import edu.xanderson.ritimoTask.model.repository.TagRepository;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public void createTag(TagDTO tagDTO){
        tagRepository.save(new TagEntity(tagDTO));
    }
}
