package com.nc.study.wx.service;

import com.nc.study.wx.pojo.message.*;
import com.nc.study.wx.utils.MessageUtil;
import com.nc.study.wx.utils.MsgType;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyangyang
 * @date 2018/10/9 16:29
 */
public class HandleMessageService {

    /**
     * 处理text类型的数据
     * @param map 客户端发送的数据
     * @return
     */
    public static String handleMessageText(Map<String,String> map) {
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String content = map.get("Content");

        TextMessage text = new TextMessage();
        text.setToUserName(fromUserName);
        text.setFromUserName(toUserName);
        text.setContent("欢迎关注AI新世纪1122" + content);
        text.setCreateTime(new Date().getTime());
        text.setMsgType(MsgType.MESSAGE_TEXT);

       return MessageUtil.textMessageToXml(text);
    }

    /**
     * 处理article类型的数据
     * @param map 客户端发送的数据
     * @return
     */
    public static String handleMessageNews(Map<String,String> map) {
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");

        Article article = new Article();
        article.setTitle("换了人间");
        article.setDescription("点击进入AI新世纪官方网站");
        article.setPicUrl("http://akbh9d.natappfree.cc/image/2.jpg");
        article.setUrl("http://akbh9d.natappfree.cc/new/newCentury/zyy/123123");

        List<Article> articles = new ArrayList<>();
        articles.add(article);

        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setFromUserName(toUserName);
        newsMessage.setToUserName(fromUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MsgType.MESSAGE_NEWS);
        newsMessage.setArticles(articles);
        newsMessage.setArticleCount(articles.size());

        return MessageUtil.newsMessageToXml(newsMessage);
    }

    /**
     * 处理text类型的数据
     * @param map 客户端发送的数据
     * @return
     */
    public static String handleMessageDefalut(Map<String,String> map) {
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");

        TextMessage text = new TextMessage();
        text.setToUserName(fromUserName);
        text.setFromUserName(toUserName);
        text.setContent("您请求的是人工智障服务，不能识别消息!");
        text.setCreateTime(new Date().getTime());
        text.setMsgType(MsgType.MESSAGE_TEXT);

        XStream xstream  = new XStream();
        xstream.alias("xml", text.getClass());
        return xstream.toXML(text);
    }

    public static String handleMessage(Map<String,String> map) {
        // String msgType = map.get("MsgType");
        String content = map.get("Content");
        String message = null;
        switch (content) {
            case "1":
                message = handleMessageText(map);
                break;
            case "2":
                message = handleMessageNews(map);
                break;
            default:
                message = handleMessageDefalut(map);
                break;
        }
        return  message;
    }


}
