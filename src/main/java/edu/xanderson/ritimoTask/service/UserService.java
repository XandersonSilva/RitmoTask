package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void deleteUser(long userId){
        userRepository.deleteById(userId);
    }    
}
