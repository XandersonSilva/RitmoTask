package edu.xanderson.ritimoTask.model.entity;

import edu.xanderson.ritimoTask.service.EmailService;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private long id;
    
    private String name;
    
    private String username;
    
    private String email;
    
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrganizationEntity> organizationsEntity;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WorkGroupEntity> workGroupsEntity;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BoardEntity> boarsEntity;
    
    @OneToMany(mappedBy = "mentionedUser")
    private List<MentionsEntity> mentions;

    @OneToMany(mappedBy = "recipientUser")
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
        this.email = EmailService.validateEmail(email);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<OrganizationEntity> getOrganizationsEntity() {
        return organizationsEntity;
    }

    public void setOrganizationsEntity(List<OrganizationEntity> organizationsEntity) {
        this.organizationsEntity = organizationsEntity;
    }

    public List<WorkGroupEntity> getWorkGroupsEntity() {
        return workGroupsEntity;
    }

    public void setWorkGroupsEntity(List<WorkGroupEntity> workGroupsEntity) {
        this.workGroupsEntity = workGroupsEntity;
    }

    public List<BoardEntity> getBoarsEntity() {
        return boarsEntity;
    }

    public void setBoarsEntity(List<BoardEntity> boarsEntity) {
        this.boarsEntity = boarsEntity;
    }

    public List<MentionsEntity> getMentions() {
        return mentions;
    }

    public void setMentions(List<MentionsEntity> mentions) {
        this.mentions = mentions;
    }

    public List<NotificationEntity> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationEntity> notifications) {
        this.notifications = notifications;
    }

}