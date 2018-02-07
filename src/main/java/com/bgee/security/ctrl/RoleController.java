package com.bgee.security.ctrl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.constants.SessionConstants;
import com.bgee.security.entity.Account;
import com.bgee.security.entity.R;
import com.bgee.security.entity.Role;
import com.bgee.security.service.RoleService;
import com.bgee.security.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping("/list")
    public R list(){
        try {
            Account acct = SessionUtil.getAcct();
            log.info("RoleController, list, session acct: " + acct);
            List<Role> list = roleService.accountRole(acct.getId());
            return new R(1,list,true);
        } catch (Exception e){
            log.error("RoleController, list, e, " , e);
            return new R(0,e.getMessage(),false);
        }
    }
}
