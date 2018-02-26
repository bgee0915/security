package com.bgee.security.service;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.dao.AccountDao;
import com.bgee.security.entity.Account;
import com.bgee.security.entity.Authz;
import com.bgee.security.entity.Role;
import com.bgee.security.entity.dto.AccountDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountService {
    private Log log = LogFactory.getLog(this.getClass());

    @Resource
    private AccountDao accountDao;
    @Resource
    private RoleService roleService;

    public Account get(Integer id){
        return accountDao.get(id);
    }

    public AccountDto gets(Integer id){
        log.info("AccountService, gets, id= " + id);
        AccountDto dto = new AccountDto();
        Account account = get(id);
        log.info("AccountService, gets, account= " + account);

        List<Role> roles = roleService.accountRole(id);
        log.info("AccountService, gets, roles= " + roles);

        return dto.convert(account).setRoles(roles);
    }

    public Account get4Tel(String tel){return accountDao.get4Tel(tel);}

    public Account get4Account(String account){return accountDao.get4Account(account);}

    public Account getAccountPass(String account, String pass){ return accountDao.getAccountPass(account,pass); }

    public List<Account> list(){ return accountDao.list(); }

    public int del(Integer id){return accountDao.del(id); }

    public int delete(Integer id){
        int result1 = del(id);
        int result2 = roleService.delAccountRole(id,null);
        return result1 > 0 && result2 > 0 ? 1 : 0;
    }

    public int update(Account account){return accountDao.update(account);}

    public int updateAcctInfo(Account account, Integer roles[]){
        int result1 = roleService.delAccountRole(account.getId(),null);
        int result2 = insertAcctRoles(account.getId(),roles);
        int result3 = update(account);
        return result1 > 0 && result2 > 0 && result3 > 0 ? 1 : 0;
    }

    public int insert(Account account){return accountDao.insert(account);}

    public int insertAcctRoles(Integer accountId, Integer roles[]){return accountDao.insertAcctRoles(accountId,roles);}

    public int insertAcctInfo(Account account, Integer roles[]){
        int result1 = insert(account);
        int result2 = insertAcctRoles(account.getId(),roles);
        return result1 > 0 && result2 > 0 ?  1 : 0;
    }


    public List<Authz> accountAuthz(Integer accountId){
        return  accountDao.accountAuthz(accountId);
    }

}
