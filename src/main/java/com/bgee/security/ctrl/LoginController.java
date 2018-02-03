package com.bgee.security.ctrl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.entity.Account;
import com.bgee.security.entity.R;
import com.bgee.security.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/login")
public class LoginController {
    private Log log = LogFactory.getLog(this.getClass());

    @Resource
    private AccountService accountService;

    // login test
    @ResponseBody
    @RequestMapping("/login")
    public R login(String account, String pass){
        try {
            Account acct = accountService.getAccountPass(account,pass);
            if(acct == null){
                return new R(0,"account null", false);
            } else {
                return new R(1,acct,true);
            }
        } catch (Exception e){
            log.error("LoginController, login, account=" + account + ", pass=" + pass + ",  e=" , e);
            return new R(0,e.getMessage(),false);
        }
    }
}
