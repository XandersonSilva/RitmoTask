package edu.xanderson.ritimoTask.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.xanderson.ritimoTask.model.entity.OrganizationEntity;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long>{
    @Query("SELECT o FROM OrganizationEntity o JOIN memberships m WHERE m.user.id = :userId")
    List<OrganizationEntity> findByUser(long userId);
}
