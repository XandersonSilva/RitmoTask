package edu.xanderson.ritimoTask.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.BoardDTO;
import edu.xanderson.ritimoTask.model.DTOs.ColumnDTO;
import edu.xanderson.ritimoTask.model.DTOs.TaskDTO;
import edu.xanderson.ritimoTask.model.entity.BoardEntity;
import edu.xanderson.ritimoTask.model.entity.BoardMembership;
import edu.xanderson.ritimoTask.model.entity.ColumnEntity;
import edu.xanderson.ritimoTask.model.entity.RoleType;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.BoardMembershipRepository;
import edu.xanderson.ritimoTask.model.repository.BoardRepository;
import edu.xanderson.ritimoTask.model.repository.ColumnRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class BoardService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ColumnRepository columnRepository;
    
    @Autowired
    BoardMembershipRepository boardMembershipRepository;

    @Autowired
    ColumnService columnService;

    @Autowired
    TaskService taskService;


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
        
        UserEntity  user  = userRepository.getReferenceById(userId);
        BoardEntity board = boardRepository.getReferenceById(columnDTO.getBoardId());
        
        if (verifyUserAutority(user, board.getId())) {
            columnService.createColumn(columnDTO, user, board);
        }
    }
    
    public void createBoardColumnTask(TaskDTO taskDTO, long userId){
        UserEntity   user   = userRepository.getReferenceById(userId);
        ColumnEntity column = columnRepository.getReferenceById(taskDTO.getColumnId());

        if (verifyUserAutority(user, column.getBoard().getId())) {
            taskService.createTask(taskDTO);
        }
    }
    
    
    private boolean verifyUserAutority(UserEntity user, long boardId){
        BoardEntity board = boardRepository.getReferenceById(boardId);
        Optional<BoardMembership> boardMembership;
        boardMembership = boardMembershipRepository.findByUserAndBoard(user, board);
        if (boardMembership.isPresent()) {
            RoleType userRole = boardMembership.get().getRole();
            
            if (userRole != RoleType.ADMINISTRATOR &&
                userRole != RoleType.LEADER && 
                userRole != RoleType.MEMBER) {
                return false;
            }
            return true;
        }
        return false;
    }
}
