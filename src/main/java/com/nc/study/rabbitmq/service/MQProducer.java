package com.nc.study.rabbitmq.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangyangyang
 * @date 2019/3/8 13:43
 */
@Component
public class MQProducer {

    private static final Logger logger = LogManager.getLogger(MQProducer.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDataToQueue(String exChangeName, String queueKey, Object object) {
        try {
            amqpTemplate.convertAndSend(exChangeName, queueKey, object);
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
