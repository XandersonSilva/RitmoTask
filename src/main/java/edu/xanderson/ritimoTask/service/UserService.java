package edu.xanderson.ritimoTask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.ResourceMembershipDTO;
import edu.xanderson.ritimoTask.model.DTOs.UserEditDTO;
import edu.xanderson.ritimoTask.model.entity.BoardMembership;
import edu.xanderson.ritimoTask.model.entity.OrganizationMembership;
import edu.xanderson.ritimoTask.model.entity.RoleType;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.entity.WorkGroupMembership;
import edu.xanderson.ritimoTask.model.repository.BoardMembershipRepository;
import edu.xanderson.ritimoTask.model.repository.OrganizationMembershipRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import edu.xanderson.ritimoTask.model.repository.WorkGroupMembershipRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardMembershipRepository boardMembershipRepository;

    @Autowired
    private WorkGroupMembershipRepository workGroupMembershipRepository;

    @Autowired
    private OrganizationMembershipRepository organizationMembershipRepository;

    public void deleteUser(long userId){
        userRepository.deleteById(userId);
    }

    public boolean validateUser(UserEditDTO userDTO, long userId){
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        UserEntity user = userRepository.getReferenceById(userId);

        if(encryptedPassword  == user.getPassword()){
            return true;
        }
        return false;
    }
    @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeader(#userId, #dto.getResourceId())")
    public void blockUserOnBoard(ResourceMembershipDTO dto, long userId){
        List<BoardMembership> boardMemberships = boardMembershipRepository.findByUserIdAndBoardId(dto.getUserId(), dto.getResourceId());
        for (BoardMembership boardMembership : boardMemberships) {
            boardMembership.setRole(RoleType.REMOVED);
            boardMembershipRepository.save(boardMembership);
        }
    }

     @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeader(#userId, #dto.getResourceId())")
    public void blockUserOnWorkGroup(ResourceMembershipDTO dto, long userId){
        List<WorkGroupMembership> workgroupsMemberships = workGroupMembershipRepository.findByUserIdAndWorkGroupId(dto.getUserId(), dto.getResourceId());
        for (WorkGroupMembership workgroupsMembership : workgroupsMemberships) {
            workgroupsMembership.setRole(RoleType.REMOVED);
            workGroupMembershipRepository.save(workgroupsMembership);
        }
    }

     @Transactional
    @PreAuthorize("@boardSecurityService.verifyIfUserIsAdministratorOrLeader(#userId, #dto.getResourceId())")
    public void blockUserOnOrganization(ResourceMembershipDTO dto, long userId){
        List<OrganizationMembership> organizationMemberships = organizationMembershipRepository.findByUserIdAndOrganizationId(dto.getUserId(), dto.getResourceId());
        for (OrganizationMembership organizationMembership : organizationMemberships) {
            organizationMembership.setRole(RoleType.REMOVED);
            organizationMembershipRepository.save(organizationMembership);
        }
    }
}
/*
 * INSERT INTO ritmo_task.board_membership (`role`,board_id,user_id) VALUES
	 ('LEADER',1,2),
	 ('LEADER',2,2),
	 ('LEADER',2,7);

 */
