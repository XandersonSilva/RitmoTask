package edu.xanderson.ritimoTask.model.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.xanderson.ritimoTask.model.DTOs.UserDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserEntity implements UserDetails{
    public UserEntity(){
    
    }

    public UserEntity(UserDTO dto){
        BeanUtils.copyProperties(dto, this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true, length = 100)
    private String username;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    private String password;

    @Enumerated(EnumType.STRING)
    private UserSituation situation;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrganizationMembership> organizationsEntity;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WorkGroupMembership> workGroupsEntity;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BoardMembership> boarsEntity;
    
    @OneToMany(mappedBy = "mentionedUser")
    private List<MentionEntity> mentions;

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
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public UserSituation getSituation() {
        return situation;
    }

    public void setSituation(UserSituation situation) {
        this.situation = situation;
    }
}