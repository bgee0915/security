package com.bgee.security.ctrl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.annotation.LPerm;
import com.bgee.security.entity.Account;
import com.bgee.security.entity.Authz;
import com.bgee.security.entity.R;
import com.bgee.security.service.AccountService;
import com.bgee.security.util.SessionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final String LOGIN_CODE = "loginCode";

    private Log log = LogFactory.getLog(this.getClass());

    @Resource
    private AccountService accountService;


    @ResponseBody
    @RequestMapping("/login")
    public R login(String account, String pass, String code){
        try {
            // 判断验证码
            String sCode = (String)SessionUtil.get(LOGIN_CODE);
            if(StringUtils.isBlank(code)
                    || StringUtils.isBlank(sCode)
                    || !sCode.equals(code)){
                return new R(0,"验证码不正确");
            }

            Subject subject  = SecurityUtils.getSubject();
            if(subject.isAuthenticated()){
                return new R(1,"登陸成功");
            }

            try {
                UsernamePasswordToken token = new UsernamePasswordToken(account,pass);
                token.setRememberMe(true);
                subject.login(token);
                return new R(1,"登陸成功");
            } catch (UnknownAccountException | IncorrectCredentialsException e1){
                log.error("账号密码不存在 e1," + e1.getMessage());
                e1.printStackTrace();
            } catch (AuthenticationException | AuthorizationException e2){
                log.error("权限认证失败 e2," + e2.getMessage());
                e2.printStackTrace();
            } catch (Exception e3){
                log.error("登陸失败 e3," + e3.getMessage());
                e3.printStackTrace();
            }

            return new R(0,"登陸失败");
        } catch (Exception e){
            log.error("LoginController, login, account=" + account + ", pass=" + pass + ",  e=" , e);
            e.printStackTrace();
            return new R(0,e.getMessage(),false);
        }
    }

    @ResponseBody
    @RequestMapping("/accountAuthz")
    public R accountAuthz (){
        try{
            Account account = SessionUtil.getAcct();
            List<Authz> authzList = SessionUtil.getAuthz();
            if(CollectionUtils.isEmpty(authzList)){
                authzList = accountService.accountAuthz(account.getId());
                SessionUtil.setAuthz(authzList);
            }
            return new R(1,authzList,true);
        }catch (Exception e){
            log.error("LoginController, accountAuthz, e:",e);
            return new R(0,e.getMessage());
        }
    }

    /**
     * 验证码
     * @param request
     * @param response
     */
    @RequestMapping("/code")
    @LPerm("aaa")
    public void code(HttpServletRequest request, HttpServletResponse response){
        try {
            BufferedImage bi = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.getGraphics();
            Color c = new Color(200, 150, 255);
            g.setColor(c);
            g.fillRect(0, 0, 68, 22);

            char[] op = "+-".toCharArray();
            Random r = new Random();
            int index, len1 = op.length;
            int result = 0, firstNum = 0, secondNum = 0;
            char operation = '0';
            for (int i = 0; i < 4; i++) {
                if (i != 1) index = r.nextInt(100);
                else index = r.nextInt(len1);

                g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
                if (i == 0) {
                    g.drawString(index+"", (i*15)+3, 18);
                    firstNum = index;
                } else if (i == 2) {
                    g.drawString(index+"", (i*15)+3, 18);
                    secondNum = index;
                } else if (i == 1) {
                    g.drawString(op[index]+"", (i*15)+3, 18);
                    operation = op[index];
                }  else {
                    g.drawString("=", (i*15)+3, 18);
                }
            }

            if (operation == '+') result = firstNum+secondNum;
            else if (operation == '-') result = firstNum-secondNum;
            else if (operation == '*') result = firstNum*secondNum;
            SessionUtil.set(LOGIN_CODE,result + "");
            ImageIO.write(bi, "JPG", response.getOutputStream());
        } catch(IOException ioe){
            log.error("LoginController, code, ioe,",ioe);
        }

    }
}