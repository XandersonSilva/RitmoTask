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

import edu.xanderson.ritimoTask.model.DTOs.BoardDTO;
import edu.xanderson.ritimoTask.model.DTOs.BoardSummaryDTO;
import edu.xanderson.ritimoTask.model.DTOs.EditUserResourcePermitionDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.BoardService;

@RestController
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

    @GetMapping("/get/board")
    public ResponseEntity<BoardSummaryDTO> getBoard(@AuthenticationPrincipal UserEntity currentUser,
                                @RequestParam(value = "boardId", required=true) long boardId) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            BoardSummaryDTO boardsDTO = boardService.getBoard(boardId, userId);

            return ResponseEntity.ok(boardsDTO);

        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/get/boards")
    public ResponseEntity<List<BoardSummaryDTO>> getBoardsByUser(@AuthenticationPrincipal UserEntity currentUser) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            List<BoardSummaryDTO> boardsDTO = boardService.getBoardsByUser(userId);

            return ResponseEntity.ok(boardsDTO);

        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/get/WorkGroup/boards")
    public ResponseEntity<List<BoardSummaryDTO>> getBoardsByWorkGroup(@AuthenticationPrincipal UserEntity currentUser,
                                @RequestParam(value = "workgroupId", required=true) long workgroupId) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            List<BoardSummaryDTO> boardsDTO = boardService.getBoardsByWorkGroup(workgroupId, userId);

            return ResponseEntity.ok(boardsDTO);

        }
        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping("/edite/board")
    public ResponseEntity editeBoard(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody BoardDTO boardDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            boardService.editeBoard(boardDTO, userId);

            return ResponseEntity.ok().body("Criando quadro para o usuário com ID: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @DeleteMapping("/delete/board")
    public ResponseEntity deleteBoard(@AuthenticationPrincipal UserEntity currentUser, 
                                @RequestParam(value = "boardId", required=true) long boardId) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            boardService.deleteBoard(boardId, userId);

            return ResponseEntity.ok().body("Deletando quadro para o usuário com ID: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PutMapping("/board/adduser")
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