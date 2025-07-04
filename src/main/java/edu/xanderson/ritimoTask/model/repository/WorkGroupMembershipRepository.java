package edu.xanderson.ritimoTask.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.entity.WorkGroupEntity;
import edu.xanderson.ritimoTask.model.entity.WorkGroupMembership;

public interface WorkGroupMembershipRepository extends JpaRepository<WorkGroupMembership, Long>{
    Optional<WorkGroupMembership> findByUserAndWorkGroup(UserEntity user, WorkGroupEntity workGroup);
}
