package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.ColumnDTO;
import edu.xanderson.ritimoTask.model.entity.BoardEntity;
import edu.xanderson.ritimoTask.model.entity.ColumnEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.ColumnRepository;

@Service
public class ColumnService {
    @Autowired
    ColumnRepository columnRepository;

    public void createColumn(ColumnDTO columnDTO, UserEntity user, BoardEntity board){


        ColumnEntity column = new ColumnEntity(columnDTO);
        column.setBoard(board);

        long ordem = columnRepository.countByBoard(board);

        //Adicionando um ao número atual de colunas assim tendo a quantidade atual como ordem na nova coluna
        column.setColumnOrder(ordem + 1);


        columnRepository.save(column);
    }
}
