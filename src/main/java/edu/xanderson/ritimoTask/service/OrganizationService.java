package edu.xanderson.ritimoTask.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.EditUserResourcePermitionDTO;
import edu.xanderson.ritimoTask.model.DTOs.OrganizationDTO;
import edu.xanderson.ritimoTask.model.DTOs.OrganizationEditDTO;
import edu.xanderson.ritimoTask.model.DTOs.OrganizationSummaryDTO;
import edu.xanderson.ritimoTask.model.entity.NotificationEntity;
import edu.xanderson.ritimoTask.model.entity.OrganizationEntity;
import edu.xanderson.ritimoTask.model.entity.OrganizationMembership;
import edu.xanderson.ritimoTask.model.entity.RoleType;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.OrganizationMembershipRepository;
import edu.xanderson.ritimoTask.model.repository.OrganizationRepository;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationMembershipRepository organizationMembershipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    public void createOrganization(OrganizationDTO dto, long userId) {

        OrganizationEntity organization = new OrganizationEntity(dto);
        OrganizationMembership organizationMembership = new OrganizationMembership();

        organizationRepository.save(organization);

        organizationMembership.setOrganization(organization);
        organizationMembership.setUser((UserEntity) userRepository.getReferenceById(userId));
        organizationMembership.setRole(RoleType.ADMINISTRATOR);

        organizationMembershipRepository.save(organizationMembership);

    }

    @Transactional
    @PreAuthorize("@organizationSecurityService.verifyIfUserIsAdministratorOrLeaderOrMemberOrGuest(#userId, #organizationId)")
    public OrganizationSummaryDTO getOrganization(long organizationId, long userId) {
        OrganizationEntity organization = organizationRepository.getReferenceById(organizationId);
        OrganizationSummaryDTO organizationDTO = new OrganizationSummaryDTO(organization);

        return organizationDTO;
    }

    public List<OrganizationSummaryDTO> getOrganizations(long userId) {
        UserEntity user = userRepository.getById(userId);
        if(user == null) return null;

        List<OrganizationSummaryDTO> organizationsDTO = new ArrayList<>();
        for (OrganizationEntity organization : organizationRepository.findByUser(userId)) {
            organizationsDTO.add(new OrganizationSummaryDTO(organization));
        }

        return organizationsDTO;
    }

    @Transactional
    @PreAuthorize("@organizationSecurityService.verifyIfUserIsAdministratorOrLeader(#userId, #dto.getId())")
    public void editeOrganization(OrganizationEditDTO dto, long userId) {
        if (dto.getId() == 0)
            return;

        OrganizationEntity organization = new OrganizationEntity(dto);

        organizationRepository.save(organization);
    }

    @Transactional
    @PreAuthorize("@organizationSecurityService.verifyIfUserIsAdministratorOrLeader(#userId, #organizationId)")
    public void deleteOrganization(long organizationId, long userId) {
        OrganizationEntity organization = organizationRepository.getReferenceById(organizationId);

        organizationRepository.delete(organization);
    }

    @Transactional
    @PreAuthorize("@organizationSecurityService.verifyIfUserIsAdministratorOrLeader(#adminOrLeaderId, #data.getResoarceId())")
    public void addUserToOrganization(EditUserResourcePermitionDTO data, long adminOrLeaderId) {
        UserEntity adminOrLeader = userRepository.getReferenceById(adminOrLeaderId);

        OrganizationMembership organizationMembership = new OrganizationMembership();
        OrganizationEntity organization = new OrganizationEntity();
        UserEntity user = userRepository.getReferenceById(data.getUserId());
        organization.setId(data.getResoarceId());

        organizationMembership.setOrganization(organization);
        organizationMembership.setRole(data.getRole());
        organizationMembership.setUser(user);

        organizationMembershipRepository.save(organizationMembership);

        NotificationEntity notification = new NotificationEntity();

        notification.setRecipientEmail(user.getEmail());
        notification.setRecipientUser(user);
        notification.setRecipientUsername(user.getUsername());

        notification.setSubject("Você foi adicionado a uma organização por " + adminOrLeader.getName());
        notification.setContent("Você foi adicionado a uma organização por " + adminOrLeader.getName());

        notificationService.sendNotification(notification);
    }
}
