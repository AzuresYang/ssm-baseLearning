package cn.ssm.service.impl;

import cn.exception.CustomException;
import cn.pojo.ActiveUser;
import cn.pojo.SysPermission;
import cn.pojo.SysRole;
import cn.pojo.SysUser;
import cn.ssm.DBTools;
import cn.ssm.mapper.SysUserRolePermissionMapper;
import cn.ssm.service.SysService;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Permission;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 28029 on 2017/10/27.
 */
@Service
public class SysServiceImpl implements SysService{
    private static Logger log = LoggerFactory.getLogger(SysServiceImpl.class);
    private  static SqlSession session = DBTools.getSession();
    private static SysUserRolePermissionMapper pm = session.getMapper(SysUserRolePermissionMapper.class);
    @Override
    public ActiveUser authenticat(String userCode, String password) throws Exception {

        if(null == userCode)
            throw  new CustomException("账号不能为空");

        SysUser user=null;

        user = findSysUserByUserCode(userCode);
        if(null == user){
            log.info("账号:["+userCode+"]不存在");
            return null;
        }
        //允许密码为空的情况，数据库中已经设置了密码不能为空的情况，此处无妨
        if(null ==  password){
            if(null != user.getPassword() || !"".equals(user.getPassword().trim())){
                log.info("密码不正确");
                return null;
            }

        }else{
            if( !password.equals(user.getPassword())) {
                log.info("密码不正确");
                return null;
            }
        }
        ActiveUser activeUser = findUserByUserCode(userCode);
        return activeUser;
    }

    @Override
    public ActiveUser findUserByUserCode(String userCode) throws Exception {
        SysUser user= pm.findUserByUserCode(userCode);
        if(null == user){
            return null;
        }
        List<SysPermission> permissions = findPermissionListByUserId(user.getId());

        ActiveUser activeUser = new ActiveUser();

        activeUser.setUsercode(userCode);
        activeUser.setUsername(user.getUsername());
        activeUser.setUserid(user.getId());
        activeUser.setPermissions(permissions);

        return activeUser;

    }

    @Override
    public SysUser findSysUserByUserCode(String userCode) throws Exception {
        return pm.findUserByUserCode(userCode);
    }

    @Override
    public SysRole findRoleByUserCode(String userCode) throws Exception {
        SysUser user = findSysUserByUserCode(userCode);
        if(null == user)
            return null;
        return pm.findRoleByUserId(user.getId());
    }

    @Override
    public List<SysPermission> findMenuListByUserId(String userId) throws Exception {
        return null;
    }
    @Override
    public List<SysPermission> findPermissionListByUserId(String userId) throws Exception {
        return pm.findPermissionByUserId(userId);
    }



}
