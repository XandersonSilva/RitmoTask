package edu.xanderson.ritimoTask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.entity.BoardMembership;
import edu.xanderson.ritimoTask.model.entity.RoleType;
import edu.xanderson.ritimoTask.model.repository.BoardMembershipRepository;


@Service("boardSecurityService") // O nome usado no @PreAuthorize
public class BoardSecurityService {

    @Autowired
    private BoardMembershipRepository boardMembershipRepository;


    public boolean verifyIfUserIsAdministrator(long userId, long boardId) {
        List<BoardMembership> boardMemberships;
        boardMemberships = boardMembershipRepository.findByUserIdAndBoardId(userId, boardId);

        for (BoardMembership boardMembership : boardMemberships) {
            if(boardMembership.getRole().equals(RoleType.ADMINISTRATOR)){
                return true;
            }
        }
        return false;
    }

    public boolean verifyIfUserIsAdministratorOrLeader(long userId, long boardId) {
        List<BoardMembership> boardMemberships;
        boardMemberships = boardMembershipRepository.findByUserIdAndBoardId(userId, boardId);

        for (BoardMembership boardMembership : boardMemberships) {
            if(boardMembership.getRole().equals(RoleType.ADMINISTRATOR) ||
                boardMembership.getRole().equals(RoleType.LEADER)
            ){
                return true;
            }
        }
        return false;
    }

    public boolean verifyIfUserIsAdministratorOrLeaderOrMember(long userId, long boardId) {
        List<BoardMembership> boardMemberships;
        boardMemberships = boardMembershipRepository.findByUserIdAndBoardId(userId, boardId);

        for (BoardMembership boardMembership : boardMemberships) {
            if(boardMembership.getRole().equals(RoleType.ADMINISTRATOR) ||
                boardMembership.getRole().equals(RoleType.LEADER) ||
                boardMembership.getRole().equals(RoleType.MEMBER) 
            ){
                return true;
            }
        }
        return false;
    }

    public boolean verifyIfUserIsAdministratorOrLeaderOrMemberOrGuest(long userId, long boardId) {
        List<BoardMembership> boardMemberships;
        boardMemberships = boardMembershipRepository.findByUserIdAndBoardId(userId, boardId);

        for (BoardMembership boardMembership : boardMemberships) {
            if(boardMembership.getRole().equals(RoleType.ADMINISTRATOR) ||
                boardMembership.getRole().equals(RoleType.LEADER) ||
                boardMembership.getRole().equals(RoleType.MEMBER) ||
                boardMembership.getRole().equals(RoleType.GUEST)
            ){
                return true;
            }
        }
        return false;
    }

    public boolean verifyIfUserIsLeader(long userId, long boardId) {
    
        List<BoardMembership> boardMemberships;
        boardMemberships = boardMembershipRepository.findByUserIdAndBoardId(userId, boardId);

        for (BoardMembership boardMembership : boardMemberships) {
            if(boardMembership.getRole().equals(RoleType.LEADER)){
                return true;
            }
        }
        return false;
    }

    public boolean verifyIfUserIsLeaderOrMember(long userId, long boardId) {
    
        List<BoardMembership> boardMemberships;
        boardMemberships = boardMembershipRepository.findByUserIdAndBoardId(userId, boardId);

        for (BoardMembership boardMembership : boardMemberships) {
            if(boardMembership.getRole().equals(RoleType.LEADER) ||
                boardMembership.getRole().equals(RoleType.MEMBER) 
            ){
                return true;
            }
        }
        return false;
    }

}