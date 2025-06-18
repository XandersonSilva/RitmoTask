package edu.xanderson.ritimoTask.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long>{
    
}
