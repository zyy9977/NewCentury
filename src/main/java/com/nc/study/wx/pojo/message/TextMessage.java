package com.nc.study.wx.pojo.message;

/**
 * @author zhangyangyang
 * @date 2018/10/9 13:29
 */
public class TextMessage extends BaseMessage {

    private String Content;//文本消息内容

    public TextMessage(){

    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}
