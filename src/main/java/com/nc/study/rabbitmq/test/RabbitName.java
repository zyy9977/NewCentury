package com.nc.study.rabbitmq.test;

/**
 * @author zhangyangyang
 * @date 2018/11/28 17:45
 */
public enum RabbitName {
    RABBIT("1", "RABBIT_ZYY"), FAILED("2", "失败"), EXCHANGE_NAME("3", "EXCHANGE_ZYY");

    private String value;
    private String desc;

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    RabbitName(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
