package com.nc.study.quartz;

import com.nc.core.system.pojo.Shares;
import com.nc.core.system.service.SharesService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyangyang
 * @date 2018/12/24 18:16
 */
// @Component
public class RabbitJob {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private SharesService sharesService;

    public void sendDataToCrQueue() {
        // List<Shares> sharesList = sharesService.sharesService();
        List<Shares> sharesList = new ArrayList<>();
        amqpTemplate.convertAndSend("queueTestKey", sharesList);
    }
}
