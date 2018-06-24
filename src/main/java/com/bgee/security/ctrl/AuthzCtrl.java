package com.bgee.security.ctrl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.entity.Authz;
import com.bgee.security.entity.R;
import com.bgee.security.service.AuthzService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/authz")
public class AuthzCtrl {
    private Log log = LogFactory.getLog(this.getClass());

    @Resource
    private AuthzService authzService;

    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("a_authz_list")
    public R list(){
        try {
            return new R(1,authzService.list(),true);
        } catch (Exception e){
            log.error("AuthzController, list ,  e=" + e.getMessage());
            return new R(0,e.getMessage(),false);
        }
    }

    @ResponseBody
    @RequestMapping("/get")
    public R get(Integer id){
        try {
            return new R(1,authzService.get(id),true);
        } catch (Exception e){
            log.error("AuthzController, get ,  e=" + e.getMessage());
            return new R(0,e.getMessage(),false);
        }
    }

    @ResponseBody
    @RequestMapping("/add")
    @RequiresPermissions("a_authz_add")
    public R add(Authz authz){
        try {
            return new R(1,authzService.insert(authz),true);
        } catch (Exception e){
            log.error("AuthzController, add ,  e=" + e.getMessage());
            return new R(0,e.getMessage(),false);
        }
    }

    @ResponseBody
    @RequestMapping("/edit")
    @RequiresPermissions("a_authz_edit")
    public R edit(Authz authz){
        try {
            return new R(1,authzService.update(authz),true);
        } catch (Exception e){
            log.error("AuthzController, edit ,  e=" + e.getMessage());
            return new R(0,e.getMessage(),false);
        }
    }

    @ResponseBody
    @RequestMapping("/del")
    @RequiresPermissions("a_authz_del")
    public R del(Integer id){
        try{
            return new R(authzService.delete(id),"");
        }catch(Exception e){
            return new R(0,e.getMessage());
        }
    }

}
