package edu.xanderson.ritimoTask.model.entity;

import java.time.Instant;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.DTOs.SubTaskCreateDTO;
import edu.xanderson.ritimoTask.model.DTOs.SubTaskEditDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SubTaskEntity {
    public SubTaskEntity(){
        setStatusIfIsNull();
    }
    public SubTaskEntity(SubTaskCreateDTO taskDTO){
        BeanUtils.copyProperties(taskDTO, this);
        setStatusIfIsNull();
    }
    public SubTaskEntity(SubTaskEditDTO taskDTO){
        BeanUtils.copyProperties(taskDTO, this);
        setStatusIfIsNull();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    private int position;
    
    private Instant createDate = Instant.now();
    
    private Instant dueDate;

    private boolean done;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public Instant getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }
    public Instant getDueDate() {
        return dueDate;
    }
    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }

    private void setStatusIfIsNull(){
        if (this.status == null) {
            this.status = TaskStatus.TODO;
        }
    }
}
