package cn.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 28029 on 2017/10/24.
 */
@Controller
public class LoginController {

    @RequestMapping("login")
    public String login(@RequestParam String shiroLogin){

        return "login";
    }

    @RequestMapping("login.ation")
    public String loginAction(){
        return "forward:/item/list";
    }
}
