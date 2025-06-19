package edu.xanderson.ritimoTask.model.DTOs;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.TagEntity;
import edu.xanderson.ritimoTask.model.entity.TaskEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TagDTO {
    public TagDTO(){

    }

    public TagDTO(TagEntity tagEntity){
        BeanUtils.copyProperties(tagEntity, this);
        this.taskId = tagEntity.getTask().getId();
    }

    private long id;
    
    @NotBlank
    private String tag;

    private String color = "#00ff00";

    private String textColor = "#ffffff";

    @NotNull
    private long taskId;

    private TaskEntity task;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
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
}
