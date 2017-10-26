package cn.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by 28029 on 2017/10/23.
 */
public class UserRealm extends AuthorizingRealm {
    private String salt="yang";
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken tokens) throws AuthenticationException {

        String username = (String) tokens.getPrincipal();

        //如果数据库中没有这个账号
        if(!username.equals("ouyang"))
            return null;

        //从数据库中查询到密码
        String password = "231233213123131";


        //以下使用密文加密
        SimpleAuthenticationInfo info =
                new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(salt), getName());
        return info;
    }
}
