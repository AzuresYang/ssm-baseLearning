package cn.utils;

import org.springframework.context.annotation.Bean;

/**
 * Created by 28029 on 2017/10/8.
 */

public class HelloSpring implements HelloWorld{

    public void sayHello(){
        System.out.println("Hello Spring");
    }
}
