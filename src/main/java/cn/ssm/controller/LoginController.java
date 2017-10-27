package cn.ssm.controller;

import cn.pojo.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by 28029 on 2017/10/24.
 */
@Controller
public class LoginController {

    @RequestMapping("login")
    public String login(){

        return "login";
    }



    @RequestMapping("login.action")
    public String loginAction(@RequestParam String username, @RequestParam String password, Model model){
        String message=null;
        String turnUrl ="login";
        System.out.println("验证登录用户："+username);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        Subject subject = SecurityUtils.getSubject();
        subject.isRemembered();
        try{
            subject.login(token);
            if (subject.isAuthenticated()) {
                message = "登录成功";
                turnUrl= "redirect:welcome";
            } else {
               turnUrl =  "login";
               message = "登录失败";
            }
        } catch (IncorrectCredentialsException e) {
            message = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
            System.out.println(message);
        } catch (ExcessiveAttemptsException e) {
            message = "登录失败次数过多";
            System.out.println(message);
        } catch (LockedAccountException e) {
            message = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
            System.out.println(message);
        } catch (DisabledAccountException e) {
            message = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
            System.out.println(message);
        } catch (ExpiredCredentialsException e) {
            message = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
            System.out.println(message);
        } catch (UnknownAccountException e) {
            message = "帐号不存在. There is no user with username of " + token.getPrincipal();
            System.out.println(message);
        } catch (UnauthorizedException e) {
            message = "您没有得到相应的授权！" + e.getMessage();
            System.out.println(message);
        }finally {
            System.out.println(message);
            model.addAttribute("message", message);
        }
        System.out.println("跳转到："+turnUrl);
        return turnUrl;
    }

    //shiro中已经配置了使用logout进行登出活动
    @RequestMapping("logout.action")
    @RequiresRoles(value="**")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        String username = "guest";
        if(subject.isAuthenticated())
            username=((ActiveUser)subject.getPrincipal()).getUsername();
        System.out.println("用户登出："+username);
        return "login";
    }

    @RequestMapping("welcome")
    @RequiresRoles(value={"商品管理员","用户管理员"})
    public String welcome(Model model){
        Subject subject = SecurityUtils.getSubject();
        ActiveUser currentUser = (ActiveUser) subject.getPrincipal();
        model.addAttribute("activeUser", currentUser);
        if(currentUser != null)
            System.out.println("welcome:"+currentUser.getUsername()+"登录");
        return "welcome";
    }

    @RequestMapping("menu")
    @RequiresRoles(value={"商品管理员","用户管理员"})
    public String menu(Model model){
        Subject subject = SecurityUtils.getSubject();
        ActiveUser currentUser = (ActiveUser) subject.getPrincipal();
        model.addAttribute("activeUser", currentUser);
        return "menu";
    }
    @RequestMapping("error")
    public String gotoEerro(){
        return "error";
    }
}
