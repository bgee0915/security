package com.bgee.security.dao;

import com.bgee.security.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    // 账号的角色列
    List<Role> accountRole(@Param("accountId") Integer accountId);

}
