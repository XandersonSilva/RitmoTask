package edu.xanderson.ritimoTask.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
    
}
