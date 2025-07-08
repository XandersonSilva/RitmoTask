package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.EditUserResourcePermitionDTO;
import edu.xanderson.ritimoTask.model.DTOs.OrganizationDTO;
import edu.xanderson.ritimoTask.model.DTOs.WorkGroupDTO;
import edu.xanderson.ritimoTask.model.entity.NotificationEntity;
import edu.xanderson.ritimoTask.model.entity.OrganizationEntity;
import edu.xanderson.ritimoTask.model.entity.RoleType;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.entity.WorkGroupEntity;
import edu.xanderson.ritimoTask.model.entity.WorkGroupMembership;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import edu.xanderson.ritimoTask.model.repository.WorkGroupMembershipRepository;
import edu.xanderson.ritimoTask.model.repository.WorkGroupRepository;

@Service
public class WorkGroupService {
    @Autowired
    WorkGroupRepository workGroupRepository;
    
    @Autowired
    WorkGroupMembershipRepository workGroupMembershipRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VerifyUserAutority verifyUserAutority;

    @Autowired
    NotificationService notificationService;

    public void createWorkGroupService(WorkGroupDTO dto, long userId){
        
        WorkGroupEntity workGroup = new WorkGroupEntity(dto);
        WorkGroupMembership workGroupMembership = new WorkGroupMembership();
        
        workGroupRepository.save(workGroup);


        workGroupMembership.setUser((UserEntity) userRepository.getReferenceById(userId));
        workGroupMembership.setRole(RoleType.ADMINISTRATOR);
        workGroupMembership.setWorkGroup(workGroup);

        workGroupMembershipRepository.save(workGroupMembership);
    }

    public void editeWorkGroup(WorkGroupDTO dto, long userId){
        if(dto.getId() == 0) return;     

        WorkGroupEntity workGroup = new WorkGroupEntity(dto);

        workGroupRepository.save(workGroup);
    }

    public void deleteWorkGroup(long workGroupId, long userId){
        WorkGroupEntity workGroup = workGroupRepository.getReferenceById(workGroupId);

        workGroupRepository.delete(workGroup);
    }

    public void addUserToWorkGroup(EditUserResourcePermitionDTO data, long adminOrLeaderId){
        UserEntity   adminOrLeader   = userRepository.getReferenceById(adminOrLeaderId);
        if (verifyUserAutority.verifyUserAutorityWorkGroup(adminOrLeader, data.getResoarceId())) {
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
    
}
