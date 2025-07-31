package edu.xanderson.ritimoTask.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.EditUserResourcePermitionDTO;
import edu.xanderson.ritimoTask.model.DTOs.WorkGroupDTO;
import edu.xanderson.ritimoTask.model.DTOs.WorkGroupSummaryDTO;
import edu.xanderson.ritimoTask.model.entity.NotificationEntity;
import edu.xanderson.ritimoTask.model.entity.RoleType;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.entity.WorkGroupEntity;
import edu.xanderson.ritimoTask.model.entity.WorkGroupMembership;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import edu.xanderson.ritimoTask.model.repository.WorkGroupMembershipRepository;
import edu.xanderson.ritimoTask.model.repository.WorkGroupRepository;
import jakarta.transaction.Transactional;

@Service
public class WorkGroupService {
    @Autowired
    private WorkGroupRepository workGroupRepository;
    
    @Autowired
    private WorkGroupMembershipRepository workGroupMembershipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationSecurityService organizationSecurityService;

    @Autowired
    private NotificationService notificationService;

    public void createWorkGroupService(WorkGroupDTO dto, long userId){
        if (dto.getOrganizationId() != 0 && 
            !(organizationSecurityService.verifyIfUserIsAdministratorOrLeader(userId, dto.getOrganizationId()))) {
            return;
        }

        WorkGroupEntity workGroup = new WorkGroupEntity(dto);
        WorkGroupMembership workGroupMembership = new WorkGroupMembership();
        workGroupRepository.save(workGroup);

        workGroupMembership.setUser((UserEntity) userRepository.getReferenceById(userId));
        workGroupMembership.setWorkGroup(workGroup);
        
        if (dto.getOrganizationId() != 0 && 
            (organizationSecurityService.verifyIfUserIsLeader(userId, dto.getOrganizationId()))) {
            workGroupMembership.setRole(RoleType.LEADER);
            workGroupMembershipRepository.save(workGroupMembership);
            return;
        }
        
        workGroupMembership.setRole(RoleType.ADMINISTRATOR);
        workGroupMembershipRepository.save(workGroupMembership);
    }

    @Transactional
    @PreAuthorize("@workGroupSecurityService.verifyIfUserIsAdministratorOrLeaderOrMemberOrGuest(#userId, #workgroupId)")
    public WorkGroupSummaryDTO getWorkGroup(long workgroupId, long userId){
        
        WorkGroupEntity workgroup = workGroupRepository.getReferenceById(workgroupId);
        
        return new WorkGroupSummaryDTO(workgroup);
    }


    public List<WorkGroupSummaryDTO> getWorkGroups(long userId){        
        List<WorkGroupSummaryDTO> workgroups = new ArrayList<>();
        for (WorkGroupEntity workgroup : workGroupRepository.findByUserId(userId)) {
            workgroups.add(new WorkGroupSummaryDTO(workgroup));
        }
        
        return workgroups;
    } 

    @Transactional
    @PreAuthorize("@organizationSecurityService.verifyIfUserIsAdministratorOrLeaderOrMemberOrGuest(#userId, #organizationId)")
    public List<WorkGroupSummaryDTO> getOrganizationWorkgroups(long organizationId, long userId){        
        List<WorkGroupSummaryDTO> workgroups = new ArrayList<>();
        for (WorkGroupEntity workgroup : workGroupRepository.findByOrganizationId(organizationId)) {
            workgroups.add(new WorkGroupSummaryDTO(workgroup));
        }
        
        return workgroups;
    } 

    @Transactional
    @PreAuthorize("@workGroupSecurityService.verifyIfUserIsLeaderOrMember(#userId, #taskDTO.getBoardId())")
    public void editeWorkGroup(WorkGroupDTO dto, long userId){
        if(dto.getId() == 0) return;     

        WorkGroupEntity workGroup = new WorkGroupEntity(dto);

        workGroupRepository.save(workGroup);
    }

    @Transactional
    @PreAuthorize("@workGroupSecurityService.verifyIfUserIsAdministratorOrLeader(#userId, #workgroupId)")
    public void deleteWorkGroup(long workGroupId, long userId){
        WorkGroupEntity workGroup = workGroupRepository.getReferenceById(workGroupId);

        workGroupRepository.delete(workGroup);
    }

    @Transactional
    @PreAuthorize("@workGroupSecurityService.verifyIfUserIsAdministratorOrLeader(#userId, #workgroupId)")
    public void addUserToWorkGroup(EditUserResourcePermitionDTO data, long adminOrLeaderId){
        UserEntity   adminOrLeader   = userRepository.getReferenceById(adminOrLeaderId);
        
        WorkGroupMembership workGroupMembership = new WorkGroupMembership();
        WorkGroupEntity workGroup = new WorkGroupEntity();
        UserEntity user = userRepository.getReferenceById(data.getUserId());
        workGroup.setId(data.getResoarceId());


        workGroupMembership.setWorkGroup(workGroup);
        workGroupMembership.setRole(data.getRole());
        workGroupMembership.setUser(user);

        workGroupMembershipRepository.save(workGroupMembership);

        NotificationEntity notification = new NotificationEntity();

        notification.setRecipientEmail(user.getEmail());
        notification.setRecipientUser(user);
        notification.setRecipientUsername(user.getUsername());
        
        notification.setSubject("Você foi adicionado a um work goup por " + adminOrLeader.getName());
        notification.setContent("Você foi adicionado a um work goup por " + adminOrLeader.getName());

        notificationService.sendNotification(notification);
    }
    
}
