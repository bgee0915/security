package com.bgee.security.ctrl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.constants.SessionConstants;
import com.bgee.security.entity.Account;
import com.bgee.security.entity.R;
import com.bgee.security.entity.Role;
import com.bgee.security.service.RoleService;
import com.bgee.security.util.SessionUtil;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    private Log log = LogFactory.getLog(this.getClass());

    @Resource
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/get")
    public R get(Integer id){
        try {
            return new R(1,roleService.get(id),true);
        } catch (Exception e){
            log.error("RoleController, get, e, " , e);
            return new R(0,e.getMessage(),false);
        }
    }

    @ResponseBody
    @RequestMapping("/gets")
    public R gets(Integer id){
        try {
            return new R(1,roleService.gets(id),true);
        } catch (Exception e){
            log.error("RoleController, get, e, " , e);
            return new R(0,e.getMessage(),false);
        }
    }

    @ResponseBody
    @RequestMapping("/accountRole")
    public R accountRole(){
        try {
            Account acct = SessionUtil.getAcct();
            List<Role> list = roleService.accountRole(acct.getId());
            return new R(1,list,true);
        } catch (Exception e){
            log.error("RoleController, accountRole, e, " , e);
            return new R(0,e.getMessage(),false);
        }
    }

    @ResponseBody
    @RequestMapping("/list")
    public R list(){
        try {
            List<Role> list = roleService.list();
            return new R(1,list,true);
        } catch (Exception e){
            log.error("RoleController, list, e, " , e);
            return new R(0,e.getMessage(),false);
        }
    }

    @ResponseBody
    @RequestMapping("/del")
    public R del(Integer id){
        try {
            Account acct = SessionUtil.getAcct();
            return new R(roleService.delAccountRole(acct.getId(),id),"");
        } catch (Exception e){
            log.error("RoleController, del, e, " , e);
            return new R(0,e.getMessage(),false);
        }
    }

    @ResponseBody
    @RequestMapping("/add")
    public R add(Role role, @RequestParam(value="authz[]")Integer []authz){
        try {
            Role selRole = roleService.get4Key(role.getKeys());
            if(selRole == null){
                return new R(roleService.insertRoleInfo(role, authz),"");
            } else {
                log.error("RoleController, add, 存在的keys");
               return new R(0,"存在的keys");
            }
        } catch (Exception e){
            log.error("RoleController, add, e, " , e);
            return new R(0,e.getMessage(),false);
        }
    }

    @ResponseBody
    @RequestMapping("/edit")
    public R edit(Role role, @RequestParam(value="authz[]")Integer []authz){
        try {
            return new R(roleService.updateRoleInfo(role,authz),"");
        } catch (Exception e){
            log.error("RoleController, edit, e, " , e);
            return new R(0,e.getMessage(),false);
        }
    }
}
