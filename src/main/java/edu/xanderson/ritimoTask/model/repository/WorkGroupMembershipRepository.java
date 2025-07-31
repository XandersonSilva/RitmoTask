package edu.xanderson.ritimoTask.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.WorkGroupMembership;

public interface WorkGroupMembershipRepository extends JpaRepository<WorkGroupMembership, Long>{
    List<WorkGroupMembership> findByUserIdAndWorkGroupId(long userId, long workGroupId);
}
