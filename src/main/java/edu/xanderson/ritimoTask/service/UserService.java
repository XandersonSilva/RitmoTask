package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.UserEditDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void deleteUser(long userId){
        userRepository.deleteById(userId);
    }

    public boolean validateUser(UserEditDTO userDTO, long userId){
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        UserEntity user = userRepository.getReferenceById(userId);

        if(encryptedPassword  == user.getPassword()){
            return true;
        }
        return false;
    }
}
