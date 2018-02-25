package com.bgee.security.service;

import com.bgee.security.dao.RoleDao;
import com.bgee.security.entity.Role;
import com.bgee.security.entity.dto.RoleDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {
    @Resource
    private RoleDao roleDao;

    @Resource
    private AuthzService authzService;

    public Role get(Integer id){return roleDao.get(id);}

    public RoleDto gets(Integer id){
        Role role = get(id);
        RoleDto dto = new RoleDto();
        return dto.convert(role).setAuthz(authzService.getRoleAuthz(id));
    }

    public Role get4Key(String keys){return roleDao.get4Key(keys);}

    public List<Role> accountRole(Integer accountId) {
        return roleDao.accountRole(accountId);
    }

    public List<Role> list(){return roleDao.list();}

    public int insert(Role role){return roleDao.insert(role);}

    public int insertRoleAuthz(Integer roleId, Integer authz[]){return roleDao.insertRoleAuthz(roleId,authz);}

    public int insertRoleInfo(Role role, Integer authz[]){
        int result1 = insert(role);
        int result2 = insertRoleAuthz(role.getId(),authz);
        return result1 > 0 && result2 > 0 ?  1 : 0;
    }

    public int update(Role role){return roleDao.update(role);}

    public int updateRoleInfo(Role role, Integer authz[]){
        int result1 = authzService.delRoleAuthz(role.getId(),null);
        int result2 = insertRoleAuthz(role.getId(),authz);
        int result3 = update(role);
        return result1 > 0 && result2 > 0 && result3 > 0 ? 1 : 0;
    }

    // update role status
    public int del(int id){ return roleDao.del(id);}

    // delete from s_account_role where role_id = #{id}
    public int delRole(int id){return roleDao.delRole(id);}

    // delete from s_account_role where account_id = #{accountId} and role_id = #{roleId}
    public int delAccountRole(Integer accountId, Integer roleId){
        return roleDao.delAccountRole(accountId,roleId);
    }

    // TODO
    @Transactional
    public int delete(int id){
        int result1 = del(id);
        int result2 = delRole(id);
        int result3 = authzService.delRoleAuthz(id,null);
        return result1 > 0 && result2 > 0  && result3 > 0 ? result1 : 0;
    }

}
