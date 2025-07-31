package edu.xanderson.ritimoTask.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.xanderson.ritimoTask.model.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long>{
    @Query("SELECT t FROM TaskEntity t JOIN t.column c WHERE c.board.id = :boardId")
    List<TaskEntity> findTasksByBoardId(@Param("boardId") long boardId);

    @Query("SELECT tk FROM TaskEntity tk JOIN tk.tags tg WHERE tg.tag.id = :tagId")
    List<TaskEntity> findTasksByTagId(@Param("tagId") long tagId);
}
