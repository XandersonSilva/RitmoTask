package edu.xanderson.ritimoTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.ritimoTask.model.DTOs.BoardDTO;
import edu.xanderson.ritimoTask.model.DTOs.OrganizationDTO;
import edu.xanderson.ritimoTask.model.DTOs.WorkGroupDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.BoardService;
import edu.xanderson.ritimoTask.service.OrganizationService;
import edu.xanderson.ritimoTask.service.WorkGroupService;

@RestController
public class ResourceCreationController {
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private WorkGroupService workGroupService;

    @Autowired
    private BoardService boardService;

    @PostMapping("/create/organization")
    public String createOrganization(@AuthenticationPrincipal UserEntity currentUser, 
                                @RequestBody OrganizationDTO organizationDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            organizationService.createOrganization(organizationDTO, userId);

            return "Criando organização para o usuário com ID: " + userId;

        }
        return "Usuário não autenticado.";
    }

    @PostMapping("/create/workgroup")
    public String createWorkGroup(@AuthenticationPrincipal UserEntity currentUser, 
                                @RequestBody WorkGroupDTO workGroupDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            workGroupService.createWorkGroupService(workGroupDTO, userId);

            return "Criando grupo de trabalho para o usuário com ID: " + userId;

        }
        return "Usuário não autenticado.";
    }

    @PostMapping("/create/board")
    public String createBoard(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody BoardDTO boardDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            boardService.createBoard(boardDTO, userId);

            return "Criando quadro para o usuário com ID: " + userId;

        }
        return "Usuário não autenticado.";
    }

}
