package edu.xanderson.ritimoTask.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.entity.OrganizationMembership;
import edu.xanderson.ritimoTask.model.entity.OrganizationEntity;

public interface OrganizationMembershipRepository extends JpaRepository<OrganizationMembership, Long>{
    Optional<OrganizationMembership> findByUserAndOrganization(UserEntity user, OrganizationEntity organization);
}
