package test;

import cn.pojo.SysPermission;
import cn.pojo.SysRole;
import cn.pojo.SysUser;
import cn.ssm.mapper.SysPermissionMapper;
import cn.ssm.mapper.SysUserRolePermissionMapper;
import cn.ssm.mapper.sqlprovider.SysUserRolePermissionProvider;
import org.apache.ibatis.session.SqlSession;
import test.mapper.CustomerMapper;
import cn.ssm.DBTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 28029 on 2017/10/13.
 */
public class CustomerService {
    public static void main(String[] args) {
//        for(Customer item:selectAllCustomer()){
//            System.out.println(item.toString());
//        }
        CustomerService ser = new CustomerService();
        SysUser user = ser.getUserByUserCode("lisi");
        if(user == null)
            System.out.println("user 为空");
        else
            System.out.println("user :"+user.getUsername());

        List<SysUser> users = new ArrayList<>();
        users = ser.findAllUser();
        for(SysUser u : users){
            System.out.println("user:"+u.getUsername());
        }
//        SysUserRolePermissionProvider pro = new SysUserRolePermissionProvider();
//        HashMap<String,Object> map  =new  HashMap<>();
//        map.put("userid","lisi");
//        System.out.println(pro.selectPermissionByUserId(map));
        List<SysPermission> pers = ser.getPermissionByUserId("lisi");
        System.out.println("pers num:"+ pers.size());

    }
    private static List<Customer> selectAllCustomer(){
        SqlSession session = DBTools.getSession();
        CustomerMapper mapper = session.getMapper(CustomerMapper.class);
        try {
            List<Customer> user=mapper.selectAllCustomer();
            System.out.println("选择所有用户成功");
            session.commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }finally{
            session.close();
        }
        return null;
    }

    public SysUser getUserByUserCode(String usercode){
        SqlSession session = DBTools.getSession();
        SysPermissionMapper pm = session.getMapper(SysPermissionMapper.class);
       SysUser user = pm.findUserByUserCode(usercode);
       return user;
    }

    public List<SysUser> findAllUser(){
        SqlSession session = DBTools.getSession();
        SysPermissionMapper pm = session.getMapper(SysPermissionMapper.class);
        List<SysUser> list =  pm.findAllUser();
        if(list == null ){
            System.out.println("list为空");
            return null;
        }
        else{
            System.out.println("list数量："+list.size());
        }
        return list;
    }

    public SysRole getRoleByUserId(String userId){
        SqlSession session = DBTools.getSession();
        SysUserRolePermissionMapper pm = session.getMapper(SysUserRolePermissionMapper.class);
        return pm.findRoleByUserId(userId);
    }

    public List<SysPermission> getPermissionByUserId(String userId){
        SqlSession session = DBTools.getSession();
        SysUserRolePermissionMapper pm = session.getMapper(SysUserRolePermissionMapper.class);
        return pm.findPermissionByUserId(userId);
    }
}
