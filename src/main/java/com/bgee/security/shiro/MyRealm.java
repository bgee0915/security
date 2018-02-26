package com.bgee.security.shiro;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.constants.SessionConstants;
import com.bgee.security.entity.Account;
import com.bgee.security.entity.Authz;
import com.bgee.security.entity.Role;
import com.bgee.security.service.AccountService;
import com.bgee.security.service.AuthzService;
import com.bgee.security.service.RoleService;
import com.bgee.security.util.SessionUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    private Log log = LogFactory.getLog(this.getClass());

    @Resource
    private AccountService accountService;
    @Resource
    private RoleService roleService;
    @Resource
    private AuthzService authzService;


    // 权限认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println(super.getAvailablePrincipal(principalCollection));
        System.out.println("-------------------------> ");
        System.out.println(principalCollection.getPrimaryPrincipal());
        System.out.println(principalCollection.getRealmNames());
        System.out.println(principalCollection.fromRealm(getName()).iterator().next());

        String account  = principalCollection.getPrimaryPrincipal().toString();
        Account acct = accountService.get4Account(account);
        List<Role> roleList = roleService.accountRole(acct.getId());
        Set<String> authzKey = new HashSet<>();
        for(Role role : roleList){
            List<Authz> authzList = authzService.getRoleAuthz(role.getId());
            for(Authz authz: authzList){
                authzKey.add(authz.getKeys());
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(authzKey);
        System.out.println("------------> " + info);
        System.out.println("============> " + authzKey);
        return info;
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
