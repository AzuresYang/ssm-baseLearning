package cn.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
/**
 * Created by 28029 on 2017/10/8.
 */
@Service("HelloService")
public class HelloService {
//    @Autowired
//    @Qualifier("helloSpring")
     private HelloWorld helloWorld;

     public HelloService(){

     }
    public HelloWorld getHello(){
        return helloWorld;
    }
    public void setHello(HelloWorld hello){
        this.helloWorld = hello;
    }
}
