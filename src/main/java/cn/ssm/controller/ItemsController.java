package cn.ssm.controller;

import cn.pojo.Items;
import cn.pojo.ItemsExample;
import cn.ssm.service.ItemsService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.junit.runners.Parameterized;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by 28029 on 2017/10/16.
 */
@Controller
public class ItemsController
{
    private static ItemsService itemService = new ItemsService();
    @RequestMapping("/item/edit")
    @RequiresRoles(value={"商品管理员"})
    public String editItem(@RequestParam(required = true) int id, Model model){
        model.addAttribute("item",itemService.selectItemById(id));
        return "edititem";
    }

    @RequestMapping("/item/edit.action")
    @RequiresRoles(value={"商品管理员"})
    public String updateItemsAction(Items item, Model model){

        String message;
        if( 0 > itemService.updateItems(item))
            message="edite "+item.getName()+" success";
        else
            message="edite "+item.getName()+" false";
        model.addAttribute("message",message);

        return "redirect:/item/list";
    }

    @RequestMapping("/item/list")
    @RequiresRoles(value={"商品管理员","用户管理员"})
    public String listItem(@RequestParam(required = false) String isQuery, Model model,RedirectAttributes attr){

        Map<String, Object> map = (Map<String, Object>) attr.getFlashAttributes();
        if(!model.containsAttribute("selectItem") && null == map.get("selectItem")){
            model.addAttribute("selectItem", new Items());
        }

        System.out.println("mapSize:"+map.size()+"keysetSize:"+map.keySet().size());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
            System.out.println("isQuery:"+isQuery);
        System.out.println("isQuery in attr:"+ map.get(isQuery));
        System.out.println("item in attr:"+ map.get("selectItem"));
        if( isQuery==null || !isQuery.trim().equals("yes"))
        {
            model.addAttribute("itemsList", itemService.selectAll());
            System.out.println("列出所有商品");

        }
        else
        {
            model.addAttribute("itemList",map.get("itemList"));
        }

        System.out.println("itemlist 列出：");
        return "itemlist";
    }

    @RequestMapping("/item/query.action")
    @RequiresRoles(value={"商品管理员","用户管理员"})
    //在jsp中绑定该对象即可
    public String queryItemAction( Items selectItem,RedirectAttributes attr){

        if(selectItem != null){
            List<Items> itemsList = itemService.selectByItem(selectItem);
            attr.addFlashAttribute("itemsList", itemsList);
            attr.addFlashAttribute("selectItem", selectItem);
            attr.addFlashAttribute("isQuery", "yes");
            attr.addAttribute("isQuery", "yes");
        }
        return "redirect:/item/list";
    }

}
