package cn.pojo;

import java.util.List;

/**
 * Created by 28029 on 2017/10/26.
 * 已经登录了的用户信息
 * 存入session 由于tomcat将session会序列化在本地硬盘上，所以使用Serializable接口
 */

public class ActiveUser implements java.io.Serializable{
    private String userid;//用户id(Primary key)
    private String usercode;//用户账号
    private String username; //用户名

    private List<SysPermission> menus;// 菜单
    private List<SysPermission> permissions;// 权限

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<SysPermission> getMenus() {
        return menus;
    }

    public void setMenus(List<SysPermission> menus) {
        this.menus = menus;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }
}
