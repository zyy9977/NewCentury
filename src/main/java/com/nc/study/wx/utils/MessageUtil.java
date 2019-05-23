package com.nc.study.wx.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nc.study.wx.pojo.message.Article;
import com.nc.study.wx.pojo.message.ImageMessage;
import com.nc.study.wx.pojo.message.NewsMessage;
import com.nc.study.wx.pojo.message.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangyangyang
 * @date 2018/10/9 13:32
 */
public class MessageUtil {

    private static XStream xstream  = new XStream();
    /**
     * 将微信的请求中参数转成map
     * @param request
     * @return
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream in = null;
        try {
            in = request.getInputStream();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for (Element element : list) {
                map.put(element.getName(), element.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally{
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 图文消息转换xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 图文消息转换xml
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }

    /**
     * 图片消息转换xml
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

//    /**
//     * 语音消息转换xml
//     * @param voiceMessage
//     * @return
//     */
//    public static String voiceMessageToXml(VoiceMessage voiceMessage) {
//        xstream.alias("xml", voiceMessage.getClass());
//        return xstream.toXML(voiceMessage);
//    }
//
//    /**
//     * 视频消息转换xml
//     * @param videoMessage
//     * @return
//     */
//    public static String videoMessageToXml(VideoMessage videoMessage) {
//        xstream.alias("xml", videoMessage.getClass());
//        return xstream.toXML(videoMessage);
//    }
//
//    /**
//     * 音乐消息转换xml
//     * @param musicMessage
//     * @return
//     */
//    public static String musicMessageToXml(MusicMessage musicMessage) {
//        xstream.alias("xml", musicMessage.getClass());
//        return xstream.toXML(musicMessage);
//    }
}
