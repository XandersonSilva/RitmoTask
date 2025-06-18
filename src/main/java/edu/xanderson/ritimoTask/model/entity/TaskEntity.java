package edu.xanderson.ritimoTask.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.DTOs.TaskDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TaskEntity {
    public TaskEntity(){
        setStatusIfIsNull();
    }
    public TaskEntity(TaskDTO taskDTO){
        BeanUtils.copyProperties(taskDTO, this);
        this.column = new ColumnEntity();
        this.column.setId(taskDTO.getColumnId());
        setStatusIfIsNull();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;
    
    private LocalDateTime startDate;
    
    private LocalDateTime dueDate;
    
    @Column(nullable = false)
    private boolean isBlocked;

    @ManyToOne
    @JoinColumn(name = "column_id", nullable = false)
    private ColumnEntity column;
    
    @OneToMany(mappedBy = "task", cascade =  CascadeType.ALL)
    private List<TagEntity> tags;

    @OneToMany(mappedBy = "task", cascade =  CascadeType.ALL)
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
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
