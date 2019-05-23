package com.nc.common.websocket;

import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangyangyang
 * @date 2018/12/24 14:20
 * java方式实现websocket
 */
@ServerEndpoint(value ="/newwebsocket/{username}")
public class Webcomment {
    private Session session;

    private static final Logger logger = Logger.getLogger(Webcomment.class);

    //线程安全的Map
    public static ConcurrentHashMap<String,Session> webSocketMap = new ConcurrentHashMap<>();

    public static CopyOnWriteArraySet<Webcomment> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session,@PathParam("username")String  username) throws IOException, TimeoutException, InterruptedException {
        this.session = session;
        webSocketSet.add(this);
        webSocketMap.put(username, session);
        logger.debug(username + "连接成功");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session){
        Map<String, String> map = session.getPathParameters();
        webSocketMap.remove(map.get("username"));
        logger.debug(map.get("username") + "断开连接");
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        //获取用户ID
        Map<String, String> map = session.getPathParameters();
        String username = map.get("username");

        logger.debug("来自客户端的消息"+ username +":" + message);

    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        logger.debug("websocket发生错误");
        error.printStackTrace();
    }

    /**
     * 向所有连接用户发送消息
     * @param message
     * @throws IOException
     */
    public void sendMessageAll(String message) throws IOException{
        if(this.session.isOpen()){
            this.session.getAsyncRemote().sendText(message);
        }
    }

    /**
     * 向单个用户发送消息
     * @param message
     * @throws IOException
     */
    public void sendMessageTo(String message, String to) throws IOException{
        Map<String, String> map = session.getPathParameters();
        if(this.session.isOpen() && map.get("username").equals(to)){
            this.session.getAsyncRemote().sendText(message);
        }
    }
}