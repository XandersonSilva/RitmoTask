package edu.xanderson.ritimoTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.ritimoTask.model.DTOs.BoardDTO;
import edu.xanderson.ritimoTask.model.DTOs.ColumnDTO;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.ColumnService;

@RestController
public class BoardColumnController {

    @Autowired
    private ColumnService columnService;

    @PostMapping("/create/boardcolumn")
    public ResponseEntity createBoardColumn(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody ColumnDTO columnDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            columnService.createBoardColumn(columnDTO, userId);

            return ResponseEntity.ok().body("Tentativa de cração de recurso via user: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PostMapping("/edite/boardcolumn")
    public ResponseEntity editecolumn(@AuthenticationPrincipal UserEntity currentUser, 
                                @Validated @RequestBody ColumnDTO columnDTO) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            columnService.editeColumn(columnDTO, userId);

            return ResponseEntity.ok().body("Criando quadro para o usuário com ID: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }

    @PostMapping("/delete/boardcolumn")
    public ResponseEntity deletecolumn(@AuthenticationPrincipal UserEntity currentUser, 
                                @RequestParam(value = "columnId", required=true) long columnId) {
        if (currentUser != null) {
            long userId = currentUser.getId(); // Obtém o ID do usuário
                        
            columnService.deletecolumn(columnId, userId);

            return ResponseEntity.ok().body("Deletando quadro para o usuário com ID: " + userId);

        }
        return ResponseEntity.badRequest().body("Usuário não autenticado.");
    }
}