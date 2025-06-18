package edu.xanderson.ritimoTask.model.DTOs;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.CommentEntity;
import edu.xanderson.ritimoTask.model.entity.MentionEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import jakarta.validation.constraints.NotNull;

public class MentionDTO {
    public void MentionsDTO(){

    }

    public void MentionsDTO(MentionEntity mentionsEntity){
        BeanUtils.copyProperties(mentionsEntity, this);
        this.commentId = mentionsEntity.getComment().getId();
        this.mentionedUserId = mentionsEntity.getMentionedUser().getId();
    }

    private long id;

    @NotNull
    private long commentId;

    private CommentEntity comment;
    
    @NotNull
    private long mentionedUserId;

    private UserEntity mentionedUser;
    
    private boolean mentionedUserNotified = false;
        
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public CommentEntity getComment() {
        return comment;
    }

    public void setComment(CommentEntity comment) {
        this.comment = comment;
    }

    public long getMentionedUserId() {
        return mentionedUserId;
    }

    public void setMentionedUserId(long mentionedUserId) {
        this.mentionedUserId = mentionedUserId;
    }

    public UserEntity getMentionedUser() {
        return mentionedUser;
    }

    public void setMentionedUser(UserEntity mentionedUser) {
        this.mentionedUser = mentionedUser;
    }

    public boolean isMentionedUserNotified() {
        return mentionedUserNotified;
    }

    public void setMentionedUserNotified(boolean mentionedUserNotified) {
        this.mentionedUserNotified = mentionedUserNotified;
    };


    
}
