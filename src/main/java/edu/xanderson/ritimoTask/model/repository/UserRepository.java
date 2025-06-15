package edu.xanderson.ritimoTask.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import edu.xanderson.ritimoTask.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    UserDetails findByUsername(String login);
    UserDetails findByEmail(String login);
}
