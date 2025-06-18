package edu.xanderson.ritimoTask.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.BoardDTO;
import edu.xanderson.ritimoTask.model.DTOs.ColumnDTO;
import edu.xanderson.ritimoTask.model.entity.BoardEntity;
import edu.xanderson.ritimoTask.model.entity.BoardMembership;
import edu.xanderson.ritimoTask.model.entity.RoleType;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.BoardMembershipRepository;
import edu.xanderson.ritimoTask.model.repository.BoardRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class BoardService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;
    
    @Autowired
    BoardMembershipRepository boardMembershipRepository;

    @Autowired
    ColumnService columnService;


    public void createBoard(BoardDTO dto, long userId){
        BoardEntity board = new BoardEntity(dto);
        BoardMembership boardMembership = new BoardMembership();

        boardRepository.save(board);


        boardMembership.setUser((UserEntity) userRepository.getReferenceById(userId));
        boardMembership.setRole(RoleType.ADMINISTRATOR);
        boardMembership.setBoard(board);

        boardMembershipRepository.save(boardMembership);
    }

    public void createBoardColumn(ColumnDTO columnDTO, long userId){
        Optional<BoardMembership> boardMembership;
        
        UserEntity  user  = userRepository.getReferenceById(userId);
        BoardEntity board = boardRepository.getReferenceById(columnDTO.getBoardId());
        
        boardMembership = boardMembershipRepository.findByUserAndBoard(user, board);
        if (boardMembership.isPresent()) {
            RoleType userRole = boardMembership.get().getRole();

            if (userRole != RoleType.ADMINISTRATOR &&
                userRole != RoleType.LEADER && 
                userRole != RoleType.MEMBER) {
                return;
            }
            columnService.createColumn(columnDTO, user, board);
        }


    }
}
