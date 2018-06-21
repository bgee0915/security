package com.bgee.security.util;

import com.bgee.security.constants.SessionConstants;
import com.bgee.security.entity.Account;
import com.bgee.security.entity.Authz;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SessionUtil {
    private SessionUtil(){}

    private static HttpSession session;

    static {
        ServletRequestAttributes servletContainer = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletContainer.getRequest();
        session =  request.getSession(true);
    }

    private static HttpSession get(){
        return session;
    }

    public static HttpSession rm(String key){
        session.removeAttribute(key);
        return session;
    }

    public static HttpSession set(String key, Object value){
        session.setAttribute(key,value);
        return session;
    }

    public static Object get(String key){
        return session.getAttribute(key);
    }







    public static HttpSession setAcct(Account acct){
        set(SessionConstants.SESSION_ACCOUNT,acct);
        return session;
    }

    public static Account getAcct(){
        return (Account) get(SessionConstants.SESSION_ACCOUNT);
    }

    public static HttpSession setAuthz(List<Authz> authzList){
        set(SessionConstants.SESSION_ACCOUNT_AUTHZ,authzList);
        return session;
    }
    public static List<Authz> getAuthz(){
        return (List<Authz>) get(SessionConstants.SESSION_ACCOUNT_AUTHZ);
    }

}
