package edu.xanderson.ritimoTask.model.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.DTOs.BoardDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class BoardEntity {
    public BoardEntity(){

    }
    
    public BoardEntity(BoardDTO board){
        BeanUtils.copyProperties(board, this);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;
    
    private String identifier;
    
    private String email;
    
    private String area_performance;
    
    private String logo = "default_logo";

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "workGroup_id")
    private WorkGroupEntity workGroup;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ColumnEntity> columns;

    @OneToMany(
        mappedBy = "board", 
        cascade = CascadeType.ALL,
        orphanRemoval = true,      // Remove filhos que não estão mais na coleção
        fetch = FetchType.LAZY
    )
    private List<BoardMembership> memberships = new ArrayList<>(); 

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
    
    public List<BoardMembership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<BoardMembership> memberships) {
        this.memberships = memberships;
    }
}
