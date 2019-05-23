package com.nc.study.wx.pojo.menu;


/**
 * @author zhangyangyang
 * @date 2018/10/10 11:20
 */
public class Menu {
    /**
     * 定义名称与json中一致，不然解析名称对不上
     */
    private Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }
}
