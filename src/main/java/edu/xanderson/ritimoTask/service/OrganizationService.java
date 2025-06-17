package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.OrganizationDTO;
import edu.xanderson.ritimoTask.model.entity.OrganizationEntity;
import edu.xanderson.ritimoTask.model.entity.OrganizationMembership;
import edu.xanderson.ritimoTask.model.entity.RoleType;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.OrganizationMembershipRepository;
import edu.xanderson.ritimoTask.model.repository.OrganizationRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    
    @Autowired
    private OrganizationMembershipRepository organizationMembershipRepository;

    @Autowired
    private UserRepository userRepository;

    public void createOrganization(OrganizationDTO dto, long userId){

        OrganizationEntity organization = new OrganizationEntity(dto);
        OrganizationMembership organizationMembership = new OrganizationMembership();

        organizationRepository.save(organization);

        organizationMembership.setOrganization(organization);
        organizationMembership.setUser((UserEntity) userRepository.getReferenceById(userId));
        organizationMembership.setRole(RoleType.ADMINISTRATOR);

        organizationMembershipRepository.save(organizationMembership);

    }

}
