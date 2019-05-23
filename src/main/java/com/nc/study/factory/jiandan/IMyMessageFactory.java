package com.nc.study.factory.jiandan;

/**
 * @author zhangyangyang
 * @date 2018/12/3 17:13
 * 简单工厂模式
 */
public class IMyMessageFactory {
    public static final String SOUTH_MESSAGE = "SOUTH";
    public static final String NOUTH_MESSAGE = "NOUTH";

    public static MyMessage createMessage (String message) {
        switch (message) {
            case SOUTH_MESSAGE:
                return new SMyMessage();
            case NOUTH_MESSAGE:
                return new NMyMessage();
            default:
                return new SMyMessage();
        }
    }
}
