package com.bgee.security.service;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.dao.AccountDao;
import com.bgee.security.entity.Account;
import com.bgee.security.entity.Authz;
import com.bgee.security.entity.Role;
import com.bgee.security.entity.dto.AccountDto;
import com.bgee.security.util.SessionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountService {
    private Log log = LogFactory.getLog(this.getClass());

    @Resource private AccountDao accountDao;
    @Resource private RoleService roleService;

    /** 获取账号信息 */
    public Account get(Integer id){ return accountDao.get(id); }

    /** 获取账号信息及角色信息 */
    public AccountDto gets(Integer id){
        log.info("AccountService, gets, id= " + id);
        AccountDto dto = new AccountDto();
        Account account = get(id);
        log.info("AccountService, gets, account= " + account);

        List<Role> roles = roleService.accountRole(id);
        log.info("AccountService, gets, roles= " + roles);

        return dto.convert(account).setRoles(roles);
    }

    /** 通过电话获取账号 */
    public Account get4Tel(String tel){return accountDao.get4Tel(tel);}

    /** 通过账号获取账号 */
    public Account get4Account(String account){return accountDao.get4Account(account);}

    /** 通过账号密码获取账号 */
    public Account getAccountPass(String account, String pass){ return accountDao.getAccountPass(account,pass); }

    /** 获取账号列表 */
    public List<Account> list(){ return accountDao.list(); }

    /** 删除账号 */
    public int del(Integer id){return accountDao.del(id); }

    /** 删除账号信息及账号的角色 */
    @Transactional
    public int delete(Integer id){
        int result1 = del(id);
        int result2 = roleService.delAccountRole(id,null);
        return result1 > 0 && result2 > 0 ? 1 : 0;
    }

    /** 更新账号 */
    public int update(Account account){return accountDao.update(account);}

    // TODO 先删除后添加 的问题
    /** 跟新账号信息及角色信息 */
    @Transactional
    public int updateAcctInfo(Account account, Integer roles[]){
        int result1 = roleService.delAccountRole(account.getId(),null);
        int result2 = insertAcctRoles(account.getId(),roles);
        int result3 = update(account);

        if(result1 > 0 && result2 > 0 && result3 > 0){
            SessionUtil.setRole(roleService.accountRole(SessionUtil.getAcct().getId()));    // 如果的话 更新用户的角色
            return 1;
        } else {
            return 0;
        }
    }

    /** 添加账号 */
    public int insert(Account account){return accountDao.insert(account);}

    /** 添加账号角色关联信息 */
    public int insertAcctRoles(Integer accountId, Integer roles[]){return accountDao.insertAcctRoles(accountId,roles);}

    /** 添加账号和角色 */
    @Transactional
    public int insertAcctInfo(Account account, Integer roles[]){
        int result1 = insert(account);
        int result2 = insertAcctRoles(account.getId(),roles);
        return result1 > 0 && result2 > 0 ?  1 : 0;
    }

    /** 账号的权限信息 */
    public List<Authz> accountAuthz(Integer accountId){
        return  accountDao.accountAuthz(accountId);
    }

}
