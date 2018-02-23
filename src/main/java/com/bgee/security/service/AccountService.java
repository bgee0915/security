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

    public Account get4Tel(String tel){return accountDao.get4Tel(tel);}

    public Account get4Account(String account){return accountDao.get4Account(account);}

    public Account getAccountPass(String account, String pass){ return accountDao.getAccountPass(account,pass); }

    public List<Account> list(){ return accountDao.list(); }

    public int del(Integer id){return accountDao.del(id); }

    public int insert(Account account){return accountDao.insert(account);}

    public int update(Account account){return accountDao.update(account);}

    public int insertAcctRoles(Integer accountId, Integer roles[]){return accountDao.insertAcctRoles(accountId,roles);}

    public int insertAcctInfo(Account account, Integer roles[]){
        int result1 = insert(account);
        int result2 = insertAcctRoles(account.getId(),roles);
        return result1 > 0 && result2 > 0 ?  1 : 0;
    }
}
