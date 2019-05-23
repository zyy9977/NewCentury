package com.nc.core.problemmanage.controller;

import com.alibaba.fastjson.JSONArray;
import com.nc.common.exception.CustomException;
import com.nc.common.model.ReturnModel;
import com.nc.common.model.ReturnStatus;
import com.nc.common.rabbitmq.message.RabbitUtil;
import com.nc.common.websocket.Webcomment;
import com.nc.core.problemmanage.pojo.ModuleInfo;
import com.nc.core.problemmanage.service.ModuleInfoService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyangyang
 * @date 2019/2/28 11:22
 */
@RestController
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    private ModuleInfoService moduleInfoService;

//    @Autowired
//    private MQProducer mqProducer;

    @Autowired
    private RabbitUtil rabbitUtil;

    @RequestMapping(value = "/selectModuleInfoList", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String selectModuleInfoList(int page, int pageSize) {
        ReturnModel<Map<String, Object>> returnModel = new ReturnModel();
        Map<String, Object> data = new HashMap<>();
        data.put("total", moduleInfoService.selectModuleInfoTotal());
        data.put("list", moduleInfoService.getModuleInfoListDao(page, pageSize));
        returnModel.setData(data);
        returnModel.setCode(ReturnStatus.OK.getCode());
        returnModel.setMsg("查询模块信息成功");
        return JSONArray.toJSONString(returnModel);
    }

    @RequestMapping(value = "/oprModuleInfo", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String oprModuleInfo(ModuleInfo moduleInfo, String action) throws CustomException {
        ReturnModel<Map<String, Object>> returnModel = new ReturnModel();
        Map<String, Object> data = new HashMap<>();
        moduleInfoService.oprModuleInfoService(moduleInfo, action);
        returnModel.setData(data);
        returnModel.setCode(ReturnStatus.OK.getCode());
        returnModel.setMsg("模块信息"+ modelMsg(action) +"成功");
        return JSONArray.toJSONString(returnModel);
    }

    private String modelMsg (String action) {
        String msg = "";
        switch (action) {
            case "A":
                msg = "新增";
                break;
            case "U":
                msg = "修改";
                break;
            case "D":
                msg = "删除";
                break;
        }
        return msg;
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String sendMessage() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //mqProducer.sendDataToQueue("topicExchange","level.aaa.log", "这是一条测试websocket的消息" + simpleDateFormat.format(new Date()));

        Queue queue = new Queue("zyy_01", true);
        rabbitUtil.addQueue(queue);
        rabbitUtil.sendToQueue("zyy_01", "这是一条测试websocket的消息" + simpleDateFormat.format(new Date()));
        rabbitUtil.mqBindListener("zyy_01");

        ReturnModel<Map<String, Object>> returnModel = new ReturnModel();
        returnModel.setCode(ReturnStatus.OK.getCode());
        returnModel.setMsg("创建队列成功");
        return JSONArray.toJSONString(returnModel);
    }

    @RequestMapping(value = "/receiveMessage", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String receiveMessage() throws IOException {
        Message message = rabbitUtil.receive("zyy_01");
        for (Webcomment webcomment: Webcomment.webSocketSet) {
            webcomment.sendMessageAll(new String(message.getBody(), "UTF-8"));
        }

        ReturnModel<Map<String, Object>> returnModel = new ReturnModel();
        returnModel.setCode(ReturnStatus.OK.getCode());
        returnModel.setMsg("收取成功！");
        return JSONArray.toJSONString(returnModel);
    }
}
