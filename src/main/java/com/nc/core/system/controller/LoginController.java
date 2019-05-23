package com.nc.core.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.nc.common.model.ReturnModel;
import com.nc.common.model.ReturnStatus;
import com.nc.core.system.pojo.Sys_User;
import com.nc.core.system.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyangyang
 * @date 2018/12/27 13:35
 */
@RestController
@RequestMapping("/_login")
public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/_login", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String login(String userName, String password) {
        logger.info("'" + userName + "'正在登陆");
        Sys_User user = userService.selectUserByNameService(userName);
        ReturnModel<Map<String, Object>> returnModel = new ReturnModel();
        Map<String, Object> returnData = new HashMap();
        returnModel.setCode(ReturnStatus.OK.getCode());
        returnModel.setData(returnData);
        if (user == null) {
            returnData.put("status", "0");
            returnData.put("result", "用户不存在，请重新输入");
            returnModel.setMsg("登录失败");
            return JSONArray.toJSONString(returnModel);
        } else if(!user.getPassword().equals(password)) {
            returnModel.setMsg("登录失败");
            returnData.put("status", "0");
            returnData.put("result", "密码错误，请重新输入");
            return JSONArray.toJSONString(returnModel);
        }
        //使用权限工具进行用户登录，登录成功后，如果shiro配置successUrl，则跳转到successUrl，否则跳转到下方return路径
        SecurityUtils.getSubject().login(new UsernamePasswordToken(userName, password));
        returnModel.setMsg("登陆成功");
        returnData.put("status", "1");
        returnData.put("result", "登陆成功");
        return JSONArray.toJSONString(returnModel);

    }

    /**
     * 根据名称获取用户及其角色和菜单
     * @param token userName 用户名
     * @return
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public String getUserInfo(String token) {
        Sys_User sysUser = userService.selectUserByNameService(token);
        return JSONArray.toJSONString(sysUser);
    }

    /**
     * 退出登录
     * @param token 登录名
     * @return
     */
    @RequestMapping(value="/_logout",method=RequestMethod.POST,  produces="application/json;charset=UTF-8")
    public String logout(String token){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        logger.info("'" + token + "'退出登录");
        ReturnModel<String> returnModel = new ReturnModel();
        returnModel.setCode(ReturnStatus.OK.getCode());
        returnModel.setMsg("退出登录成功");
        return JSONArray.toJSONString(returnModel);
    }

    /**
     * session超时请求此接口
     * @return
     */
    @RequestMapping(value="/noLogged",method=RequestMethod.GET,  produces="application/json;charset=UTF-8")
    public String noLogged () {
        ReturnModel<String> returnModel = new ReturnModel();
        returnModel.setCode(ReturnStatus.LOGIN_FAILED.getCode());
        returnModel.setMsg("未登录");
        return JSONArray.toJSONString(returnModel);
    }

    /**
     * 没有权限跳转到此接口
     * @return
     */
    @RequestMapping(value="/noPermission",method=RequestMethod.GET,  produces="application/json;charset=UTF-8")
    public String noPermission () {
        ReturnModel<String> returnModel = new ReturnModel();
        returnModel.setCode(ReturnStatus.NO_PERMISSION.getCode());
        returnModel.setMsg("该用户权限不足");
        return JSONArray.toJSONString(returnModel);
    }
}
