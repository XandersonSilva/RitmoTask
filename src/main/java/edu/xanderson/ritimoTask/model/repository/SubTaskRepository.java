package edu.xanderson.ritimoTask.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.SubTaskEntity;

public interface SubTaskRepository extends JpaRepository<SubTaskEntity, Long>{

    
}
