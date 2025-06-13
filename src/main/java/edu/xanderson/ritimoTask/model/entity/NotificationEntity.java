package edu.xanderson.ritimoTask.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String sender;
    
    @Column(nullable = false)
    private String recipientEmail;
    
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity recipientUser;
    
    @Column(nullable = false)
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
