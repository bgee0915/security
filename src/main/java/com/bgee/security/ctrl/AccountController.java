package com.bgee.security.ctrl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.entity.R;
import com.bgee.security.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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


}
