package edu.xanderson.ritimoTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.EditUserResourcePermitionDTO;
import edu.xanderson.ritimoTask.model.DTOs.OrganizationDTO;
import edu.xanderson.ritimoTask.model.entity.NotificationEntity;
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

    @Autowired
    private VerifyUserAutority verifyUserAutority;

    @Autowired
    private NotificationService notificationService;

    public void createOrganization(OrganizationDTO dto, long userId){

        OrganizationEntity organization = new OrganizationEntity(dto);
        OrganizationMembership organizationMembership = new OrganizationMembership();

        organizationRepository.save(organization);

        organizationMembership.setOrganization(organization);
        organizationMembership.setUser((UserEntity) userRepository.getReferenceById(userId));
        organizationMembership.setRole(RoleType.ADMINISTRATOR);

        organizationMembershipRepository.save(organizationMembership);

    }

    public void addUserToOrganization(EditUserResourcePermitionDTO data, long adminOrLeaderId){
        if (!data.getRole().equals(RoleType.ADMINISTRATOR)) return;
        System.out.println(data.getRole());
        UserEntity   adminOrLeader   = userRepository.getReferenceById(adminOrLeaderId);
        if (verifyUserAutority.verifyUserAutorityOrganization(adminOrLeader, data.getResoarceId())) {
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
}
