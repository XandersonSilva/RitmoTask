package edu.xanderson.ritimoTask.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MentionsEntity {
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
}
