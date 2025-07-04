package edu.xanderson.ritimoTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.xanderson.ritimoTask.model.DTOs.MentionDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.BoardService;
import edu.xanderson.ritimoTask.service.MentionService;

public class MentionController {
@Autowired
    private MentionService mentionService;

    @PostMapping("/create/boardcolumn/task/comment/mention")
    public ResponseEntity createMention(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody MentionDTO mentionDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
            mentionService.createBoardColumnTaskCommentMention(mentionDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }    
}