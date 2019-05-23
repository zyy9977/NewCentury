package com.nc.study.wx.service;

import com.alibaba.fastjson.JSONObject;
import com.nc.study.wx.pojo.message.AccessToken;
import com.nc.study.wx.utils.HttpClientUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyangyang
 * @date 2018/10/9 19:35
 */
public class HandleAccessTokenService {

    private static final Logger logger = LogManager.getLogger(HandleAccessTokenService.class);

//    private static final String AppID = "wx64e35a3a93109802";
//    private static final String AppSecret = "1bd7140748c76879fd54607da3594964";

    // 测试账号
    private static final String AppID = "wx1ac6cd870a892300";
    private static final String AppSecret = "b50db4125607a71f5f73936a3acafb17";

    public static AccessToken getAccessToken() {
        AccessToken token = new AccessToken();
        // 访问微信服务器
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + AppID + "&secret=" + AppSecret;

        JSONObject jsonObject = HttpClientUtils.doGet(url);
        Map<String, String> map = new HashMap<>();
        map.put("access_token", jsonObject.getString("access_token"));
        map.put("expires_in", jsonObject.getString("expires_in"));
        AccessToken.setMap(map);

        logger.info(JSONObject.parseObject(JSONObject.toJSONString(HandleMenuService.getMenu())));

        logger.info(jsonObject);
        JSONObject response = HttpClientUtils.doPost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + jsonObject.getString("access_token"), JSONObject.parseObject(JSONObject.toJSONString(HandleMenuService.getMenu())));
        logger.info(response);

        return token;
    }
}
