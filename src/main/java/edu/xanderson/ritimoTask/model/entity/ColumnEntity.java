package edu.xanderson.ritimoTask.model.entity;

import java.util.List;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.DTOs.ColumnDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(
        name = "uk_board_column_order", // Nome para a restrição no BD
        columnNames = {"board_id", "column_order"}
    )
)
public class ColumnEntity {
    public ColumnEntity(){

    }
    public ColumnEntity(ColumnDTO columnDTO){
        BeanUtils.copyProperties(columnDTO, this);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "column_order", nullable = false)
    private long columnOrder;
    
    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity board;
    
    @OneToMany(mappedBy = "column", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntity> tasks;

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

    public long getColumnOrder() {
        return columnOrder;
    }

    public void setColumnOrder(long columnOrder) {
        this.columnOrder = columnOrder;
    }

    public BoardEntity getBoard() {
        return board;
    }

    public void setBoard(BoardEntity board) {
        this.board = board;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

}
