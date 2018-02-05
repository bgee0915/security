package com.bgee.security.dao;

import com.bgee.security.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao {
    Account get(Integer id);

    Account getAccountPass(@Param("account")String account,@Param("pass")String pass);
}
