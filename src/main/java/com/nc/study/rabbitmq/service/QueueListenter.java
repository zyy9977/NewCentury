package com.nc.study.rabbitmq.service;

import com.nc.common.websocket.Webcomment;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangyangyang
 * @date 2019/3/8 13:58
 */
@Component
public class QueueListenter implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try{
            for (Webcomment webcomment: Webcomment.webSocketSet) {
                webcomment.sendMessageAll(new String(message.getBody(), "UTF-8"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
