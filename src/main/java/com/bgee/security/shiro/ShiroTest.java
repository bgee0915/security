package com.bgee.security.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;

public class ShiroTest {
    public static void main(String[] args){
        System.out.println("my first apache shiro application");

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // 获取当前的 “用户”
        Subject currentUser = SecurityUtils.getSubject();
        // 获取session
        Session session = currentUser.getSession();
        session.setAttribute("someKey","aValue");
        String value = (String)session.getAttribute("someKey");
        System.out.println("---------- session keys value=  " + value);

        // 如果未登录认证
        if(!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr","vespa");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                System.out.println("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                System.out.println("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                System.out.println("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
        }

        System.out.println("---------- user [ " + currentUser.getPrincipal() + " ] logged in successfully");

        if (currentUser.hasRole("schwartz")){
            System.out.println("---------- user has role : schwartz"  );
        } else {
            System.out.println("---------- user hasn't role : schwartz"  );
        }

        if (currentUser.isPermitted("lightsaber:weild")) {
            System.out.println("---------- user has permission : lightsaber:weild");
        } else {
            System.out.println("---------- user hasn't permission : lightsaber:weild");
        }

        if ( currentUser.isPermitted( "winnebago:drive:eagle5" ) ) {
            System.out.println("---------- You are permitted to 'drive' the 'winnebago' with license plate (id) 'eagle5'. Here are the keys - have fun!");
        } else {
            System.out.println("---------- Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }


        currentUser.logout();
        System.exit(0);
    }
}
