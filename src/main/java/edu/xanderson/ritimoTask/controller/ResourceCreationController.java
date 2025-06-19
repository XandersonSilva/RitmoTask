package edu.xanderson.ritimoTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.ritimoTask.model.DTOs.BoardDTO;
import edu.xanderson.ritimoTask.model.DTOs.ColumnDTO;
import edu.xanderson.ritimoTask.model.DTOs.CommentDTO;
import edu.xanderson.ritimoTask.model.DTOs.MentionDTO;
import edu.xanderson.ritimoTask.model.DTOs.OrganizationDTO;
import edu.xanderson.ritimoTask.model.DTOs.TagDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskDTO;
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
    public ResponseEntity createOrganization(@AuthenticationPrincipal UserEntity currentUser, 
                                @RequestBody OrganizationDTO organizationDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            organizationService.createOrganization(organizationDTO, userId);

            return ResponseEntity.ok().body("Criando organização para o usuário com ID: " + userId);

        }
        return ResponseEntity.ok().body("Usuário não autenticado.");
    }

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

    @PostMapping("/create/board")
    public ResponseEntity createBoard(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody BoardDTO boardDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            boardService.createBoard(boardDTO, userId);

            return ResponseEntity.ok().body("Criando quadro para o usuário com ID: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PostMapping("/create/boardcolumn")
    public ResponseEntity createBoardColumn(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody ColumnDTO columnDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            boardService.createBoardColumn(columnDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PostMapping("/create/boardcolumn/task")
    public ResponseEntity createBoardColumnTask(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TaskDTO taskDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            boardService.createBoardColumnTask(taskDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PostMapping("/create/boardcolumn/task/comment")
    public ResponseEntity createBoardColumnTaskComment(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody CommentDTO commentDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            boardService.createBoardColumnTaskComment(commentDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PostMapping("/create/boardcolumn/task/comment/mention")
    public ResponseEntity createBoardColumnTaskCommentMention(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody MentionDTO mentionDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
            boardService.createBoardColumnTaskCommentMention(mentionDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PostMapping("/create/boardcolumn/task/tag")
    public ResponseEntity createBoardColumnTaskTag(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody TagDTO tagDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
            boardService.createBoardColumnTaskTag(tagDTO, userId);
            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }
}
