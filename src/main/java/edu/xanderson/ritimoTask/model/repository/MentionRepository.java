package edu.xanderson.ritimoTask.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.MentionEntity;

public interface MentionRepository extends JpaRepository<MentionEntity, Long>{
    
}
