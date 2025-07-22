package edu.xanderson.ritimoTask.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.BoardEntity;
import edu.xanderson.ritimoTask.model.entity.ColumnEntity;

public interface ColumnRepository extends JpaRepository<ColumnEntity, Long> {
    long countByBoard(BoardEntity board);
    List<ColumnEntity> findByBoardId(long boardId);
}
