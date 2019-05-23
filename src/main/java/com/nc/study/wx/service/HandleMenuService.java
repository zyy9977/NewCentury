package com.nc.study.wx.service;

import com.alibaba.fastjson.JSONObject;
import com.nc.study.wx.pojo.menu.Button;
import com.nc.study.wx.pojo.menu.LevelMenu;
import com.nc.study.wx.pojo.menu.Menu;
import com.nc.study.wx.pojo.menu.SubMenuButton;
import com.nc.study.wx.pojo.message.AccessToken;
import com.nc.study.wx.utils.HttpClientUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author zhangyangyang
 * @date 2018/10/9 19:35
 */
public class HandleMenuService {

    private static final Logger logger = LogManager.getLogger(HandleMenuService.class);

    public static void createMenu() {
        // 访问微信服务器
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + AccessToken.getMap().get("access_token");
        JSONObject jsonObject = JSONObject.parseObject(getMenu().toString());
        JSONObject response = HttpClientUtils.doPost(url, jsonObject);

        logger.info(response);

    }

    public static Menu getMenu () {
        Menu menu = new Menu();
        // 建3个导航菜单
        LevelMenu tLevelMenuOne = new LevelMenu();
        tLevelMenuOne.setName("首页");
        tLevelMenuOne.setType("click");
        tLevelMenuOne.setKey("11");
        LevelMenu tLevelMenuTwo = new LevelMenu();
        tLevelMenuTwo.setName("知识百科");
        tLevelMenuTwo.setType("click");
        tLevelMenuTwo.setKey("12");
        LevelMenu tLevelMenuThree = new LevelMenu();
        tLevelMenuThree.setName("导航菜单");
        tLevelMenuThree.setType("click");
        tLevelMenuThree.setKey("13");

        // 第一个导航菜单的子菜单
        SubMenuButton tSubMenuButton_oneone = new SubMenuButton();
        tSubMenuButton_oneone.setType("pic_sysphoto");
        tSubMenuButton_oneone.setName("系统拍照");
        tSubMenuButton_oneone.setKey("21");

        SubMenuButton tSubMenuButton_onetwo = new SubMenuButton();
        tSubMenuButton_onetwo.setType("view");
        tSubMenuButton_onetwo.setName("科技知识");
        tSubMenuButton_onetwo.setKey("22");
        tSubMenuButton_onetwo.setUrl("http://5f5w2n.natappfree.cc/page/newCentury");

        // 加入导航菜单
        tLevelMenuOne.setSub_button(new SubMenuButton[]{ tSubMenuButton_oneone, tSubMenuButton_onetwo });

        menu.setButton(new Button[]{ tLevelMenuOne,tLevelMenuTwo,tLevelMenuThree});
        return menu;
    }
}
