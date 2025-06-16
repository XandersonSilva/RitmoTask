package edu.xanderson.ritimoTask.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.entity.UserSituation;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    UserDetails findByUsername(String login);
    UserDetails findByEmail(String login);
    
    UserDetails findByEmailAndSituation(String login, UserSituation situation);
    UserDetails findByUsernameAndSituation(String login, UserSituation situation);
}
