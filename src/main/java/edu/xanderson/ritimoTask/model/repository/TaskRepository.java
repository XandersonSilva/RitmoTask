package edu.xanderson.ritimoTask.model.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.xanderson.ritimoTask.model.entity.TaskEntity;
import edu.xanderson.ritimoTask.model.entity.TaskStatus;

public interface TaskRepository extends JpaRepository<TaskEntity, Long>{
    @Query("SELECT t FROM TaskEntity t JOIN t.column c WHERE c.board.id = :boardId")
    List<TaskEntity> findTasksByBoardId(@Param("boardId") long boardId);

    @Query("SELECT tk FROM TaskEntity tk JOIN tk.tags tg WHERE tg.tag.id = :tagId")
    List<TaskEntity> findTasksByTagId(@Param("tagId") long tagId);

    @Query("SELECT t FROM TaskEntity t JOIN t.column c WHERE c.board.id = :boardId AND t.status = :status")
    List<TaskEntity> findTasksByStatus(@Param("boardId") long boardId, @Param("status") TaskStatus status);

    @Query("SELECT t FROM TaskEntity t JOIN t.column c WHERE c.board.id = :boardId AND t.isCanceled = true")
    List<TaskEntity> findCanceledTasks(@Param("boardId") long boardId);

    @Query("SELECT t FROM TaskEntity t JOIN t.column c WHERE c.board.id = :boardId AND t.isBlocked = true")
    List<TaskEntity> findBlockedTasks(@Param("boardId") long boardId);

    @Query("SELECT t FROM TaskEntity t JOIN t.column c WHERE c.board.id = :boardId AND t.dueDate BETWEEN :inicio AND :fim")
    List<TaskEntity> findByDueDateBetween(long boardId, Instant inicio, Instant fim);

    @Query("SELECT t FROM TaskEntity t JOIN t.column c WHERE c.board.id = :boardId AND t.startDate BETWEEN :inicio AND :fim")
    List<TaskEntity> findByStartDateBetween(long boardId, Instant inicio, Instant fim);

    @Query("SELECT t FROM TaskEntity t JOIN t.column c WHERE c.board.id = :boardId AND t.dueDate < :today")
    List<TaskEntity> findByOverdue(long boardId, Instant today);

    @Query("SELECT t FROM TaskEntity t JOIN t.column c JOIN t.memberships m WHERE c.board.id = :boardId AND m.user.id = :userId")
    List<TaskEntity> findByMemberships(long boardId, long userId);
   
}
