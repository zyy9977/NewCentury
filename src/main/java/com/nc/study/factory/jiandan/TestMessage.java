package com.nc.study.factory.jiandan;

/**
 * @author zhangyangyang
 * @date 2019/2/22 13:52
 */
public class TestMessage {
    public static void main(String[] args) {
        MyMessage myMessage = IMyMessageFactory.createMessage(IMyMessageFactory.NOUTH_MESSAGE);
        myMessage.desc();
    }
}
