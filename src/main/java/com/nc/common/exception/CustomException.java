package com.nc.common.exception;

/**
 * @author zhangyangyang
 * 自定义异常
 * @date 2018/12/24 10:47
 */
public class CustomException extends Exception {
    private int value;
    public CustomException(String msg) {
        super(msg);
    }
    public CustomException(String msg,int value) {
        super(msg);
        this.value=value;
    }
    public int getValue() {
        return value;
    }

}
