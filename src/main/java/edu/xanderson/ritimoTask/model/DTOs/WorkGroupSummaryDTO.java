package edu.xanderson.ritimoTask.model.DTOs;

import org.springframework.beans.BeanUtils;

import edu.xanderson.ritimoTask.model.entity.WorkGroupEntity;

    public class WorkGroupSummaryDTO {
        public WorkGroupSummaryDTO(){

        }

        public WorkGroupSummaryDTO(WorkGroupEntity workGroup){
            BeanUtils.copyProperties(workGroup, this);
        }
    
        private long id;
    
        private String name;
        
        private String identifier;
        
        private String email;
        
        private String area;
        
        private String logo;
    
        private String description;
        
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
}
    
