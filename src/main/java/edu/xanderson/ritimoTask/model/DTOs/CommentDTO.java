package edu.xanderson.ritimoTask.model.DTOs;

import java.util.List;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.CommentEntity;
import edu.xanderson.ritimoTask.model.entity.MentionEntity;
import edu.xanderson.ritimoTask.model.entity.TaskEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentDTO {
    public CommentDTO(){
    
    }

    public CommentDTO(CommentEntity commentEntity){
        BeanUtils.copyProperties(commentEntity, this);
        this.userId = commentEntity.getUser().getId();
        this.taskId = commentEntity.getTask().getId();
    }

    private long id;

    @NotBlank
    private String comment;

    private long userId;

    private UserEntity user;

    @NotNull
    private long taskId;

    private TaskEntity task;

    private List<MentionEntity> mentions;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public List<MentionEntity> getMentions() {
        return mentions;
    }

    public void setMentions(List<MentionEntity> mentions) {
        this.mentions = mentions;
    }


}
