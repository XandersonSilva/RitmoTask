package edu.xanderson.ritimoTask.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public BoardSummaryDTO getBoard(long boardId, long userId){
        //TODO:Fazer a verificação se o usuário pode realizar essa ação

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

    public void editeBoard(BoardDTO dto, long userId){
        //TODO:Fazer a verificação se o usuário pode realizar essa ação

        if(dto.getId() == 0) return;     

        BoardEntity board = new BoardEntity(dto);

        boardRepository.save(board);
    }

    public void deleteBoard(long boardId, long userId){
        //TODO:Fazer a verificação se o usuário pode realizar essa ação

        BoardEntity board = boardRepository.getReferenceById(boardId);

        boardRepository.delete(board);
    }

    public void addUserToBoard(EditUserResourcePermitionDTO data, long adminOrLeaderId){
        UserEntity   adminOrLeader   = userRepository.getReferenceById(adminOrLeaderId);
        if (verifyUserAutority.verifyUserAutorityBoard(adminOrLeader, data.getResoarceId())) {
            BoardMembership boardMembership = new BoardMembership();
            BoardEntity board = new BoardEntity();
            UserEntity user = userRepository.getReferenceById(data.getUserId());
            board.setId(data.getResoarceId());


            boardMembership.setBoard(board);
            boardMembership.setRole(data.getRole());
            boardMembership.setUser(user);

            boardMembershipRepository.save(boardMembership);

            NotificationEntity notification = new NotificationEntity();

            notification.setRecipientEmail(user.getEmail());
            notification.setRecipientUser(user);
            notification.setRecipientUsername(user.getUsername());
            notification.setSubject("Você foi adicionado a um board por " + adminOrLeader.getName());
            notification.setContent("Você foi adicionado a um board por " + adminOrLeader.getName());

            notificationService.sendNotification(notification);
            
        }
    }
}
