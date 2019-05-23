package com.nc.core.system.shiro;

import com.nc.core.system.pojo.Sys_Permission;
import com.nc.core.system.pojo.Sys_Role;
import com.nc.core.system.pojo.Sys_User;
import com.nc.core.system.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhangyangyang
 * @date 2019/1/7 18:35
 */
@Service
public class MyRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;
    /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录时输入的用户名
        String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
        //到数据库查是否有此对象
        Sys_User user = userService.selectUserByNameService(loginName);
        if(user!=null){
            // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 用户的角色集合
            Set<String> roleSet = new HashSet<>();
            List<Sys_Role> roleList = user.getRoleList();
            int roleListLength = user.getRoleList().size();
            for (int i = 0; i < roleListLength; i++) {
                roleSet.add(roleList.get(i).getRole());
            }
            info.setRoles(roleSet);
            //用户的角色对应的所有权限
            Set<String> permissionSet = new HashSet<>();
            List<Sys_Permission> permissionList = userService.getPermissionByUserNameService(loginName);
            int permissionListLength = permissionList.size();
            for (int i = 0; i < permissionListLength; i++) {
                permissionSet.add(permissionList.get(i).getName());
            }
            info.addStringPermissions(permissionSet);
            return info;
        }
        return null;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //查出是否有此用户
        Sys_User user = userService.selectUserByNameService(token.getUsername());
        if(user!=null){
            //若存在，将此用户存放到登录认证info中
            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
        }
        return null;
    }
}
