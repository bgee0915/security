package com.bgee.security.entity.dto;

import com.bgee.security.entity.Authz;
import com.bgee.security.entity.Role;

import java.util.List;

public class RoleDto extends Role{
    private List<Authz> authz;

    public List<Authz> getAuthz() {
        return authz;
    }

    public RoleDto setAuthz(List<Authz> authz) {
        this.authz = authz;
        return this;
    }


    public RoleDto convert(Role role){
        setId(role.getId());
        setName(role.getName());
        setKeys(role.getKeys());
        setStatus(role.getStatus());
        return this;
    }
}
