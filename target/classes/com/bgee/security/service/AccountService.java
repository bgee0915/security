package com.bgee.security.service;

import com.bgee.security.dao.AccountDao;
import com.bgee.security.entity.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountService {
    @Resource
    private AccountDao accountDao;

    public Account get(Integer id){
        return accountDao.get(id);
    }
}
