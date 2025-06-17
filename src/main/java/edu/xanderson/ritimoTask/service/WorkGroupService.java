package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.WorkGroupDTO;
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

    public void createWorkGroupService(WorkGroupDTO dto, long userId){
        
        WorkGroupEntity workGroup = new WorkGroupEntity(dto);
        WorkGroupMembership workGroupMembership = new WorkGroupMembership();
        
        workGroupRepository.save(workGroup);


        workGroupMembership.setUser((UserEntity) userRepository.getReferenceById(userId));
        workGroupMembership.setRole(RoleType.ADMINISTRATOR);
        workGroupMembership.setWorkGroup(workGroup);

        workGroupMembershipRepository.save(workGroupMembership);
    }
    
}
