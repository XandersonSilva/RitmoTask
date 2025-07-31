package edu.xanderson.ritimoTask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.entity.OrganizationMembership;
import edu.xanderson.ritimoTask.model.entity.RoleType;
import edu.xanderson.ritimoTask.model.repository.OrganizationMembershipRepository;


@Service("organizationSecurityService") // O nome usado no @PreAuthorize
public class OrganizationSecurityService {

    @Autowired
    private OrganizationMembershipRepository organizationMembershipRepository;


    public boolean verifyIfUserIsAdministrator(long userId, long organizationId) {
        List<OrganizationMembership> organizationMemberships;
        organizationMemberships = organizationMembershipRepository.findByUserIdAndOrganizationId(userId,  organizationId);

        for (OrganizationMembership organizationMembership : organizationMemberships) {
            if(organizationMembership.getRole().equals(RoleType.ADMINISTRATOR)){
                return true;
            }
        }
        return false;
    }

    public boolean verifyIfUserIsLeader(long userId, long organizationId) {
        List<OrganizationMembership> organizationMemberships;
        organizationMemberships = organizationMembershipRepository.findByUserIdAndOrganizationId(userId,  organizationId);

        for (OrganizationMembership organizationMembership : organizationMemberships) {
            if(organizationMembership.getRole().equals(RoleType.LEADER)
            ){
                return true;
            }
        }
        return false;
    }

    public boolean verifyIfUserIsAdministratorOrLeader(long userId, long organizationId) {
        List<OrganizationMembership> organizationMemberships;
        organizationMemberships = organizationMembershipRepository.findByUserIdAndOrganizationId(userId,  organizationId);

        for (OrganizationMembership organizationMembership : organizationMemberships) {
            if(organizationMembership.getRole().equals(RoleType.ADMINISTRATOR) ||
                organizationMembership.getRole().equals(RoleType.LEADER)
            ){
                return true;
            }
        }
        return false;
    }

    public boolean verifyIfUserIsAdministratorOrLeaderOrMember(long userId, long organizationId) {
        List<OrganizationMembership> organizationMemberships;
        organizationMemberships = organizationMembershipRepository.findByUserIdAndOrganizationId(userId,  organizationId);

        for (OrganizationMembership organizationMembership : organizationMemberships) {
            if(organizationMembership.getRole().equals(RoleType.ADMINISTRATOR) ||
                organizationMembership.getRole().equals(RoleType.LEADER) ||
                organizationMembership.getRole().equals(RoleType.MEMBER) 
            ){
                return true;
            }
        }
        return false;
    }

    public boolean verifyIfUserIsAdministratorOrLeaderOrMemberOrGuest(long userId, long organizationId) {
        List<OrganizationMembership> organizationMemberships;
        organizationMemberships = organizationMembershipRepository.findByUserIdAndOrganizationId(userId,  organizationId);

        for (OrganizationMembership organizationMembership : organizationMemberships) {
            if(organizationMembership.getRole().equals(RoleType.ADMINISTRATOR) ||
                organizationMembership.getRole().equals(RoleType.LEADER) ||
                organizationMembership.getRole().equals(RoleType.MEMBER) ||
                organizationMembership.getRole().equals(RoleType.GUEST)
            ){
                return true;
            }
        }
        return false;
    }


}