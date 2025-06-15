package edu.xanderson.ritimoTask.model.entity;

import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 1024)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<MentionsEntity> mentions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public List<MentionsEntity> getMentions() {
        return mentions;
    }

    public void setMentions(List<MentionsEntity> mentions) {
        this.mentions = mentions;
    }
    
}
