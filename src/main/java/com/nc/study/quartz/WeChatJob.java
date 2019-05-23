package com.nc.study.quartz;

import com.nc.study.wx.service.HandleAccessTokenService;
import com.nc.study.wx.service.HandleMenuService;
import org.springframework.stereotype.Component;

/**
 * @author zhangyangyang
 * @date 2018/10/10 9:29
 */
@Component
public class WeChatJob {

    public void handleAccessToken(){
        HandleAccessTokenService.getAccessToken();
    }

    public void handleCreateMenu(){
        HandleMenuService.createMenu();
    }
}
