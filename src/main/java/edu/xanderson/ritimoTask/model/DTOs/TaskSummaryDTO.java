package edu.xanderson.ritimoTask.model.DTOs;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import edu.xanderson.ritimoTask.model.entity.CommentEntity;
import edu.xanderson.ritimoTask.model.entity.SubTaskEntity;
import edu.xanderson.ritimoTask.model.entity.TaskEntity;
import edu.xanderson.ritimoTask.model.entity.TaskStatus;
import edu.xanderson.ritimoTask.model.entity.TaskTagsEntity;
import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.service.TagService;
import jakarta.validation.constraints.NotNull;

public class TaskSummaryDTO {
    @Autowired
    private TagService tagService;

    
    public TaskSummaryDTO(){
        setStatusIfIsNull();
    }
    public TaskSummaryDTO(TaskEntity taskEntity){
        BeanUtils.copyProperties(taskEntity, this);
        setStatusIfIsNull();
        
        //Preenchendo campos que o BeanUtils não consegue por conta do 
        //tipo original não ser igual ao dessa classe
        if (taskEntity.getColumn() != null) {
            this.column = new ColumnSummaryDTO(taskEntity.getColumn());
        }
        
        if (taskEntity.getTags() != null && taskEntity.getColumn().getBoard() != null) {
            List<TagSummaryDTO> tagsDTO = new ArrayList<>();
            TagEditDTO tagDTO = new TagEditDTO();
            for (TaskTagsEntity tagTask : taskEntity.getTags()) {
                tagDTO.setId(tagTask.getTag().getId());
                tagDTO.setBoardId(taskEntity.getColumn().getBoard().getId());
                
    
                UserEntity currentUser = (UserEntity) SecurityContextHolder
                                                    .getContext()
                                                    .getAuthentication()
                                                    .getPrincipal();
                TagSummaryDTO tag = tagService.getTag(tagDTO, currentUser.getId());
                tagsDTO.add(tag);
            }
            this.tags = tagsDTO;
        }

        if (taskEntity.getComments() != null) {
            List<CommentSummaryDTO> commentDTO = new ArrayList<>();
            for (CommentEntity comment : taskEntity.getComments()) {
                commentDTO.add(new CommentSummaryDTO(comment));
            }
            this.comments = commentDTO;
        }

        if (taskEntity.getSubTasks() != null) {
            List<SubTaskSummaryDTO> subtaskDTO = new ArrayList<>();
            for (SubTaskEntity subtask : taskEntity.getSubTasks()) {
                subtaskDTO.add(new SubTaskSummaryDTO(subtask));
            }
            this.subTasks = subtaskDTO;
        }
    }

    @NotNull
    private long id;
    private String title;
    
    private String description;
    
    private TaskStatus status;
    
    private Instant startDate;
    
    private Instant dueDate;
    
    private boolean isCanceled;

    private boolean isBlocked;

    private ColumnSummaryDTO column;
    
    private List<TagSummaryDTO> tags;

    private List<CommentSummaryDTO> comments;

    private List<SubTaskSummaryDTO> subTasks;

    private void setStatusIfIsNull(){
        if (this.status == null) {
            this.status = TaskStatus.TODO;
        }
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public TaskStatus getStatus() {
        return status;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
    public Instant getStartDate() {
        return startDate;
    }
    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }
    public Instant getDueDate() {
        return dueDate;
    }
    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }
    public boolean isCanceled() {
        return isCanceled;
    }
    public void setCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }
    public boolean isBlocked() {
        return isBlocked;
    }
    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
    public ColumnSummaryDTO getColumn() {
        return column;
    }
    public void setColumn(ColumnSummaryDTO column) {
        this.column = column;
    }
    public List<TagSummaryDTO> getTags() {
        return tags;
    }
    public void setTags(List<TagSummaryDTO> tags) {
        this.tags = tags;
    }
    public List<CommentSummaryDTO> getComments() {
        return comments;
    }
    public void setComments(List<CommentSummaryDTO> comments) {
        this.comments = comments;
    }
    public List<SubTaskSummaryDTO> getSubTasks() {
        return subTasks;
    }
    public void setSubTasks(List<SubTaskSummaryDTO> subTasks) {
        this.subTasks = subTasks;
    }
    
}
