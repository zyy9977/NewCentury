package com.nc.study.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangyangyang
 * @date 2018/11/29 14:13
 * 发布/订阅（广播模式）
 * 所有订阅了的消费者都会接收到消息
 */
public class EmitLog {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("zyy");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(RabbitName.EXCHANGE_NAME.getDesc(), "fanout"); // fanout表示分发，所有的消费者得到同样的队列信息

        for (int i = 0; i < 50; i++) {
            String message = "Hello ZhangYY" + i;
            channel.basicPublish(RabbitName.EXCHANGE_NAME.getDesc(), "", null, message.getBytes("UTF-8"));
            System.out.println("EmitLog Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}
