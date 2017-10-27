package cn.ssm.mapper;

import cn.pojo.SysPermission;
import cn.pojo.SysRole;
import cn.pojo.SysRolePermission;
import cn.pojo.SysUser;
import cn.ssm.mapper.sqlprovider.SysUserRolePermissionProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 28029 on 2017/10/27.
 */
public interface SysUserRolePermissionMapper {
    @SelectProvider(type= SysUserRolePermissionProvider.class, method = "selectRoleByUserId")
    public SysRole findRoleByUserId(@Param("userid") String userid);

    @SelectProvider(type=SysUserRolePermissionProvider.class, method="selectPermissionByUserId")
    @Results({
            @Result(id=true, property = "id", column = "id"),
            @Result(property = "name",column="name"),
            @Result(property = "type",column = "url"),
            @Result(property = "percode",column = "percode"),
            @Result(property = "parentid",column = "parentid"),
            @Result(property = "parentids",column = "parentids"),
            @Result(property = "sortstring",column = "sortstring"),
            @Result(property = "available", column = "available")
    })
    public List<SysPermission> findPermissionByUserId(@Param("userid") String userid);

    @Select("select * from sys_user where usercode=#{usercode}")
    public SysUser findUserByUserCode(@Param("usercode") String usercode);

    @Select("select * from sys_user")
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="usercode",column="usercode"),
            @Result(property="username",column="username"),
            @Result(property="password", column="password"),
            @Result(property="salt",column = "salt"),
            @Result(property="locked",column="locked")
    })
    //如果使用@ResultMap(value=namespace)，这个值要自己定义在xml中，和namesapce相同
    public List<SysUser> findAllUser();




}
