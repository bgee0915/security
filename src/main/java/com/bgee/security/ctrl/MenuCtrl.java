package com.bgee.security.ctrl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.entity.Menu;
import com.bgee.security.entity.R;
import com.bgee.security.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/menu")
public class MenuCtrl {
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



    @ResponseBody
    @RequestMapping("add")
    public R add(Menu menu){
        try{
            return new R(1,menuService.insert(menu),true);
        }catch(Exception e){
            log.error("MenuController,add,e:",e);
            return new R(0,"添加失败",false);
        }
    }

    @ResponseBody
    @RequestMapping("del")
    public R del(Integer id){
        try {
            return new R(1,menuService.del(id),true);
        } catch (Exception e){
            log.error("MenuController,del,e:",e);
            return new R(0,"删除失败",false);
        }
    }

    @ResponseBody
    @RequestMapping("edit")
    public R edit(Menu menu){
        try{
            return new R(1,menuService.update(menu),true);
        }catch(Exception e){
            log.error("MenuController,edit,e:",e);
            return new R(0,"修改失败",false);
        }
    }
}
