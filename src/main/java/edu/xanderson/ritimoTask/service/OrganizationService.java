package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.nimbusds.openid.connect.sdk.assurance.evidences.Organization;

import edu.xanderson.ritimoTask.model.DTOs.OrganizationDTO;
import edu.xanderson.ritimoTask.model.entity.OrganizationEntity;
import edu.xanderson.ritimoTask.model.entity.OrganizationMembership;
import edu.xanderson.ritimoTask.model.entity.RoleType;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.OrganizationMembershipRepository;
import edu.xanderson.ritimoTask.model.repository.OrganizationRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import jakarta.persistence.Column;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    
    @Autowired
    private OrganizationMembershipRepository organizationMembershipRepository;

    @Autowired
    private UserRepository userRepository;

    public void createOrganization(OrganizationDTO dto, long userId){

        OrganizationEntity organization = new OrganizationEntity();
        OrganizationMembership organizationMembership = new OrganizationMembership();

        organization.setName(dto.getName());
        organization.setIdentifier(dto.getIdentifier());
        organization.setPhoneNumber(dto.getPhoneNumber());
        organization.setEmail(dto.getEmail());
        organization.setSector(dto.getSector());
        organization.setLogoUrl(dto.getLogoUrl());
        organization.setDescription(dto.getDescription());
        organization.setWebsiteUrl(dto.getWebsiteUrl());

        organizationRepository.save(organization);

        organizationMembership.setOrganization(organization);
        organizationMembership.setUser((UserEntity) userRepository.getById(userId));
        organizationMembership.setRole(RoleType.ADMINISTRATOR);

        organizationMembershipRepository.save(organizationMembership);

    }

}
