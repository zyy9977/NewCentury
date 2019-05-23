package com.nc.study.wx.pojo.message;

/**
 * @author zhangyangyang
 * @date 2018/10/9 13:29
 */
public class ImageMessage extends BaseMessage {

    private String PicUrl;
    private String MediaId;
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
