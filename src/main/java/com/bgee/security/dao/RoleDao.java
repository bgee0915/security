package com.bgee.security.dao;

import com.bgee.security.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    // 账号的角色列
    List<Role> accountRole(@Param("accountId") Integer accountId);

    List<Role> list();

    int insert(Role role);

    int insertRoleAuthz(@Param("roleId")Integer roleId, @Param("authz")Integer authz[]);

    int update(Role role);

    int del(int id);

    int delRole(int id);

    int delAccountRole(@Param("accountId")Integer accountId, @Param("roleId")Integer roleId);

    Role get(Integer id);
    Role get4Key(@Param("keys")String keys);

}
