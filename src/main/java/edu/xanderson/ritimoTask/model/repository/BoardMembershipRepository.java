package edu.xanderson.ritimoTask.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.BoardEntity;
import edu.xanderson.ritimoTask.model.entity.BoardMembership;
import edu.xanderson.ritimoTask.model.entity.UserEntity;

public interface BoardMembershipRepository extends JpaRepository<BoardMembership, Long>{
    Optional<BoardMembership> findByUserAndBoard(UserEntity user, BoardEntity board);
}
