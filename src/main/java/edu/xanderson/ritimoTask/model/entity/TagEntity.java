package edu.xanderson.ritimoTask.model.entity;

import java.util.List;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.DTOs.TagDTO;
import edu.xanderson.ritimoTask.model.DTOs.TagEditDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TagEntity {
    public TagEntity(){

    }

    public TagEntity(TagDTO tagTDO ){
        BeanUtils.copyProperties(tagTDO, this);
        if (tagTDO.getBoardId() != 0) {
            this.board.setId(tagTDO.getBoardId());
        }
    }

    public TagEntity(TagEditDTO tagTDO ){
        BeanUtils.copyProperties(tagTDO, this);
        if (tagTDO.getBoardId() != 0) {
            this.board.setId(tagTDO.getBoardId());
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String tag;
    
    //Padrão de cores hexadeciamal aceito
    //Cor padrão preto
    @Column(nullable = false)
    private String color = "#000000";

    //Cor padrão branco
    @Column(nullable = false)
    private String textColor = "#ffffff";

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private BoardEntity board;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<TaskTagsEntity> taskTags;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public BoardEntity getBoard() {
        return board;
    }

    public void setBoard(BoardEntity board) {
        this.board = board;
    }
}
