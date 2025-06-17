package edu.xanderson.ritimoTask.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.WorkGroupEntity;

public interface WorkGroupRepository extends JpaRepository<WorkGroupEntity, Long> {
    
}
