package com.bgee.security.service;

import com.bgee.security.dao.AuthzDao;
import com.bgee.security.entity.Authz;
import com.bgee.security.entity.dto.AuthzDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthzService {
    @Resource
    private AuthzDao authzDao;


    public Authz get(Integer id){return authzDao.get(id);}

    public List<AuthzDto> list(){return authzDao.list();}

    public int del(Integer id){return authzDao.del(id);}

    public int delSon(Integer pid){return authzDao.delSon(pid);}

    public int delete(Integer id){
        Authz authz = get(id);
        if(authz.getPid() == 0){
            delSon(id);
        }
        return del(id);
    }

    public int insert(Authz authz){return authzDao.insert(authz);}

    public int update(Authz authz){return authzDao.update(authz);}
}
