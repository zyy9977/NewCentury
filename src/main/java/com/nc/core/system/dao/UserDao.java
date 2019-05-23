package com.nc.core.system.dao;

import com.nc.core.system.pojo.Sys_Permission;
import com.nc.core.system.pojo.Sys_User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangyangyang
 * @date 2018/9/18 16:54
 */
@Repository
public interface UserDao {

    Sys_User selectUserByName(String username);

    List<Sys_Permission> getPermissionByUserName(String username);

}
