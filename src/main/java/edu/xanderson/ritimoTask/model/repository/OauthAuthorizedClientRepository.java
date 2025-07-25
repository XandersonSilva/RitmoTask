package edu.xanderson.ritimoTask.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.xanderson.ritimoTask.model.entity.OauthAuthorizedClientEntity;

@Repository
public interface OauthAuthorizedClientRepository extends JpaRepository<OauthAuthorizedClientEntity, OauthAuthorizedClientEntity.AuthorizedClientId> {
}
