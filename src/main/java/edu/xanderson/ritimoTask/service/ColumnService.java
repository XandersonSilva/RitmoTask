package edu.xanderson.ritimoTask.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.ColumnDTO;
import edu.xanderson.ritimoTask.model.DTOs.ColumnSummaryDTO;
import edu.xanderson.ritimoTask.model.entity.BoardEntity;
import edu.xanderson.ritimoTask.model.entity.ColumnEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.BoardRepository;
import edu.xanderson.ritimoTask.model.repository.ColumnRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class ColumnService {
    @Autowired
    ColumnRepository columnRepository;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;



    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeaderOrMember(#userId, #columnDTO.getBoardId())")
    public void createBoardColumn(ColumnDTO columnDTO, long userId){
        UserEntity  user  = userRepository.getReferenceById(userId);
        BoardEntity board = boardRepository.getReferenceById(columnDTO.getBoardId());
        
        createColumn(columnDTO, user, board);
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeaderOrMemberOrGuest(#userId, #boardId)")
    public List<ColumnSummaryDTO> getBoardColumns(long boardId, long userId){

        List<ColumnSummaryDTO> columnsDTO = new ArrayList<>();
        //TODO: Verificar outros locais onde foi criado um objeto para a query no lugar de usar o id
        List<ColumnEntity> columns = columnRepository.findByBoardId(boardId);
        for (ColumnEntity column : columns) {
            columnsDTO.add(new ColumnSummaryDTO(column));
        }

        return columnsDTO;

    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeaderOrMember(#userId, #columnDTO.getBoardId())")
    public void editeColumn(ColumnDTO columnDTO, long userId){
        if(columnDTO.getId() == 0) return;     

        ColumnEntity column = new ColumnEntity(columnDTO);

        columnRepository.save(column);
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeaderOrMember(#userId, #columnDTO.getBoardId())")
    public void deletecolumn(ColumnDTO columnDTO, long userId){
        ColumnEntity column = columnRepository.getReferenceById(columnDTO.getId());

        columnRepository.delete(column);
    }




    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeaderOrMember(#user.getId(), #columnDTO.getBoardId())")
    public void createColumn(ColumnDTO columnDTO, UserEntity user, BoardEntity board){
        ColumnEntity column = new ColumnEntity(columnDTO);
        column.setBoard(board);

        long ordem = columnRepository.countByBoard(board);

        //Adicionando um ao número atual de colunas assim tendo a quantidade atual como ordem na nova coluna
        column.setColumnOrder(ordem + 1);

        columnRepository.save(column);
    }
}
