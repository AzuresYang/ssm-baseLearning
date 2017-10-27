package cn.ssm.mapper;

import cn.pojo.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 28029 on 2017/10/27.
 */
@Repository
public interface SysPermissionMapper {
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
