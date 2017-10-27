package cn.ssm.mapper.sqlprovider;

import java.util.Map;
import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;

//mybatis提供SelectBuilder和SqlBuilder这2个小工具来帮助我们生成SQL语句，
// SelectBuilder专门用来生成select语句，而SqlBuilder则是一般性的工具，可以生成任何SQL语句，
// 这里选择了SqlBuilder来生成
/**
 * Created by 28029 on 2017/10/27.
 */
public class SysUserRolePermissionProvider {
    private static final String TABLE_USER="sys_user";
    private static final String TABLE_USER_ROLE = "sys_user_role";
    private static final String TABLE_ROLE_PERMISSION="sys_role_permission";
    private static final String TABLE_ROLE="sys_role";
    private static final String TABLE_PERMISSION="sys_permission";
    //对于只有一个参数的情况，可以直接使用，参见前面的getUser和selectUser。
    //但是，如果在getUser方法中，对userId方法使用了@Param注解的话，那么相应的方法必须接受Map<String, Object>做为参数：
    public String selectRoleByUserId(Map<String, Object> para){
        String userid = (String)para.get("userid");
        BEGIN();//开始
        SELECT("*");
        FROM(TABLE_ROLE);

        WHERE(" id IN   (" +
                "Select id from "+TABLE_USER_ROLE+" where sys_user_id = \'"+userid+"\')");
        return SQL();
    }

    public String selectPermissionByUserId(Map<String, Object> para){
        String userid = (String)para.get("userid");
        BEGIN();
        SELECT("*");
        FROM(TABLE_PERMISSION);
        WHERE("id IN ( Select sys_permission_id from "+ TABLE_ROLE_PERMISSION+
                " where sys_role_id = (Select sys_role_id from "+TABLE_USER_ROLE
                +" where sys_user_id=\'"+userid+"'))");
        return SQL();
    }
}
