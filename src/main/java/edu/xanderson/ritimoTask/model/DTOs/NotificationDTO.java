package edu.xanderson.ritimoTask.model.DTOs;

import java.time.Instant;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.NotificationEntity;
import jakarta.validation.constraints.NotNull;

public class NotificationDTO {
    public NotificationDTO(){
        
    }
    public NotificationDTO (NotificationEntity notification){
        BeanUtils.copyProperties(notification, this);
        if (notification.getRecipientUser() != null) {
            recipientUserId = notification.getRecipientUser().getId();
        }
    }

    @NotNull
    private long id;
    
    private String sender;
    
    private String recipientUsername;

    private String recipientEmail;
    
    private long recipientUserId;

    private String subject;

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

    public long getrecipientUserId() {
        return recipientUserId;
    }

    public void setrecipientUserId(long recipientUserId) {
        this.recipientUserId = recipientUserId;
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
