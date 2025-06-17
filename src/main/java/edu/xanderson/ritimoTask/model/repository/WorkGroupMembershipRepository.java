package edu.xanderson.ritimoTask.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.WorkGroupMembership;

public interface WorkGroupMembershipRepository extends JpaRepository<WorkGroupMembership, Long>{
    
}
