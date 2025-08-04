package edu.xanderson.ritimoTask.model.DTOs;


import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.NotificationsTypes;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.entity.UserSituation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserEditDTO {
    public UserEditDTO(){
    
    }

    public UserEditDTO(UserEntity user){
        BeanUtils.copyProperties(user, this);
    }

    @NotNull(message = "O id é obrigatorio")
    private long id;
    
    private String name;
    
    @Email
    private String email;
    
    private UserSituation situation;

    private NotificationsTypes notificationsPreferences;

    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public NotificationsTypes getNotificationsPreferences() {
        return notificationsPreferences;
    }

    public void setNotificationsPreferences(NotificationsTypes notificationsPreferences) {
        this.notificationsPreferences = notificationsPreferences;
    }
}
