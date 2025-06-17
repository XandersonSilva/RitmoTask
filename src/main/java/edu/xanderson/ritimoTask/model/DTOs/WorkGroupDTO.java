package edu.xanderson.ritimoTask.model.DTOs;

import java.util.List;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.BoardEntity;
import edu.xanderson.ritimoTask.model.entity.OrganizationEntity;
import edu.xanderson.ritimoTask.model.entity.WorkGroupEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

    public class WorkGroupDTO {
        public WorkGroupDTO(){

        }

        public WorkGroupDTO(WorkGroupEntity workGroup){
            BeanUtils.copyProperties(workGroup, this);
        }
    
        private long id;
    
        @NotBlank
        private String name;
        
        private String identifier;
        
        @Email
        private String email;
        
        private String area;
        
        private String logo;
    
        @NotBlank
        private String description;
        
        private OrganizationEntity organization;
    
        private List<BoardEntity> boards;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public OrganizationEntity getOrganization() {
            return organization;
        }

        public void setOrganization(OrganizationEntity organization) {
            this.organization = organization;
        }

        public List<BoardEntity> getBoards() {
            return boards;
        }

        public void setBoards(List<BoardEntity> boards) {
            this.boards = boards;
        }
    
}
    
