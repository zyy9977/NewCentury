package com.nc.study.wx.pojo.menu;

/**
 * @author zhangyangyang
 * @date 2018/10/10 11:32
 */
public class SubMenuButton extends Button {

    private String url = "";

    private String media_id = "";

    private String appid = "";

    private String pagepath = "";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }
}
