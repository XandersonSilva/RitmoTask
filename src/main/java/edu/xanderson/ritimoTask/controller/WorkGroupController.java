package edu.xanderson.ritimoTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.ritimoTask.model.DTOs.EditUserResourcePermitionDTO;
import edu.xanderson.ritimoTask.model.DTOs.WorkGroupDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.WorkGroupService;

@RestController
public class WorkGroupController {

    @Autowired
    private WorkGroupService workGroupService;

    @PostMapping("/create/workgroup")
    public ResponseEntity createWorkGroup(@AuthenticationPrincipal UserEntity currentUser, 
                                @RequestBody WorkGroupDTO workGroupDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            workGroupService.createWorkGroupService(workGroupDTO, userId);

            return ResponseEntity.ok().body("Criando grupo de trabalho para o usuário com ID: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PostMapping("/workgoup/adduser")
    public ResponseEntity addUserToWorkGroup(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody EditUserResourcePermitionDTO data ) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
            workGroupService.addUserToWorkGroup(data, userId);
            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }
}