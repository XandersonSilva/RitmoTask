package edu.xanderson.ritimoTask.model.DTOs;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.ColumnEntity;

public class ColumnSummaryDTO {
    public ColumnSummaryDTO(ColumnEntity column){
        BeanUtils.copyProperties(column, this);
        this.boardId = column.getBoard().getId();
    }

    private long id;
    private String name;
    private long columnOrder;
    private long boardId;

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
}
