package com.bgee.security.ctrl;

import com.bgee.security.entity.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @ResponseBody
    @RequestMapping("list")
    public R list(){
        return null;
    }
}
