package edu.xanderson.ritimoTask.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.xanderson.ritimoTask.model.entity.WorkGroupEntity;

public interface WorkGroupRepository extends JpaRepository<WorkGroupEntity, Long> {
    @Query("SELECT w FROM WorkGroupEntity w JOIN w.memberships m WHERE m.user.id = :userId")
    List<WorkGroupEntity> findByUserId(long userId);

    List<WorkGroupEntity> findByOrganizationId(long organizationId);
}
