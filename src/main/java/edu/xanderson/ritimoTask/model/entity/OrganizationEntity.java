package edu.xanderson.ritimoTask.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class OrganizationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String name;
    
    private String identifier;
    
    private String phoneNumber;
    
    private String email;
    
    private String sector;
    
    private String logoUrl;
    
    @Column(nullable = false)
    private String description;
    
    private String websiteUrl;
    
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<WorkGroupEntity> groupEntities;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<OrganizationMembership> memberships = new ArrayList<>();


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
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSector() {
        return sector;
    }
    public void setSector(String sector) {
        this.sector = sector;
    }
    public String getLogoUrl() {
        return logoUrl;
    }
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getWebsiteUrl() {
        return websiteUrl;
    }
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}
