package com.bgee.security.service;

import com.bgee.security.dao.RoleDao;
import com.bgee.security.entity.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {
    @Resource
    private RoleDao roleDao;

    public List<Role> accountRole(Integer accountId) {
        return roleDao.accountRole(accountId);
    }

}
