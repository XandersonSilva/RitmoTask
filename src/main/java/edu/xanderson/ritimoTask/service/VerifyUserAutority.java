package edu.xanderson.ritimoTask.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.entity.BoardEntity;
import edu.xanderson.ritimoTask.model.entity.BoardMembership;
import edu.xanderson.ritimoTask.model.entity.OrganizationEntity;
import edu.xanderson.ritimoTask.model.entity.OrganizationMembership;
import edu.xanderson.ritimoTask.model.entity.RoleType;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.entity.WorkGroupEntity;
import edu.xanderson.ritimoTask.model.entity.WorkGroupMembership;
import edu.xanderson.ritimoTask.model.repository.BoardMembershipRepository;
import edu.xanderson.ritimoTask.model.repository.BoardRepository;
import edu.xanderson.ritimoTask.model.repository.OrganizationMembershipRepository;
import edu.xanderson.ritimoTask.model.repository.OrganizationRepository;
import edu.xanderson.ritimoTask.model.repository.WorkGroupMembershipRepository;
import edu.xanderson.ritimoTask.model.repository.WorkGroupRepository;

@Service
public class VerifyUserAutority {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardMembershipRepository boardMembershipRepository;

    @Autowired
    private WorkGroupRepository workGroupRepository;

    @Autowired
    private WorkGroupMembershipRepository workGroupMembershipRepository;

    @Autowired
    private OrganizationRepository organizationRepository;
    
    @Autowired
    private OrganizationMembershipRepository organizationMembershipRepository;
    


    //TODO: Refatorar o código para fazer a verificação mediante as diferentes  
    //autoridades permitidas para realizar uma ação

    public boolean verifyUserAutorityBoard(UserEntity user, long boardId){
        BoardEntity board = boardRepository.getReferenceById(boardId);
        Optional<BoardMembership> boardMembership;
        boardMembership = boardMembershipRepository.findByUserAndBoard(user, board);
        if (boardMembership.isPresent()) {
            RoleType userRole = boardMembership.get().getRole();
            
            if (userRole != RoleType.ADMINISTRATOR &&
                userRole != RoleType.LEADER) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean verifyUserAutorityWorkGroup(UserEntity user, long workGroupId){
        WorkGroupEntity workGroup = workGroupRepository.getReferenceById(workGroupId);
        Optional<WorkGroupMembership> workGroupMembership;
        workGroupMembership = workGroupMembershipRepository.findByUserAndWorkGroup(user, workGroup);
        if (workGroupMembership.isPresent()) {
            RoleType userRole = workGroupMembership.get().getRole();
            
            if (userRole != RoleType.ADMINISTRATOR &&
                userRole != RoleType.LEADER) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean verifyUserAutorityOrganization(UserEntity user, long organizationId){
        OrganizationEntity organization = organizationRepository.getReferenceById(organizationId);
        Optional<OrganizationMembership> organizationMembership;
        organizationMembership = organizationMembershipRepository.findByUserAndOrganization(user, organization);
        if (organizationMembership.isPresent()) {
            RoleType userRole = organizationMembership.get().getRole();
            
            if (userRole != RoleType.ADMINISTRATOR) {
                return false;
            }
            return true;
        }
        return false;
    }
}
