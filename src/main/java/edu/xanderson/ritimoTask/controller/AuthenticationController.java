package edu.xanderson.ritimoTask.controller;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.ritimoTask.infra.security.TokenService;
import edu.xanderson.ritimoTask.model.DTOs.AuthResponseDTO;
import edu.xanderson.ritimoTask.model.DTOs.AuthenticationDTO;
import edu.xanderson.ritimoTask.model.DTOs.UserRegisterDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.entity.UserSituation;
import edu.xanderson.ritimoTask.model.entity.UserVerifyEntity;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import edu.xanderson.ritimoTask.model.repository.UserVerifyRepository;
import edu.xanderson.ritimoTask.service.AuthorizationService;
import edu.xanderson.ritimoTask.service.EmailService;


@RestController
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
    @Autowired
    private EmailService emailService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        if (this.userRepository.findByEmailAndSituation(data.login(), UserSituation.UNCONFIRMED) != null 
            || this.userRepository.findByUsernameAndSituation(data.login(), UserSituation.UNCONFIRMED) != null) {
            return ResponseEntity.badRequest().body("Para fazer login valide seu e-mail");
        }
        UsernamePasswordAuthenticationToken usernamePassword;
        usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated UserRegisterDTO newUserData) throws Exception{
        if (this.userRepository.findByEmailAndSituation(newUserData.getEmail(), UserSituation.UNCONFIRMED) != null 
            || this.userRepository.findByUsernameAndSituation(newUserData.getUsername(), UserSituation.UNCONFIRMED) != null) {
            
            //Deletar o usuário não confirmado da base de dados caso ele esteja tentando realizar cadastro novamente para salvar as novas informações
            userRepository.delete((UserEntity)userRepository.findByEmail(newUserData.getEmail()));
        }
        if (this.userRepository.findByEmailAndSituation(newUserData.getEmail(), UserSituation.ACTIVE) != null 
            || this.userRepository.findByUsernameAndSituation(newUserData.getUsername(), UserSituation.ACTIVE) != null) {
            return ResponseEntity.badRequest().build();
        }

            String encryptedPassword = new BCryptPasswordEncoder().encode(newUserData.getPassword());

            UserEntity newUser = new UserEntity();
            newUser.setName(newUserData.getName());
            newUser.setEmail(newUserData.getEmail());
            newUser.setUsername(newUserData.getUsername());
            newUser.setPassword(encryptedPassword);
            newUser.setSituation(UserSituation.UNCONFIRMED);
            
            UserVerifyEntity userVerifyEntity = new UserVerifyEntity();
            
            UUID uuid = UUID.randomUUID();
            
            boolean result = emailService.sendTextMail(newUserData.getEmail(), 
            "Email de verificação de cadastro",
                    String.valueOf(uuid));
            
            if (result) {
                this.userRepository.save(newUser);
            }

            System.out.println(uuid);
            userVerifyEntity.setUuid(uuid);
            userVerifyEntity.setUser(newUser);
            userVerifyEntity.setDateExpiration(Instant.now().plusSeconds(900));
            userVerifyRepository.save(userVerifyEntity);

            return ResponseEntity.ok().build();
    }

    @GetMapping("/register/verification/{uuid}")
    public String registerVerification(@PathVariable("uuid") String uuid) throws ExecutionException{
        authorizationService.registerVerification(uuid);
        return null;
    }    
}