package test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by 28029 on 2017/10/22.
 */
public class Login {
    private static final transient Logger log = LoggerFactory.getLogger(Login.class);

    //工厂获取配置文件
    private static Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:config/shiro.ini");
    static SecurityManager securityManatger = factory.getInstance();
    private static boolean isInit = false;
    Login(){
        if(!isInit){
            SecurityUtils.setSecurityManager(securityManatger);
            isInit = true;
        }
    }

    public static void main(String[] args) {
            Login lo = new Login();
            lo.testPremission();

    }

    //使用权限认证
    public void testPremission(){
        Subject currentUser = SecurityUtils.getSubject();
        //如果还没有认证
        if(!currentUser.isAuthenticated()){

            //构建一个登陆用户
            UsernamePasswordToken token = new UsernamePasswordToken("Azure","1234");
            token.setRememberMe(true);
            try{
                //登陆身份验证
                currentUser.login(token);
            }catch (UnknownAccountException uae) {
                log.info("没有该用户： " + token.getPrincipal());
                return;
            } catch (IncorrectCredentialsException ice) {
                log.info( token.getPrincipal() + " 的密码不正确!");
                return;
            } catch (LockedAccountException lae) {
                log.info( token.getPrincipal() + " 被锁定 ，请联系管理员");
                return;
            }catch (AuthenticationException ae) {
                //其他未知的异常
                log.info( token.getPrincipal() + " 其它异常 ，请联系管理员");
                return;
            }

            if(currentUser.isAuthenticated())
                log.info("用户："+currentUser.getPrincipal()+"登陆成功");
            else
                log.info("用户："+currentUser.getPrincipal()+"登陆失败");
            if(currentUser.hasRole("role1"))
                log.info("有角色：role1");
            else
                log.info("没有角色：role1");



            if(currentUser.isPermitted("write-all"))
                log.info("拥有权限:write-all");
            else
                log.info("没有权限：write-all");
        }
    }

    public void testLogin(){
            //得到Subject及创建用户名/密码用户验证Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try{
            subject.login(token);
        }catch(AuthenticationException e){
            System.out.println(e.getMessage());
        }

        if(subject.isAuthenticated()){
            System.out.println("已经登录");
        }else{
            System.out.println("未登录");
        }

        subject.logout();
    }
}
