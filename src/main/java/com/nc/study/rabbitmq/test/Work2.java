package com.nc.study.rabbitmq.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangyangyang
 * @date 2018/11/28 17:57
 */
public class Work2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        final ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("106.12.204.43");
        connectionFactory.setUsername("zyy");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(RabbitName.RABBIT.getDesc(), true, false, false, null);
        System.out.println("Worker2  Waiting for messages");

        // 每次冲队列中获取的数量
        channel.basicQos(1);

        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Worker2  Received '" + message + "'");
                try {
                    doWork();
                    channel.abort();
                    System.out.println("Worker2 Done");
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }catch (Exception e){
                }

            }
        };
        channel.basicConsume(RabbitName.RABBIT.getDesc(), true, consumer);
    }

    private static void doWork() {
        try {
            Thread.sleep(1000); // 暂停1秒钟
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    }
}

