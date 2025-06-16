package edu.xanderson.ritimoTask.service;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.entity.UserSituation;
import edu.xanderson.ritimoTask.model.entity.UserVerifyEntity;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import edu.xanderson.ritimoTask.model.repository.UserVerifyRepository;

@Service
public class AuthorizationService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserVerifyRepository userVerifyRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.findByUsername(username) instanceof UserDetails) {
            return userRepository.findByUsername(username);
        }    
        return userRepository.findByEmail(username);
    }

    public boolean registerVerification(String uuid) throws ExecutionException{
        try {
            UserVerifyEntity userVerify = userVerifyRepository.findByUuid(UUID.fromString(uuid)).get();
            if(userVerify.getDateExpiration().compareTo(Instant.now()) > 0){
                UserEntity userActive = userVerify.getUser();
                userActive.setSituation(UserSituation.ACTIVE);
                userRepository.save(userActive);
            } else{
                userVerifyRepository.delete(userVerify);
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new ExecutionException("Not find UUID \n", e);
        }
    }
}