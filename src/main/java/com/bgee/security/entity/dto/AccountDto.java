package com.bgee.security.entity.dto;

import com.bgee.security.entity.Account;
import com.bgee.security.entity.Role;

import java.util.Date;
import java.util.List;

public class AccountDto extends Account{
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public AccountDto setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    public AccountDto convert(Account account){
        setId(account.getId());
        setAccount(account.getAccount());
        setPass(account.getPass());
        setTel(account.getTel());
        setSex(account.getSex());
        setStatus(account.getStatus());
        setCreateDate(account.getCreateDate());
        setCreateBy(account.getCreateBy());
        setModifyBy(account.getModifyBy());
        setModifyDate(account.getModifyDate());
        return this;
    }

}
