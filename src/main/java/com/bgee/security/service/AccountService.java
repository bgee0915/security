package com.bgee.security.service;

import com.bgee.security.dao.AccountDao;
import com.bgee.security.entity.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountService {
    @Resource
    private AccountDao accountDao;

    public Account get(Integer id){
        return accountDao.get(id);
    }

    public Account getAccountPass(String account, String pass){
        return accountDao.getAccountPass(account,pass);
    }

    public List<Account> list(){ return accountDao.list(); }

    public int del(Integer id){return accountDao.del(id);}
}
