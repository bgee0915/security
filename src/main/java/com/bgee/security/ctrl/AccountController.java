package com.bgee.security.ctrl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.entity.Account;
import com.bgee.security.entity.R;
import com.bgee.security.service.AccountService;
import com.bgee.security.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/account")
public class AccountController {
    private Log log = LogFactory.getLog(this.getClass());

    @Resource
    private AccountService accountService;

    // 账号信息
    @ResponseBody
    @RequestMapping("/get")
    public R get(Integer id){
        try {
            return new R(1,accountService.get(id),true);
        } catch (Exception e){
            log.error("AccountController, get , id=" + id +",  e=" + e.getMessage());
            return new R(0,e.getMessage(),false);
        }
    }

    // 账号列表
    @ResponseBody
    @RequestMapping("/list")
    public R list(){
        try {
            return new R(1,accountService.list(),true);
        } catch (Exception e){
            log.error("AccountController, list , e=" + e.getMessage());
            return new R(0,e.getMessage(),false);
        }
    }


    // del
    @ResponseBody
    @RequestMapping("/del")
    public R del(Integer id){
        try {
            return new R(1,accountService.del(id),true);
        } catch (Exception e){
            log.error("AccountController, del , e=" + e.getMessage());
            return new R(0,e.getMessage(),false);
        }
    }

    // edit
    @ResponseBody
    @RequestMapping("/edit")
    public R edit(Account account){
        try {
            return new R(1,accountService.update(account),true);
        } catch (Exception e){
            log.error("Acco untController, edit , e=" + e.getMessage());
            return new R(0,e.getMessage(),false);
        }
    }


    @ResponseBody
    @RequestMapping("/add")
    public R add(Account account, @RequestParam(value="roles[]")Integer[] roles){
        try {
            Date date = new Date();
            Account sessionAcct = SessionUtil.getAcct();
            account.setCreateDate(date);
            account.setModifyDate(date);
            account.setModifyBy(sessionAcct.getAccount());
            account.setCreateBy(sessionAcct.getAccount());
            int result = accountService.insertAcctInfo(account,roles);
            if(result > 0){
                return new R(1 ,"");
            } else {
                return new R(0, "");
            }
        } catch (Exception e){
            log.error("AccountController, add, e=", e);
            return new R(0,e.getMessage(),false);
        }
    }

}
