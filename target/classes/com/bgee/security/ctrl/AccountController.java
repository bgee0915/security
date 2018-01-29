package com.bgee.security.ctrl;

import com.bgee.security.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;

    @ResponseBody
    @RequestMapping("/get")
    public Object get(Integer id){
        try {
            return accountService.get(id);
        } catch (Exception e){
            return null;
        }
    }
}
