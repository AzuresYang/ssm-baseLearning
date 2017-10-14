package controller;

import cn.ssm.controller.SpitController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by 28029 on 2017/10/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)//此处调用Spring单元测试类
@WebAppConfiguration  //调用javaWEB的组件，比如自动注入
@ContextConfiguration(locations = {"file:/applicationContext.xml"})//加载spring容器
public class SpitControllerTest {

    private SpitController spitController;
    @Autowired
    private WebApplicationContext wac;
    //Spring提供的测试类
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void showRegistrationForm() throws Exception {
        mockMvc.perform(get("/mytest"))//执行一个RequestBuilder请求
                .andExpect(view().name("index2"))//验证返回视图是否是"index2"
                .andDo(print()).andExpect(status().isOk());//打印结果到控制台
    }

}