package com.bgee.security.dao;

import com.bgee.security.entity.Authz;
import com.bgee.security.entity.dto.AuthzDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthzDao {

    Authz get(Integer id);

    List<Authz> getRoleAuthz(Integer roleId);

    List<AuthzDto> list();

    int del(Integer id);

    int delSon(Integer pid);

    int delRoleAuthz(@Param("roleId")Integer roleId, @Param("authzId")Integer authzId);

    int insert(Authz authz);

    int update(Authz authz);
}
