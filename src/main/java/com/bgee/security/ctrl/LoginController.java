package com.bgee.security.ctrl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.entity.Account;
import com.bgee.security.entity.R;
import com.bgee.security.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
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


    @ResponseBody
    @RequestMapping("/login")
    public R login(String account, String pass){
        try {
            Subject subject  = SecurityUtils.getSubject();
            if(subject.isAuthenticated()){
                return new R(1,"登陸成功");
            }

            try {
                UsernamePasswordToken token = new UsernamePasswordToken(account,pass);
                token.setRememberMe(true);
                subject.login(token);
                return new R(1,"登陸成功");
            } catch (UnknownAccountException | IncorrectCredentialsException e1){
                log.error("账号密码不存在 e1," + e1.getMessage());
                e1.printStackTrace();
            } catch (AuthenticationException | AuthorizationException e2){
                log.error("权限认证失败 e2," + e2.getMessage());
                e2.printStackTrace();
            } catch (Exception e3){
                log.error("登陸失败 e3," + e3.getMessage());
                e3.printStackTrace();
            }

            return new R(0,"登陸失败");
        } catch (Exception e){
            log.error("LoginController, login, account=" + account + ", pass=" + pass + ",  e=" , e);
            e.printStackTrace();
            return new R(0,e.getMessage(),false);
        }
    }
}
