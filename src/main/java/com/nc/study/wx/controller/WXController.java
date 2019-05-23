package com.nc.study.wx.controller;

import com.nc.study.wx.common.AesException;
import com.nc.study.wx.service.HandleMessageService;
import com.nc.study.wx.utils.MessageUtil;
import com.nc.study.wx.utils.WXPublicUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author zhangyangyang
 * @date 2018/10/9 10:30
 */
@RestController
@RequestMapping("/wxpublic")
public class WXController {

    @RequestMapping(value = "/verify_wx_token", method = RequestMethod.GET)
    public String verifyWXToken(String signature,String timestamp,String nonce,String echostr) throws AesException {
        if (WXPublicUtils.verifyUrl(signature, timestamp, nonce)) {
            return echostr;
        }
        return null;
    }

    @RequestMapping(value = "/verify_wx_token", method = RequestMethod.POST)
    public void verifyWXToken(HttpServletRequest request, HttpServletResponse response) throws AesException {

        response.setCharacterEncoding("utf-8");

        //获取客户端发送的数据，将微信请求xml转为map格式，获取所需的参数
        Map<String,String> map = MessageUtil.xmlToMap(request);

        PrintWriter out = null;
        String message = HandleMessageService.handleMessage(map);
        try {
            out = response.getWriter();
            out.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }
}
