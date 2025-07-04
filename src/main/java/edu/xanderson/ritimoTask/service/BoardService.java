package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.BoardDTO;
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
