package edu.xanderson.ritimoTask.model.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserVerifyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private UUID uuid;
    
    @Column(nullable = false)
    private Instant dateExpiration;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Instant getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Instant dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    
}
