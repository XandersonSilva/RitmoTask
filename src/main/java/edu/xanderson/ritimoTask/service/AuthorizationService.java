package edu.xanderson.ritimoTask.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.entity.UserEntity;
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
        try {
            return userRepository.findByEmail(username);
        } catch (Exception e) {
            return userRepository.findByUsername(username);
        }
    }

    public String registerVerification(String uuid){
        
        UserVerifyEntity userVerify = userVerifyRepository.findByUuid(UUID.fromString(uuid)).get();
        if (userVerify != null) {
            System.out.println(userVerify.getId());
        }

        return uuid;
    }

}