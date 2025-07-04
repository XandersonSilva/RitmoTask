package edu.xanderson.ritimoTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.xanderson.ritimoTask.model.DTOs.BoardDTO;
import edu.xanderson.ritimoTask.model.DTOs.EditUserResourcePermitionDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.BoardService;

public class BoardController {
    @Autowired
    private BoardService boardService;

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

    @PostMapping("/board/adduser")
    public ResponseEntity addUserToBoard(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody EditUserResourcePermitionDTO data ) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
            boardService.addUserToBoard(data, userId);
            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }
}