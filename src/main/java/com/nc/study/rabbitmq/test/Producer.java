package com.nc.study.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangyangyang
 * @date 2018/11/16 9:47
 */
public class Producer {
    public static final String QUEUE_NAME = "rabbitMQ.test";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置rabbitMQ属性信息
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("zyy");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);
        // 创建连接
        Connection connection = connectionFactory.newConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 发送消息到队列中
        int i = 0;
        while (i < 100) {
            String message = "这是一条测试rabbitMQ的消息" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println("Producer Send +'" + message + "'");
            i ++;
        }
        // 关闭通道和连接
        channel.close();
        connection.close();

    }
}
