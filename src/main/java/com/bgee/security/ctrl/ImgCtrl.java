package com.bgee.security.ctrl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.entity.Img;
import com.bgee.security.entity.R;
import com.bgee.security.service.ImgService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/img")
public class ImgCtrl {
    private Log log = LogFactory.getLog(this.getClass());

    @Resource private ImgService imgService;

    @ResponseBody
    @RequestMapping("list")
    public R list(Img img){
        try {
            return new R(1,imgService.list(img.getUrl(),img.getType(),img.getRealm(),img.getStatus()),true);
        } catch (Exception e){
            log.error("ImgCtrl, list, e:",e);
            return new R(0,"获取图片列表失败",false);
        }
    }

    @ResponseBody
    @RequestMapping("listIcon")
    public R listIcon(){
        try {
            return new R(1,imgService.list(null,Img.IMG_TYPE_ICON,null,Img.IMG_STATUS_Y),true);
        } catch (Exception e){
            log.error("ImgCtrl, listIcon, e:",e);
            return new R(0,"获取Icon图片列表失败",false);
        }
    }

}
