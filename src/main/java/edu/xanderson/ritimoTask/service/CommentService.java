package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.CommentDTO;
import edu.xanderson.ritimoTask.model.entity.CommentEntity;
import edu.xanderson.ritimoTask.model.repository.CommentRepository;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public void createComment(CommentDTO commentDTO){
        commentRepository.save(new CommentEntity(commentDTO));
    }
}
