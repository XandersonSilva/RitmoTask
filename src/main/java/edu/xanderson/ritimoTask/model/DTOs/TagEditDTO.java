package edu.xanderson.ritimoTask.model.DTOs;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.TagEntity;
import jakarta.validation.constraints.NotNull;

public class TagEditDTO {
    public TagEditDTO(){

    }

    public TagEditDTO(TagEntity tagEntity){
        BeanUtils.copyProperties(tagEntity, this);
    }

    @NotNull
    private long id;
    
    private String tag;

    private String color;

    private String textColor;
    
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
}
