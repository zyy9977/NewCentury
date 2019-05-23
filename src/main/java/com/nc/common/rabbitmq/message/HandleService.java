package com.nc.common.rabbitmq.message;

import com.nc.common.websocket.Webcomment;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * @author zhangyangyang
 * @date 2019/3/18 10:36
 */
public class HandleService implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        for (Webcomment webcomment: Webcomment.webSocketSet) {
            webcomment.sendMessageAll(new String(message.getBody(), "UTF-8"));
        }
    }
}
