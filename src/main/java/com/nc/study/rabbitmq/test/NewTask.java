package com.nc.study.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangyangyang
 * @date 2018/11/28 17:35
 */
public class NewTask {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("106.12.204.43");
        connectionFactory.setUsername("zyy");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitName.RABBIT.getDesc(), true, false, false, null);
        for (int i = 0; i < 50; i++) {
            String message = "zyy RabbitMQ -->" + i;
            channel.basicPublish("", RabbitName.RABBIT.getDesc(), MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println("NewTask send '"+message+"'");
        }

        channel.close();
        connection.close();
    }
}
