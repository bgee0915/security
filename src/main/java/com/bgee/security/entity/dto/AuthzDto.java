package com.bgee.security.entity.dto;

import com.bgee.security.entity.Authz;

public class AuthzDto extends Authz {
    private String pName;

    public String getpName() {
        return pName;
    }

    public AuthzDto setpName(String pName) {
        this.pName = pName;
        return this;
    }

    public AuthzDto convert(Authz authz){
        setId(authz.getId());
        setPid(authz.getPid());
        setName(authz.getName());
        setKeys(authz.getKeys());
        setStatus(authz.getStatus());
        return this;
    }
}
