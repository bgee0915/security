package com.bgee.security.dao;

import com.bgee.security.entity.Authz;
import com.bgee.security.entity.dto.AuthzDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthzDao {

    Authz get(Integer id);

    List<AuthzDto> list();

    int del(Integer id);

    int insert(Authz authz);

    int update(Authz authz);
}
