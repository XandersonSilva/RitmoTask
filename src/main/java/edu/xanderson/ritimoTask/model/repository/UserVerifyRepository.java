package edu.xanderson.ritimoTask.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.UserVerifyEntity;

public interface UserVerifyRepository extends JpaRepository<UserVerifyEntity, Long> {
    public Optional<UserVerifyEntity> findByUuid(UUID uuid);
}
