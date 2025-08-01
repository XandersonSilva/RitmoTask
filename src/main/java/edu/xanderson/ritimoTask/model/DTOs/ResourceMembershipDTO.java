package edu.xanderson.ritimoTask.model.DTOs;

import edu.xanderson.ritimoTask.model.entity.RoleType;
import jakarta.validation.constraints.NotNull;

public class ResourceMembershipDTO {
    @NotNull
    private long id;

    @NotNull
    private long userId;

    @NotNull
    private long resourceId;

    private RoleType role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
