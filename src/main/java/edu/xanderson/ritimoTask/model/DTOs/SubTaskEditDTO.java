package edu.xanderson.ritimoTask.model.DTOs;

import java.time.Instant;

import edu.xanderson.ritimoTask.model.entity.TaskStatus;
import jakarta.validation.constraints.NotNull;

public class SubTaskEditDTO {

    @NotNull
    private long id;
    
    private String description;
    
    private TaskStatus status;

    private int position;
    
    private Instant createDate = Instant.now();
    
    private Instant dueDate;

    private boolean done;

    private long taskId;

    @NotNull
    private long boardId;

    

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

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

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }


}
