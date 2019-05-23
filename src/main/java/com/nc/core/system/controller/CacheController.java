package com.nc.core.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.nc.common.model.ReturnModel;
import com.nc.common.model.ReturnStatus;
import com.nc.core.system.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyangyang
 * @date 2019/3/4 17:16
 */
@RestController
@RequestMapping("cache")
public class CacheController {

    @Autowired
    private SysDictService sysDictService;

    @RequestMapping(value = "getDictForDist", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String getDictForDist(){
        ReturnModel<Map<String, Object>> returnModel = new ReturnModel();
        Map<String, Object> data = new HashMap<>();
        data.put("list", sysDictService.getDictForDist());
        returnModel.setCode(ReturnStatus.OK.getCode());
        returnModel.setMsg("查询成功");
;
        returnModel.setData(data);
        return JSONArray.toJSONString(returnModel);
    }

    @RequestMapping(value = "getDictByDictItem", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String getDictByDictItem(String dictitem){
        ReturnModel<Map<String, Object>> returnModel = new ReturnModel();
        Map<String, Object> data = new HashMap<>();
        data.put("list", sysDictService.getDictByDictItem(dictitem));
        returnModel.setCode(ReturnStatus.OK.getCode());
        returnModel.setMsg("查询成功");
        ;
        returnModel.setData(data);
        return JSONArray.toJSONString(returnModel);
    }
}
