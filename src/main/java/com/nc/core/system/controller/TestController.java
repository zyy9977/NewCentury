package com.nc.core.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.nc.common.model.ReturnModel;
import com.nc.common.model.ReturnStatus;
import com.nc.core.system.pojo.Shares;
import com.nc.core.system.pojo.Sys_User;
import com.nc.core.system.service.SharesService;
import com.nc.core.system.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zhangyangyang
 * @date 2018/9/12 14:04
 */
@RestController
@RequestMapping("/new")
public class TestController {

    private static final Logger logger = LogManager.getLogger(TestController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SharesService sharesService;

    @RequestMapping(value = "newCentury/{name}/{password}", method = RequestMethod.GET, produces="text/html;charset=UTF-8")
    public String newCentury(@PathVariable String name, @PathVariable String password) {
        logger.info("-----------------------------");

        List<Sys_User> users = new ArrayList<>();
        Sys_User user = new Sys_User();
        user.setUid(1);
        user.setUserName(name);
        user.setPassword(password);
        user.setRemark("这是一条测试数据");
        users.add(user);
        return JSONArray.toJSONString(users);
    }

    @RequestMapping(value = "selectShares", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String selectShares (int page, int pageSize) {
        ReturnModel<Map<String, Object>> returnModel = new ReturnModel();
        List<Shares> list = sharesService.sharesService(page, pageSize);
        Map<String, Object> data = new HashMap<>();
        data.put("total", sharesService.selectSharesTotalService());
        data.put("list", list);
        returnModel.setCode(ReturnStatus.OK.getCode());
        returnModel.setMsg("查询成功");
        returnModel.setData(data);
        return JSONArray.toJSONString(returnModel);
    }

    @RequestMapping(value = "addShares", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public String addShares(String shzs, String szzs) {
        ReturnModel<Map<String, Object>> returnModel = new ReturnModel();
        Shares shares = new Shares();
        shares.setShzs(shzs);
        shares.setSzzs(szzs);
        sharesService.addShares(shares);
        returnModel.setCode(ReturnStatus.OK.getCode());
        returnModel.setMsg("增加成功");
        return JSONArray.toJSONString(returnModel);
    }
}
