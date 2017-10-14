package cn.ssm.controller;

import javax.servlet.http.HttpServletRequest;

import cn.utils.TagsModel;
import cn.utils.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 28029 on 2017/10/9.
 */
@Controller
public class ViewController
{
    //控制这个字段
    @RequestMapping("/views")
    public ModelAndView view(HttpServletRequest request){
        String path = request.getParameter("path") + "";
        ModelAndView mav = new ModelAndView();
        mav.setViewName(path);
        return mav;
    }
    
    //Spring MVC会根据返回的值，进行分析，返回相应的界面，这里之前配置了前辍[views]和后辍[jsp]
    @RequestMapping("/view")
    public String home(){
        return "index";
    }
    @RequestMapping("/vie")
    public String showHomePage(Map<String, Object> model){
        model.put("spittles", "helloSpittles");
        return "testcontroller";
    }

    @RequestMapping("/index")
    public Map<String, String> index(){
        Map<String, String> map = new HashMap<String, String>();

        map.put("attributeName", "attributeNameValue");

        return map;
    }

    @RequestMapping("/form")
    public String formTest(Model model){
        if(!model.containsAttribute("user")){
            User user = new User();
            user.setName("Azure");
            user.setPass("12345");

            Map<Integer, String> map = new HashMap<Integer, String>();
            map.put(1, "路人甲");
            map.put(2,"路人乙");

            model.addAttribute("user", user);
        }
        return "springform";
    }

    @RequestMapping(value="/formTest", method={RequestMethod.GET})
    public String test(Model model){

        if(!model.containsAttribute("contentModel")){

            TagsModel tagsModel=new TagsModel();

            tagsModel.setUsername("aaa");
            tagsModel.setPassword("bbb");
            tagsModel.setTestBoolean(true);
            tagsModel.setSelectArray(new String[] {"arrayItem 路人甲"});
            tagsModel.setTestArray(new String[] {"arrayItem 路人甲","arrayItem 路人乙","arrayItem 路人丙"});
            tagsModel.setRadiobuttonId(1);
            tagsModel.setSelectId(2);
            tagsModel.setSelectIds(Arrays.asList(1,2));
            Map<Integer,String> map=new HashMap<Integer,String>();
            map.put(1, "mapItem 路人甲");
            map.put(2, "mapItem 路人乙");
            map.put(3, "mapItem 路人丙");
            tagsModel.setTestMap(map);
            tagsModel.setRemark("备注...");

            model.addAttribute("contentModel", tagsModel);
        }
        return "springform";
    }
}
