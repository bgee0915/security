package com.bgee.security.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    private SessionUtil(){}

    private static HttpSession get(){
        ServletRequestAttributes servletContainer = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletContainer.getRequest();
        return request.getSession(true);
    }

    public static HttpSession rm(String key){
        HttpSession session = get();
        session.removeAttribute(key);
        return session;
    }

    public static HttpSession set(String key, Object value){
        HttpSession session = get();
        session.setAttribute(key,value);
        return session;
    }

    public static Object get(String key){
        return get().getAttribute(key);
    }

}
