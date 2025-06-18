package edu.xanderson.ritimoTask.model.DTOs;

import java.util.List;

import edu.xanderson.ritimoTask.model.entity.BoardEntity;
import edu.xanderson.ritimoTask.model.entity.TaskEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ColumnDTO {
    private long id;

    @NotBlank
    private String name;

    private long columnOrder;
    
    @NotNull
    private long boardId;

    private BoardEntity board;
    
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

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
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
