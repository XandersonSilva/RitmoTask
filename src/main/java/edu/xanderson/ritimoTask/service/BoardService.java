package edu.xanderson.ritimoTask.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.BoardDTO;
import edu.xanderson.ritimoTask.model.DTOs.BoardSummaryDTO;
import edu.xanderson.ritimoTask.model.DTOs.EditUserResourcePermitionDTO;
import edu.xanderson.ritimoTask.model.entity.BoardEntity;
import edu.xanderson.ritimoTask.model.entity.BoardMembership;
import edu.xanderson.ritimoTask.model.entity.NotificationEntity;
import edu.xanderson.ritimoTask.model.entity.RoleType;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.BoardMembershipRepository;
import edu.xanderson.ritimoTask.model.repository.BoardRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class BoardService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardMembershipRepository boardMembershipRepository;

    @Autowired
    NotificationService notificationService;

    @Autowired
    VerifyUserAutority verifyUserAutority;


    public void createBoard(BoardDTO dto, long userId){
        BoardEntity board = new BoardEntity(dto);
        BoardMembership boardMembership = new BoardMembership();

        boardRepository.save(board);


        boardMembership.setUser((UserEntity) userRepository.getReferenceById(userId));
        boardMembership.setRole(RoleType.ADMINISTRATOR);
        boardMembership.setBoard(board);

        boardMembershipRepository.save(boardMembership);
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeader(#userId, #boardId)")
    public BoardSummaryDTO getBoard(long boardId, long userId){
        BoardSummaryDTO board = new BoardSummaryDTO(boardRepository.getReferenceById(boardId));

        return board;
    }

    public List<BoardSummaryDTO> getBoardsByUser(long userId){
        //TODO:Fazer a verificação se o usuário pode realizar essa ação

        List<BoardSummaryDTO> boards = new ArrayList<>();
        for (BoardEntity board : boardRepository.findBoardsByUser(userId)) {
            boards.add(new BoardSummaryDTO(board));
        }

        return boards;
    }

    public List<BoardSummaryDTO> getBoardsByWorkGroup(long workGroupId, long userId){
        //TODO:Fazer a verificação se o usuário pode realizar essa ação

        List<BoardSummaryDTO> boards = new ArrayList<>();
        for (BoardEntity board : boardRepository.findByWorkGroupId(workGroupId)) {
            boards.add(new BoardSummaryDTO(board));
        }

        return boards;
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeader(#userId, #dto.getId())")
    public void editeBoard(BoardDTO dto, long userId){

        if(dto.getId() == 0) return;     

        BoardEntity board = new BoardEntity(dto);

        boardRepository.save(board);
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeader(#userId, #boardId)")
    public void deleteBoard(long boardId, long userId){
        BoardEntity board = boardRepository.getReferenceById(boardId);

        boardRepository.delete(board);
    }

    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeader(#adminOrLeader, #data.getResoarceId())")
    public void addUserToBoard(EditUserResourcePermitionDTO data, long adminOrLeaderId){
        UserEntity   adminOrLeader   = userRepository.getReferenceById(adminOrLeaderId);
        
        BoardMembership boardMembership = new BoardMembership();
        BoardEntity board = new BoardEntity();
        UserEntity user = userRepository.getReferenceById(data.getUserId());
        board.setId(data.getResoarceId());


        boardMembership.setBoard(board);
        boardMembership.setRole(data.getRole());
        boardMembership.setUser(user);

        boardMembershipRepository.save(boardMembership);

        //Notificando ao usuário que ele foi adicionado a um board
        NotificationEntity notification = new NotificationEntity();

        notification.setRecipientEmail(user.getEmail());
        notification.setRecipientUser(user);
        notification.setRecipientUsername(user.getUsername());
        notification.setSubject("Você foi adicionado a um board por " + adminOrLeader.getName());
        notification.setContent("Você foi adicionado a um board por " + adminOrLeader.getName());

        notificationService.sendNotification(notification);
            
    }
}
