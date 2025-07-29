package edu.xanderson.ritimoTask.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import edu.xanderson.ritimoTask.model.entity.BoardMembership;

public interface BoardMembershipRepository extends JpaRepository<BoardMembership, Long>{

    List<BoardMembership> findByUserIdAndBoardId(long userId, long board);

    List<BoardMembership> findByBoardId(long boardId);
}
