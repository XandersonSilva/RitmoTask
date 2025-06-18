package edu.xanderson.ritimoTask.model.entity;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.DTOs.MentionDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(
        name = "uk_board_comment_mention", // Nome para a restrição no BD
        columnNames = {"comment_id", "user_id"}
    )
)
public class MentionEntity {
    public MentionEntity(){

    }

    public MentionEntity(MentionDTO mentionsDTO){
        BeanUtils.copyProperties(mentionsDTO, this);
        this.comment = new CommentEntity();
        this.comment.setId(mentionsDTO.getCommentId());
        this.mentionedUser = new UserEntity();
        this.mentionedUser.setId(mentionsDTO.getMentionedUserId());
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private CommentEntity comment;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity mentionedUser;
    
    @Column(nullable = false)
    private boolean mentionedUserNotified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CommentEntity getComment() {
        return comment;
    }

    public void setComment(CommentEntity comment) {
        this.comment = comment;
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
    }
}
