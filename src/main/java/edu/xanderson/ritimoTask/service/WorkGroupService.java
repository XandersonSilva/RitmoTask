package edu.xanderson.ritimoTask.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
        //TODO:Verificar se o usuário tem autoridade para realizar essa ação
        WorkGroupEntity workGroup = new WorkGroupEntity(dto);
        WorkGroupMembership workGroupMembership = new WorkGroupMembership();
        
        workGroupRepository.save(workGroup);


        workGroupMembership.setUser((UserEntity) userRepository.getReferenceById(userId));
        workGroupMembership.setRole(RoleType.ADMINISTRATOR);
        workGroupMembership.setWorkGroup(workGroup);

        workGroupMembershipRepository.save(workGroupMembership);
    }

    public WorkGroupSummaryDTO getWorkGroup(long workgroupId, long userId){
        //TODO:Verificar se o usuário tem autoridade para realizar essa ação
        
        WorkGroupEntity workgroup = workGroupRepository.getReferenceById(workgroupId);
        
        return new WorkGroupSummaryDTO(workgroup);
    }

    public List<WorkGroupSummaryDTO> getWorkGroups(long userId){
        //TODO:Verificar se o usuário tem autoridade para realizar essa ação
        
        List<WorkGroupSummaryDTO> workgroups = new ArrayList<>();
        for (WorkGroupEntity workgroup : workGroupRepository.findByUserId(userId)) {
            workgroups.add(new WorkGroupSummaryDTO(workgroup));
        }
        
        return workgroups;
    } 

    public List<WorkGroupSummaryDTO> getOrganizationWorkgroups(long organizationId, long userId){
        //TODO:Verificar se o usuário tem autoridade para realizar essa ação
        
        List<WorkGroupSummaryDTO> workgroups = new ArrayList<>();
        for (WorkGroupEntity workgroup : workGroupRepository.findByOrganizationId(organizationId)) {
            workgroups.add(new WorkGroupSummaryDTO(workgroup));
        }
        
        return workgroups;
    } 

    public void editeWorkGroup(WorkGroupDTO dto, long userId){
        //TODO:Verificar se o usuário tem autoridade para realizar essa ação
        if(dto.getId() == 0) return;     

        WorkGroupEntity workGroup = new WorkGroupEntity(dto);

        workGroupRepository.save(workGroup);
    }

    public void deleteWorkGroup(long workGroupId, long userId){
        //TODO:Verificar se o usuário tem autoridade para realizar essa ação
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
