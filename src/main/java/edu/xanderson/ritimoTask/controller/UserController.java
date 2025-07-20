package edu.xanderson.ritimoTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.ritimoTask.model.DTOs.UserEditDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.UserService;


@RestController
public class UserController {
    @Autowired
    UserService userService;

    @DeleteMapping("/delete/user")
    public ResponseEntity deleteUser(@AuthenticationPrincipal UserEntity currentUser){
        if (currentUser != null) {
            userService.deleteUser(currentUser.getId());
            return ResponseEntity.ok().body("Usuário deletado!");
        }
        return ResponseEntity.badRequest().body("Usuário não logado!");
    }

    @PostMapping("/validate/user")
    public ResponseEntity validateUser(@AuthenticationPrincipal UserEntity currentUser,
                                        @Validated @RequestBody UserEditDTO userDTO){
        if (currentUser != null) {
            userService.validateUser(userDTO, currentUser.getId());
            return ResponseEntity.ok().body("Usuário valido!");
        }
        return ResponseEntity.badRequest().body("Usuário não logado!");
    }
    
}
