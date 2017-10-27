package cn.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.pojo.ActiveUser;
import cn.pojo.SysPermission;
import cn.pojo.SysRole;
import cn.ssm.service.SysService;
import cn.ssm.service.impl.SysServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import test.Customer;

/**
 * Created by 28029 on 2017/10/23.
 */
public class PasswordRealml extends AuthorizingRealm{
    private static transient  Logger log = LoggerFactory.getLogger(PasswordRealml.class);

    @Autowired
    SysService sysService ;
    /**
     * 给予授权信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     * Authorization：授权
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("——————————PasswrodRealml:doGetAuthorizationInfo方法被调用——————————————");

        //登录成功后，验证消息变成了这个，自己加的
        ActiveUser activeUser = (ActiveUser) getAvailablePrincipal(principalCollection);

        //Authentication：身份认证/登录，验证用户是不是拥有相应的身份；
        SimpleAuthenticationInfo a;

        //Authorization：授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用户是否能做事情；
        // 常见的如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用户对某个资源是否具有某个权限；
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();


        //这里可以通过数据库获取角色和权限信息
        //权限设置
        //根据身份信息获取权限信息,
        //从数据库中获取到的动态权限数据

        List<SysPermission> permissionList=null;
        try {
            permissionList=sysService.findPermissionListByUserId(activeUser.getUserid());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("在授权时抛出异常");
        }
        Set<String> permissions=new HashSet<>();
        if (permissionList!=null)
        {
            for (SysPermission sysPermission:permissionList)
            {
                //将数据库中的权限标签符放入集合
                permissions.add(sysPermission.getPercode());
            }
        }

        //角色授权
        SysRole role = null;
        try {
             role = sysService.findRoleByUserCode(activeUser.getUsercode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置权限有两种方式，一种是String的方式，另外一种是Object的方式
        authorizationInfo.setStringPermissions(permissions);
        Set<String> roles = new HashSet<String>();
        if(null != role)
            roles.add(role.getName());
        authorizationInfo.setRoles(roles);

        return authorizationInfo;
    }

    /**
     * 在这个方法中，进行身份验证，
     *Authentication：证明，验证
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //用户名
        String name = (String) token.getPrincipal();
        log.info("————————获取授权信息————————————");
        log.info("username:"+name);

        //Password
        String password = new String((char[])token.getCredentials());
        log.info("password:"+password);
        ActiveUser activeUser =  null;

        //这里和数据库的用户和密码进行比对
        try{
            activeUser = sysService.authenticat(name, password);
        }catch (Exception e){
            e.printStackTrace();
            log.info("user:"+name+"——"+e.getMessage());
        }
        if(activeUser == null)
        {
            log.info("user:["+name+"]没有通过Realm验证");
            return null;
        }
        //身份验证通过，返回一个身份消息
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(activeUser, password,getName());
        log.info("user:["+name+"]通过Realm验证");
        return aInfo;
    }
}
