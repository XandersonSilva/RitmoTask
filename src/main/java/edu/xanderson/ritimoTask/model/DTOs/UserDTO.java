package edu.xanderson.ritimoTask.model.DTOs;

import java.util.List;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.BoardMembership;
import edu.xanderson.ritimoTask.model.entity.MentionEntity;
import edu.xanderson.ritimoTask.model.entity.NotificationEntity;
import edu.xanderson.ritimoTask.model.entity.OrganizationMembership;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.entity.UserSituation;
import edu.xanderson.ritimoTask.model.entity.WorkGroupMembership;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {
    public UserDTO(){
    
    }

    public UserDTO(UserEntity user){
        BeanUtils.copyProperties(user, this);
    }

    private long id;
    
    @NotBlank(message = "O nome é obrigatorio")
    private String name;
    
    @NotBlank(message = "O username é obrigatorio")
    private String username;
    
    @NotBlank(message = "O email é obrigatorio")
    @Email(message = "e-mail inválido")
    private String email;
    
    private UserSituation situation;

    private List<OrganizationMembership> organizationsEntity;
    
    private List<WorkGroupMembership> workGroupsEntity;
    
    private List<BoardMembership> boarsEntity;
    
    private List<MentionEntity> mentions;

    private List<NotificationEntity> notifications;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserSituation getSituation() {
        return situation;
    }

    public void setSituation(UserSituation situation) {
        this.situation = situation;
    }

    public List<OrganizationMembership> getOrganizationsEntity() {
        return organizationsEntity;
    }

    public void setOrganizationsEntity(List<OrganizationMembership> organizationsEntity) {
        this.organizationsEntity = organizationsEntity;
    }

    public List<WorkGroupMembership> getWorkGroupsEntity() {
        return workGroupsEntity;
    }

    public void setWorkGroupsEntity(List<WorkGroupMembership> workGroupsEntity) {
        this.workGroupsEntity = workGroupsEntity;
    }

    public List<BoardMembership> getBoarsEntity() {
        return boarsEntity;
    }

    public void setBoarsEntity(List<BoardMembership> boarsEntity) {
        this.boarsEntity = boarsEntity;
    }

    public List<MentionEntity> getMentions() {
        return mentions;
    }

    public void setMentions(List<MentionEntity> mentions) {
        this.mentions = mentions;
    }

    public List<NotificationEntity> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationEntity> notifications) {
        this.notifications = notifications;
    }

}
