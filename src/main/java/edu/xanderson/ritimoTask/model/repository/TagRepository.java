package edu.xanderson.ritimoTask.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Long>{
    
}
