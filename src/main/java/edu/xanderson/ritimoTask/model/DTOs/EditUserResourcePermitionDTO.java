package edu.xanderson.ritimoTask.model.DTOs;

import edu.xanderson.ritimoTask.model.entity.RoleType;
import jakarta.validation.constraints.NotNull;

public class EditUserResourcePermitionDTO {
    @NotNull(message = "O ID do usuário não pode ser nulo.")
    private long userId;

    @NotNull(message = "O ID do recurso não pode ser nulo.")
    private long resoarceId;

    @NotNull(message = "O tipo de permissão não pode ser nulo.")
    private RoleType role;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getResoarceId() {
        return resoarceId;
    }

    public void setResoarceId(long resoarceId) {
        this.resoarceId = resoarceId;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
