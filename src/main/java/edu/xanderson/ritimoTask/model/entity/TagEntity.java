package edu.xanderson.ritimoTask.model.entity;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.DTOs.TagDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TagEntity {
    public TagEntity(){

    }

    public TagEntity(TagDTO tagTDO ){
        BeanUtils.copyProperties(tagTDO, this);
        this.task = new TaskEntity();
        this.task.setId(tagTDO.getTaskId());
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String tag;
    
    //Padrão de cores hexadeciamal aceito
    //Cor padrão verde
    @Column(nullable = false)
    private String color = "#00ff00";

    //Cor padrão branco
    @Column(nullable = false)
    private String textColor = "#ffffff";

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
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

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }
}
