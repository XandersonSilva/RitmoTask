package edu.xanderson.ritimoTask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.ritimoTask.model.DTOs.EditUserResourcePermitionDTO;
import edu.xanderson.ritimoTask.model.DTOs.WorkGroupDTO;
import edu.xanderson.ritimoTask.model.DTOs.WorkGroupSummaryDTO;
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

    @GetMapping("/get/workgroup")
    public ResponseEntity<WorkGroupSummaryDTO> getWorkgroup(@AuthenticationPrincipal UserEntity currentUser,
                                        @RequestParam(value = "workgroupId", required = true) long workgroupId){
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            WorkGroupSummaryDTO workgroup = workGroupService.getWorkGroup(workgroupId, userId);

            return ResponseEntity.ok().body(workgroup);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/get/workgroups")
    public ResponseEntity<List<WorkGroupSummaryDTO>> getWorkgroups(@AuthenticationPrincipal UserEntity currentUser){
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            List<WorkGroupSummaryDTO> workgroups = workGroupService.getWorkGroups(userId);
            System.out.println(userId);
            return ResponseEntity.ok().body(workgroups);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/get/organization/workgroups")
    public ResponseEntity<List<WorkGroupSummaryDTO>> getOrganizationWorkgroups(@AuthenticationPrincipal UserEntity currentUser,
    @RequestParam(value = "organizationId", required = true) long organizationId){
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            List<WorkGroupSummaryDTO> workgroups = workGroupService.getOrganizationWorkgroups(organizationId, userId);
            return ResponseEntity.ok().body(workgroups);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping("/edite/workgroup")
    public ResponseEntity editeWorkGroup(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody WorkGroupDTO workGroupDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            workGroupService.editeWorkGroup(workGroupDTO, userId);

            return ResponseEntity.ok().body("Criando quadro para o usuário com ID: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @DeleteMapping("/delete/workgroup")
    public ResponseEntity deleteWorkGroup(@AuthenticationPrincipal UserEntity currentUser, 
                                @RequestParam(value = "workgroupId", required=true) long workGroupId) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            workGroupService.deleteWorkGroup(workGroupId, userId);

            return ResponseEntity.ok().body("Deletando quadro para o usuário com ID: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PutMapping("/workgoup/adduser")
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