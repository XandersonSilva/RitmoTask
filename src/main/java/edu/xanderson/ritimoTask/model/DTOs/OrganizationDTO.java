package edu.xanderson.ritimoTask.model.DTOs;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.OrganizationEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class OrganizationDTO {
    public OrganizationDTO(){
    }
    
    public OrganizationDTO(OrganizationEntity organization){
        BeanUtils.copyProperties(organization, this);
    }
    
    private long id;
    
    @NotBlank(message = "O nome não pode ser nulo")
    private String name;
    
    private String identifier;
    
    private String phoneNumber;
    
    
    @Email(message = "O formato de e-mail é inválido")
    private String email;
    
    private String sector;
    
    private String logoUrl = "default_logo";;

    @NotBlank(message = "A descrição é obrigatoria")
    private String description;
    
    private String websiteUrl;


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
