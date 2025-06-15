package edu.xanderson.ritimoTask.controller;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.xanderson.ritimoTask.infra.security.TokenService;
import edu.xanderson.ritimoTask.model.DTOs.AuthResponseDTO;
import edu.xanderson.ritimoTask.model.DTOs.AuthenticationDTO;
import edu.xanderson.ritimoTask.model.DTOs.RegisterDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.entity.UserVerifyEntity;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import edu.xanderson.ritimoTask.model.repository.UserVerifyRepository;
import edu.xanderson.ritimoTask.service.AuthorizationService;
@Controller
@RequestMapping("auth")

public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private UserVerifyRepository userVerifyRepository;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        UsernamePasswordAuthenticationToken usernamePassword;
        usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        if (this.userRepository.findByEmail(data.email()) != null || this.userRepository.findByUsername(data.username()) != null) {
            return ResponseEntity.badRequest().build();
        }
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

            UserEntity newUser = new UserEntity();
            newUser.setName(data.name());
            newUser.setEmail(data.email());
            newUser.setUsername(data.username());
            newUser.setPassword(encryptedPassword);
            this.userRepository.save(newUser);

            UserVerifyEntity userVerifyEntity = new UserVerifyEntity();

            UUID uuid = UUID.randomUUID();
            System.out.println(uuid);
            userVerifyEntity.setUuid(uuid);
            userVerifyEntity.setUser(newUser);
            userVerifyEntity.setDateExpiration(Instant.now().plusSeconds(900));
            userVerifyRepository.save(userVerifyEntity);

            return ResponseEntity.ok().build();
    }

    @GetMapping("/register/verification/{uuid}")
    public String registerVerification(@PathVariable("uuid") String uuid){
        authorizationService.registerVerification(uuid);
        return null;
    }    
}