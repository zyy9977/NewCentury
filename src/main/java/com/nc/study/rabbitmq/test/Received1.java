package com.nc.study.rabbitmq.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangyangyang
 * @date 2018/11/29 14:21
 */
public class Received1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("zyy");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare(RabbitName.EXCHANGE_NAME.getDesc(), "fanout");

        // 产生一个随机队列名
        String queueName = channel.queueDeclare().getQueue();
        // 队列绑定通道
        channel.queueBind(queueName, RabbitName.EXCHANGE_NAME.getDesc(), "");
        System.out.println("Received1 Waiting for messages");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("ReceiveLogs1 Received '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
