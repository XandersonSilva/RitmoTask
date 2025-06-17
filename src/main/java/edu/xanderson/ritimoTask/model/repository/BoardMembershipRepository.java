package edu.xanderson.ritimoTask.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.BoardMembership;

public interface BoardMembershipRepository extends JpaRepository<BoardMembership, Long>{
    
}
