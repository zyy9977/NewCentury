package com.nc.study.websocket;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeoutException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import static com.nc.study.rabbitmq.test.Producer.QUEUE_NAME;

/**
 * @author zhangyangyang
 * @date 2018/12/24 14:20
 */
//@ServerEndpoint(value ="/newwebsocket/{userId}")
public class Webcomment {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<Webcomment> webSocketSet = new CopyOnWriteArraySet<Webcomment>();
    //线程安全的Map
    private static ConcurrentHashMap<String,Session> webSocketMap = new ConcurrentHashMap<String,Session>();//建立连接的方法
    @OnOpen
    public void onOpen(Session session,@PathParam("userId")String  userId) throws IOException, TimeoutException, InterruptedException {
        /*获取从/websocket开始的整条链接，用于获取userId？***=***的参数
        String uri = session.getRequestURI().toString();*/
        webSocketMap.put(userId, session);
        addOnlineCount(); //在线数加
        System.out.println(userId+"进入聊天室");
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());

        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置rabbitMQ地址
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("zyy");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);
        // 创建连接
        Connection connection = connectionFactory.newConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明要关注的队列
        channel.queueDeclare(QUEUE_NAME, false,false, false, null);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);
        while (true) {
            //从consumer中获取队列中的消息,nextDelivery是一个阻塞方法,如果队列中无内容,则等待
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody(),"UTF-8");
            for(String user:webSocketMap.keySet()){
                try {
                    sendMessage(userId + ":" + message,webSocketMap.get(user));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session){
        Map<String, String> map = session.getPathParameters();
        webSocketMap.remove(map.get("userId")); //从set中删除
        for(String user:webSocketMap.keySet()){
            System.out.println(user);
        }
        subOnlineCount(); //在线数减
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //获取用户ID
        Map<String, String> map = session.getPathParameters();
        String userId = map.get("userId");
//
//        SharesService sharesService = (SharesService) SpringTool.getBean("sharesServiceImpl");
//        List<Shares> sharesList = sharesService.sharesService();

        for(String user:webSocketMap.keySet()){
            try {
                sendMessage(userId + ":" + message,webSocketMap.get(user));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
    public void sendMessage(String message,Session session) throws IOException{
        if(session.isOpen()){
            session.getAsyncRemote().sendText(message);
        }
        //this.session.getAsyncRemote().sendText(message);
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        Webcomment.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        Webcomment.onlineCount--;
    }
}