package edu.xanderson.ritimoTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.xanderson.ritimoTask.model.DTOs.EditUserResourcePermitionDTO;
import edu.xanderson.ritimoTask.model.DTOs.OrganizationDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.OrganizationService;


class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/create/organization")
    public ResponseEntity createOrganization(@AuthenticationPrincipal UserEntity currentUser, 
                                @RequestBody OrganizationDTO organizationDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            organizationService.createOrganization(organizationDTO, userId);

            return ResponseEntity.ok().body("Criando organização para o usuário com ID: " + userId);

        }
        return ResponseEntity.ok().body("Usuário não autenticado.");
    }

    @PostMapping("/organization/adduser")
    public ResponseEntity addUserToOrganization(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody EditUserResourcePermitionDTO data ) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
            organizationService.addUserToOrganization(data, userId);
            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }
}