package com.bgee.security.service;

import com.bgee.security.dao.RoleDao;
import com.bgee.security.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {
    @Resource
    private RoleDao roleDao;

    public Role get(Integer id){return roleDao.get(id);}

    public Role get4Key(String keys){return roleDao.get4Key(keys);}

    public List<Role> accountRole(Integer accountId) {
        return roleDao.accountRole(accountId);
    }

    public int insert(Role role){return roleDao.insert(role);}

    public int update(Role role){return roleDao.update(role);}

    // update role status
    public int del(int id){ return roleDao.del(id);}

    // delete from s_account_role where role_id = #{id}
    public int delRole(int id){return roleDao.delRole(id);}

    // delete from s_account_role where account_id = #{accountId} and role_id = #{roleId}
    public int delAccountRole(Integer accountId, Integer roleId){return roleDao.delAccountRole(accountId,roleId);}



    // TODO
    @Transactional
    public int delete(int id){
        int result1 = del(id);
        int result2 = delRole(id);
        return result1 > 0 && result2 > 0  ? result1 : 0;
    }

}
