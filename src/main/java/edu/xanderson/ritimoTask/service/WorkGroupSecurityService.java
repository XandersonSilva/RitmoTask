package edu.xanderson.ritimoTask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.entity.RoleType;
import edu.xanderson.ritimoTask.model.entity.WorkGroupMembership;
import edu.xanderson.ritimoTask.model.repository.WorkGroupMembershipRepository;


@Service("workGroupSecurityService") // O nome usado no @PreAuthorize
public class WorkGroupSecurityService {

    @Autowired
    private WorkGroupMembershipRepository workGroupMembershipRepository;


    public boolean verifyIfUserIsAdministrator(long userId, long workGroupId) {
        List<WorkGroupMembership> workGroupMemberships;
        workGroupMemberships = workGroupMembershipRepository.findByUserIdAndWorkGroupId(userId, workGroupId);

        for (WorkGroupMembership workGroupMembership : workGroupMemberships) {
            if(workGroupMembership.getRole().equals(RoleType.ADMINISTRATOR)){
                return true;
            }
        }
        return false;
    }

    public boolean verifyIfUserIsAdministratorOrLeader(long userId, long workGroupId) {
        List<WorkGroupMembership> workGroupMemberships;
        workGroupMemberships = workGroupMembershipRepository.findByUserIdAndWorkGroupId(userId, workGroupId);

        for (WorkGroupMembership workGroupMembership : workGroupMemberships) {
            if(workGroupMembership.getRole().equals(RoleType.ADMINISTRATOR) ||
                workGroupMembership.getRole().equals(RoleType.LEADER)
            ){
                return true;
            }
        }
        return false;
    }

    public boolean verifyIfUserIsAdministratorOrLeaderOrMember(long userId, long workGroupId) {
        List<WorkGroupMembership> workGroupMemberships;
        workGroupMemberships = workGroupMembershipRepository.findByUserIdAndWorkGroupId(userId, workGroupId);

        for (WorkGroupMembership workGroupMembership : workGroupMemberships) {
            if(workGroupMembership.getRole().equals(RoleType.ADMINISTRATOR) ||
                workGroupMembership.getRole().equals(RoleType.LEADER) ||
                workGroupMembership.getRole().equals(RoleType.MEMBER) 
            ){
                return true;
            }
        }
        return false;
    }

    public boolean verifyIfUserIsAdministratorOrLeaderOrMemberOrGuest(long userId, long workGroupId) {
        List<WorkGroupMembership> workGroupMemberships;
        workGroupMemberships = workGroupMembershipRepository.findByUserIdAndWorkGroupId(userId, workGroupId);

        for (WorkGroupMembership workGroupMembership : workGroupMemberships) {
            if(workGroupMembership.getRole().equals(RoleType.ADMINISTRATOR) ||
                workGroupMembership.getRole().equals(RoleType.LEADER) ||
                workGroupMembership.getRole().equals(RoleType.MEMBER) ||
                workGroupMembership.getRole().equals(RoleType.GUEST)
            ){
                return true;
            }
        }
        return false;
    }

    public boolean verifyIfUserIsLeader(long userId, long workGroupId) {
    
        List<WorkGroupMembership> workGroupMemberships;
        workGroupMemberships = workGroupMembershipRepository.findByUserIdAndWorkGroupId(userId, workGroupId);

        for (WorkGroupMembership workGroupMembership : workGroupMemberships) {
            if(workGroupMembership.getRole().equals(RoleType.LEADER)){
                return true;
            }
        }
        return false;
    }

    public boolean verifyIfUserIsLeaderOrMember(long userId, long workGroupId) {
    
        List<WorkGroupMembership> workGroupMemberships;
        workGroupMemberships = workGroupMembershipRepository.findByUserIdAndWorkGroupId(userId, workGroupId);

        for (WorkGroupMembership workGroupMembership : workGroupMemberships) {
            if(workGroupMembership.getRole().equals(RoleType.LEADER) ||
                workGroupMembership.getRole().equals(RoleType.MEMBER) 
            ){
                return true;
            }
        }
        return false;
    }

}