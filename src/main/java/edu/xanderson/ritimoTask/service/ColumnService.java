package edu.xanderson.ritimoTask.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.ColumnDTO;
import edu.xanderson.ritimoTask.model.DTOs.ColumnSummaryDTO;
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

    public List<ColumnSummaryDTO> getBoardColumns(long boardId, long userId){
        //TODO:Fazer a verificação se o usuário pode realizar essa ação

        List<ColumnSummaryDTO> columnsDTO = new ArrayList();
        //TODO: Verificar outros locais onde foi criado um obijeto para a query no lugar de usar o id
        List<ColumnEntity> columns = columnRepository.findByBoardId(boardId);
        for (ColumnEntity column : columns) {
            columnsDTO.add(new ColumnSummaryDTO(column));
        }

        return columnsDTO;

    }

    public void editeColumn(ColumnDTO dto, long userId){
        if(dto.getId() == 0) return;     

        ColumnEntity column = new ColumnEntity(dto);

        columnRepository.save(column);
    }

    public void deletecolumn(long columnId, long userId){
        ColumnEntity column = columnRepository.getReferenceById(columnId);

        columnRepository.delete(column);
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
