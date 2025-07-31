package edu.xanderson.ritimoTask.model.DTOs;

import java.util.List;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.BoardEntity;
import edu.xanderson.ritimoTask.model.entity.ColumnEntity;
import edu.xanderson.ritimoTask.model.entity.WorkGroupEntity;
import jakarta.validation.constraints.NotNull;

public class BoardEditDTO {
    public BoardEditDTO(){

    }
    
    public BoardEditDTO(BoardEntity board){
        BeanUtils.copyProperties(board, this);
    }

    @NotNull
    private long id;
    
    private String name;
    
    private String identifier;
    
    private String email;
    
    private String area_performance;
    
    private String logo;

    private String description;

    private WorkGroupEntity workGroup;

    private List<ColumnEntity> columns;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArea_performance() {
        return area_performance;
    }

    public void setArea_performance(String area_performance) {
        this.area_performance = area_performance;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WorkGroupEntity getWorkGroup() {
        return workGroup;
    }

    public void setWorkGroup(WorkGroupEntity workGroup) {
        this.workGroup = workGroup;
    }

    public List<ColumnEntity> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnEntity> columns) {
        this.columns = columns;
    }
}
