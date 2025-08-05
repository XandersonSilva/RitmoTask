package edu.xanderson.ritimoTask.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.ritimoTask.service.PasswordRecoveryService;

@RestController
@RequestMapping("public")
public class PasswordRecoveryController {
    @Autowired
    private PasswordRecoveryService passwordRecoveryService;

    @GetMapping("/recoverPassword")
    public ResponseEntity<String> recoverPassword(
        @RequestParam(value = "email", required=true) String email){
        try {
            passwordRecoveryService.recoverPassword(email);
            return ResponseEntity.ok().body(
                "Um link para alterar a senha foi enviado para seu e-mail");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro!");

        }
    }

    @GetMapping("/changePassword")
    public ResponseEntity<String> changePassword(
            @RequestParam(value = "token", required=true) String token,
            @RequestParam(value = "newPassword", required=true) String newPassword){
        try {
            passwordRecoveryService.changePassword(UUID.fromString(token), newPassword);
            return ResponseEntity.ok().body("Senha alterada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro!");

        }
    } 
}
