package edu.xanderson.ritimoTask.model.DTOs;

import jakarta.validation.constraints.NotNull;

public class AssignUsersDTO {
    @NotNull
    private long userId;

    @NotNull
    private long taskId;

    @NotNull
    private long boardId;

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }
}
