package cn.ssm.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Created by 28029 on 2017/10/10.
 */
@Controller
//@RequestMapping("/spitter")
public class SpitController {
    // 处理来自/spitter/register的get请求
    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "registerForm";
    }
}
