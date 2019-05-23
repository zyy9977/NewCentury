package com.nc.core.excel.aop;

import com.nc.common.rabbitmq.message.RabbitUtil;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangyangyang
 * @date 2019/3/20 10:22
 */
public class ExcelAfterAspect {

    @Autowired
    private RabbitUtil rabbitUtil;
    public void afterMethod(JoinPoint joinPoint, Exception e) {
        Object[] args = joinPoint.getArgs();
        System.out.println(args);
        rabbitUtil.sendToQueue(args[1] + "_" + args[2], "");
    }
}
