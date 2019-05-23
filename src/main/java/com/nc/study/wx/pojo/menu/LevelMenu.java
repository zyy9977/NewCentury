package com.nc.study.wx.pojo.menu;

/**
 * @author zhangyangyang
 * @date 2018/10/10 11:48
 */
public class LevelMenu extends Button{

    /**
     * 包含多个子菜单 定义名称与json中一致，不然解析名称对不上
     */
    private SubMenuButton[] sub_button;

    public SubMenuButton[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(SubMenuButton[] sub_button) {
        this.sub_button = sub_button;
    }
}
