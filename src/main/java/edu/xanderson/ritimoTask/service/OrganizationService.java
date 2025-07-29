package edu.xanderson.ritimoTask.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.ritimoTask.model.DTOs.EditUserResourcePermitionDTO;
import edu.xanderson.ritimoTask.model.DTOs.OrganizationDTO;
import edu.xanderson.ritimoTask.model.DTOs.OrganizationSummaryDTO;
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

    public OrganizationSummaryDTO getOrganization(long organizationId, long userId){
        //TODO:Verificar se o usuário tem autoridade para realizar essa ação

        try {
            OrganizationEntity organization = organizationRepository.getReferenceById(organizationId);
            OrganizationSummaryDTO organizationDTO = new OrganizationSummaryDTO(organization);
    
            return organizationDTO;
            
        } catch (Exception e) {
            // TODO: handle exception
            // Erro: Usuário solicitou dados inexistentes ou ID incorreto
            System.out.println(e);
            return null;
        }
    }

    public List<OrganizationSummaryDTO> getOrganizations(long userId){
        //TODO:Verificar se o usuário tem autoridade para realizar essa ação

        try {
            List<OrganizationSummaryDTO> organizationsDTO = new ArrayList<>();
            for (OrganizationEntity organization : organizationRepository.findByUser(userId)) {
                organizationsDTO.add(new OrganizationSummaryDTO(organization));
            }

            return organizationsDTO;
            
        } catch (Exception e) {
            // TODO: handle exception
            // Erro: Usuário inexistentes ou ID incorreto
            System.out.println(e);
            return null;
        }
    }

    public void editeOrganization(OrganizationDTO dto, long userId){
        if(dto.getId() == 0) return;     

        OrganizationEntity organization = new OrganizationEntity(dto);

        organizationRepository.save(organization);
    }

    public void deleteOrganization(long organizationId, long userId){
        OrganizationEntity organization = organizationRepository.getReferenceById(organizationId);

        organizationRepository.delete(organization);
    }

    public void addUserToOrganization(EditUserResourcePermitionDTO data, long adminOrLeaderId){
        if (!data.getRole().equals(RoleType.ADMINISTRATOR)) return;
        System.out.println(data.getRole());
        UserEntity   adminOrLeader   = userRepository.getReferenceById(adminOrLeaderId);
    
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
