package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.ColumnDTO;
import edu.xanderson.ritimoTask.model.entity.BoardEntity;
import edu.xanderson.ritimoTask.model.entity.ColumnEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.BoardRepository;
import edu.xanderson.ritimoTask.model.repository.ColumnRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class ColumnService {
    @Autowired
    ColumnRepository columnRepository;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    VerifyUserAutority verifyUserAutority;


    public void createBoardColumn(ColumnDTO columnDTO, long userId){
        
        UserEntity  user  = userRepository.getReferenceById(userId);
        BoardEntity board = boardRepository.getReferenceById(columnDTO.getBoardId());
        
        if (verifyUserAutority.verifyUserAutorityBoard(user, board.getId())) {
            createColumn(columnDTO, user, board);
        }
    }

    public void createColumn(ColumnDTO columnDTO, UserEntity user, BoardEntity board){


        ColumnEntity column = new ColumnEntity(columnDTO);
        column.setBoard(board);

        long ordem = columnRepository.countByBoard(board);

        //Adicionando um ao número atual de colunas assim tendo a quantidade atual como ordem na nova coluna
        column.setColumnOrder(ordem + 1);


        columnRepository.save(column);
    }
}
