package edu.xanderson.ritimoTask.model.entity;


import java.time.Instant;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.DTOs.NotificationDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class NotificationEntity {
    public NotificationEntity(){

    }
    public NotificationEntity(NotificationDTO notification){
        BeanUtils.copyProperties(notification, this);
        if (notification.getrecipientUserId() != 0) {
            this.recipientUser.setId(notification.getrecipientUserId());
        }
    }
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String sender;
    
    private String recipientUsername;

    @Column(nullable = false)
    private String recipientEmail;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity recipientUser;
    
    private String subject;

    @Column(nullable = false, length = 5096)
    private String content;

    private boolean sended = false;

    private boolean readed = false;

    private Instant createdate = Instant.now();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipientUsername() {
        return recipientUsername;
    }

    public void setRecipientUsername(String recipientUsername) {
        this.recipientUsername = recipientUsername;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public UserEntity getRecipientUser() {
        return recipientUser;
    }

    public void setRecipientUser(UserEntity recipientUser) {
        this.recipientUser = recipientUser;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSended() {
        return sended;
    }

    public void setSended(boolean sended) {
        this.sended = sended;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public Instant getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Instant createdate) {
        this.createdate = createdate;
    }
}
