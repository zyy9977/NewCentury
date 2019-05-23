package com.nc.core.system.service.serviceImpl;

import com.nc.core.system.dao.UserDao;
import com.nc.core.system.pojo.Sys_Permission;
import com.nc.core.system.pojo.Sys_User;
import com.nc.core.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangyangyang
 * @date 2018/9/18 17:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Sys_User selectUserByNameService(String username) {
        return userDao.selectUserByName(username);
    }

    @Override
    public List<Sys_Permission> getPermissionByUserNameService(String username) {
        return userDao.getPermissionByUserName(username);
    }
}
