package edu.xanderson.ritimoTask.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String tag;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String textColor;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;
}
