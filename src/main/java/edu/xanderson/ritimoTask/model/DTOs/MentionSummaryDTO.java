package edu.xanderson.ritimoTask.model.DTOs;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.MentionEntity;
public class MentionSummaryDTO {
    public MentionSummaryDTO(){

    }

    public MentionSummaryDTO(MentionEntity mentionsEntity){
        BeanUtils.copyProperties(mentionsEntity, this);
        this.mentionedUser = mentionsEntity.getMentionedUser().getUsername();
    }

    private String mentionedUser;

    public String getMentionedUser() {
        return mentionedUser;
    }

    public void setMentionedUser(String mentionedUser) {
        this.mentionedUser = mentionedUser;
    }
}
