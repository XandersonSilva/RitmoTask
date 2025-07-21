package edu.xanderson.ritimoTask.model.DTOs;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.TagEntity;

public class TagSummaryDTO {
    public TagSummaryDTO(){

    }

    public TagSummaryDTO(TagEntity tagEntity){
        BeanUtils.copyProperties(tagEntity, this);
    }

    private long id;
    
    private String tag;

    private String color;

    private String textColor;

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
