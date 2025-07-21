package edu.xanderson.ritimoTask.model.DTOs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.CommentEntity;
import edu.xanderson.ritimoTask.model.entity.MentionEntity;

public class CommentSummaryDTO{
    public CommentSummaryDTO(){
    
    }

    public CommentSummaryDTO(CommentEntity commentEntity){
        BeanUtils.copyProperties(commentEntity, this);
        this.username = commentEntity.getUser().getUsername();
        this.taskId = commentEntity.getTask().getId();

        if (commentEntity.getMentions() != null) {
            List<MentionSummaryDTO> mentionsDTO = new ArrayList<>();
            for (MentionEntity mention : commentEntity.getMentions()) {
                mentionsDTO.add(new MentionSummaryDTO(mention));
            }
            this.mentions = mentionsDTO;
        }
    }

    private long id;

    private String comment;

    private String username;

    private long taskId;

    private List<MentionSummaryDTO> mentions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public List<MentionSummaryDTO> getMentions() {
        return mentions;
    }

    public void setMentions(List<MentionSummaryDTO> mentions) {
        this.mentions = mentions;
    }
}
