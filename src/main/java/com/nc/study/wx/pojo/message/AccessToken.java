package com.nc.study.wx.pojo.message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyangyang
 * @date 2018/10/9 19:34
 */
public class AccessToken {

    private static Map<String, String > map = new HashMap<>();

    public static Map<String, String> getMap() {
        return map;
    }

    public static void setMap(Map<String, String> map) {
        AccessToken.map = map;
    }



/*
    //获取到的access_token
    private String access_token;
    //有效时间（两个小时，7200s）
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }*/
}
