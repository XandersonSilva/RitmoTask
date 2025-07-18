package edu.xanderson.ritimoTask.model.DTOs;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.ColumnEntity;
import edu.xanderson.ritimoTask.model.entity.CommentEntity;
import edu.xanderson.ritimoTask.model.entity.TagEntity;
import edu.xanderson.ritimoTask.model.entity.TaskEntity;
import edu.xanderson.ritimoTask.model.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskEditeDTO {
    public TaskEditeDTO(){
        setStatusIfIsNull();
    }
    public TaskEditeDTO(TaskEntity taskEntity){
        BeanUtils.copyProperties(taskEntity, this);
        setStatusIfIsNull();
    }

    @NotNull
    private long id;
    private String title;
    
    private String description;
    
    private TaskStatus status;
    
    private Instant startDate;
    
    private Instant dueDate;
    
    private boolean isCanceled;

    private boolean isBlocked;

    private long columnId;

    private ColumnEntity column;
    
    private List<TagEntity> tags;

    private List<CommentEntity> comments;

    private void setStatusIfIsNull(){
        if (this.status == null) {
            this.status = TaskStatus.TODO;
        }
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    public Instant getStartDate() {
        return startDate;
    }
    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }
    public Instant getDueDate() {
        return dueDate;
    }
    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }
    public boolean isCanceled() {
        return isCanceled;
    }
    public void setCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }
    public boolean isBlocked() {
        return isBlocked;
    }
    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
    public long getColumnId() {
        return columnId;
    }
    public void setColumnId(long columnId) {
        this.columnId = columnId;
    }
    public ColumnEntity getColumn() {
        return column;
    }
    public void setColumn(ColumnEntity column) {
        this.column = column;
    }
    public List<TagEntity> getTags() {
        return tags;
    }
    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }
    public List<CommentEntity> getComments() {
        return comments;
    }
    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    
}
