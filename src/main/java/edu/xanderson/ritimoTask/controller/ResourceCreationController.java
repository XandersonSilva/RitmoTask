package edu.xanderson.ritimoTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.ritimoTask.model.DTOs.OrganizationDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.OrganizationService;

@RestController
public class ResourceCreationController {
    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/create/organization")
    public String crateOrganization(@AuthenticationPrincipal UserEntity currentUser, 
                                @RequestBody OrganizationDTO organization) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            organizationService.createOrganization(organization, userId);

            return "Criando organização para o usuário com ID: " + userId;

        }
        return "Usuário não autenticado.";
    }

    @PostMapping("/create/workgroup")
    public String createWorkGroup(@AuthenticationPrincipal UserEntity currentUser, 
                                @RequestBody OrganizationDTO organization) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            organizationService.createOrganization(organization, userId);

            return "Criando grupo de trabalho para o usuário com ID: " + userId;

        }
        return "Usuário não autenticado.";
    }

    @PostMapping("/create/board")
    public String createBoard(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody OrganizationDTO organization) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            organizationService.createOrganization(organization, userId);

            return "Criando quadro para o usuário com ID: " + userId;

        }
        return "Usuário não autenticado.";
    }

}
