package com.nc.study.rabbitmq.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.QueueingConsumer.Delivery;

/**
 * @author zhangyangyang
 * @date 2018/11/16 10:13
 */
public class Customer {
    private final static String QUEUE_NAME = "rabbitMQ.test";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 创建工厂
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
            Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("接收到了" + QUEUE_NAME + "中的消息:" + message);
        }
    }
}
