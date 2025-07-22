package edu.xanderson.ritimoTask.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.xanderson.ritimoTask.model.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
    @Query("SELECT b FROM BoardEntity b JOIN b.memberships m WHERE m.user.id = :userId")
    List<BoardEntity> findBoardsByUser(long userId);

    List<BoardEntity> findByWorkGroupId(long workGroupId);
}
