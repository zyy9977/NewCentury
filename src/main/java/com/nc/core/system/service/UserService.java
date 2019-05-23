package com.nc.core.system.service;

import com.nc.core.system.pojo.Sys_Permission;
import com.nc.core.system.pojo.Sys_User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangyangyang
 * @date 2018/9/18 17:36
 */

@Service
public interface UserService {

    Sys_User selectUserByNameService(String username);

    List<Sys_Permission> getPermissionByUserNameService(String username);
}
