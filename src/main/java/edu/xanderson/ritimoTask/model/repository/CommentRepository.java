package edu.xanderson.ritimoTask.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
    
}
