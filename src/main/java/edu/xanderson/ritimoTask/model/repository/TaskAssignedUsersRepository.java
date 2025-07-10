package edu.xanderson.ritimoTask.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.TaskAssignedUsersEntity;

public interface TaskAssignedUsersRepository extends JpaRepository<TaskAssignedUsersEntity, Long>{

}
