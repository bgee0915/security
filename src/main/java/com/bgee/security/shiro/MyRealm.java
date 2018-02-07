package com.bgee.security.shiro;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.constants.SessionConstants;
import com.bgee.security.entity.Account;
import com.bgee.security.service.AccountService;
import com.bgee.security.util.SessionUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class MyRealm extends AuthorizingRealm {

    private Log log = LogFactory.getLog(this.getClass());

    @Resource
    private AccountService accountService;

    // 权限认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    // 登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        String account = (String)token.getPrincipal();
        String pass = String.valueOf((char[])token.getCredentials());
        Account acct = accountService.getAccountPass(account,pass);
        if(acct == null){
            throw new UnknownAccountException("账号密码错误");
        }

        SessionUtil.set(SessionConstants.SESSION_ACCOUNT,acct);
        return new SimpleAuthenticationInfo(acct.getAccount(),pass,getName());
    }
}
