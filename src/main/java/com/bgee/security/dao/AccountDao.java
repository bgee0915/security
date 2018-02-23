package com.bgee.security.dao;

import com.bgee.security.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao {

    Account get(Integer id);

    Account get4Tel(String tel);

    Account get4Account(String account);

    Account getAccountPass(@Param("account")String account,@Param("pass")String pass);

    List<Account> list();

    int del(Integer id);

    int insert(Account account);

    int update(Account account);

    int insertAcctRoles(@Param("accountId")Integer accountId, @Param("roles")Integer roles[]);
}
