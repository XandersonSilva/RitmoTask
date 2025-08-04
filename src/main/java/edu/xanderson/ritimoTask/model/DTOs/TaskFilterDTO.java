package edu.xanderson.ritimoTask.model.DTOs;

import java.time.Instant;

import edu.xanderson.ritimoTask.model.entity.TaskStatus;
import jakarta.validation.constraints.NotNull;

public class TaskFilterDTO {

    @NotNull
    private long boardId;
    private TaskStatus status;
    private Boolean isCanceled;
    private Boolean isBlocked;
    private Instant dueDateStart;
    private Instant dueDateEnd;
    private Instant startDateStart;
    private Instant startDateEnd;
    private Instant today;

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Boolean getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(Boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Instant getDueDateStart() {
        return dueDateStart;
    }

    public void setDueDateStart(Instant dueDateStart) {
        this.dueDateStart = dueDateStart;
    }

    public Instant getDueDateEnd() {
        return dueDateEnd;
    }

    public void setDueDateEnd(Instant dueDateEnd) {
        this.dueDateEnd = dueDateEnd;
    }

    public Instant getStartDateStart() {
        return startDateStart;
    }

    public void setStartDateStart(Instant startDateStart) {
        this.startDateStart = startDateStart;
    }

    public Instant getStartDateEnd() {
        return startDateEnd;
    }

    public void setStartDateEnd(Instant startDateEnd) {
        this.startDateEnd = startDateEnd;
    }

    public Instant getToday() {
        return today;
    }

    public void setToday(Instant today) {
        this.today = today;
    }
}