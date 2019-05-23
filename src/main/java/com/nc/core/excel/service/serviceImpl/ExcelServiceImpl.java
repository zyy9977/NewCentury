package com.nc.core.excel.service.serviceImpl;

import com.nc.common.rabbitmq.message.RabbitUtil;
import com.nc.core.excel.dao.ExcelDao;
import com.nc.core.excel.service.ExcelService;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhangyangyang
 * @date 2018/9/18 17:39
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ExcelDao excelDao;
    @Autowired
    private RabbitUtil rabbitUtil;


    @Override
    public void insertObjectListService(List list, String tableName, String userName) throws Exception {
        String queueName = userName + "_" + tableName;
        // 创建队列
        Queue queue = new Queue(queueName, true);
        rabbitUtil.addQueue(queue);
        // 监听队列
        rabbitUtil.mqBindListener(queueName);

        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableName);
        int count = 0;
        int listSize = list.size();
        int toIndex = 100;
        List subList = new ArrayList();
        for (int i = 0; i < list.size(); i += 100) {
            if(i + 100 > listSize){
                toIndex = listSize - i;
                count = listSize;
            } else {
                count += 100;
            }
            subList = list.subList(i, i + toIndex);
            map.put("list", subList);
            excelDao.insertObjectList(map);
            rabbitUtil.sendToQueue(queueName, Integer.toString((count * 100 / listSize)));
        }
    }
}
