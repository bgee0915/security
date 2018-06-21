package com.bgee.security.ctrl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.entity.R;
import com.bgee.security.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/menu")
public class MenuController {
    private Log log = LogFactory.getLog(this.getClass());

    @Resource private MenuService menuService;

    @ResponseBody
    @RequestMapping("list")
    public R list(){
        try{
            return new R(1,menuService.list(),true);
        }catch(Exception e){
            log.error("MenuController,list, e:" + e.getMessage());
            return new R(0,"获取菜单列表失败",false);
        }
    }
}
