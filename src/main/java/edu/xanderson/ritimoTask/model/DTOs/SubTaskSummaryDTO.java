package edu.xanderson.ritimoTask.model.DTOs;

import java.time.Instant;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.SubTaskEntity;
import edu.xanderson.ritimoTask.model.entity.TaskStatus;

public class SubTaskSummaryDTO {
    public SubTaskSummaryDTO(SubTaskEntity subtaskEntity){
        BeanUtils.copyProperties(subtaskEntity, this);
    }

    private long id;
    
    private String description;
    
    private TaskStatus status;

    private int position;
    
    private Instant createDate;
    
    private Instant dueDate;

    private boolean done;
    

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


}
