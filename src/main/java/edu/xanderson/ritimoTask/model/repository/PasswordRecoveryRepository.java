package edu.xanderson.ritimoTask.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.ritimoTask.model.entity.PasswordRecoveryEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;

public interface PasswordRecoveryRepository extends JpaRepository<PasswordRecoveryEntity, Long> {
    PasswordRecoveryEntity findByToken(UUID token);
    PasswordRecoveryEntity findByUser(UserEntity user);
    
}
