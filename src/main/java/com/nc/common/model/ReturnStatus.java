package com.nc.common.model;

/**
 * @author zhangyangyang
 * @date 2019/1/22 10:04
 */
public enum ReturnStatus {

    OK("200", "成功"),
    ERROR("300", "失败"),
    EXCEPTION("400", "异常"),

    LOGIN_SUCCESS("10", "登录成功"),
    LOGIN_FAILED("9920", "登录失败"),
    NO_PERMISSION("9930", "权限不足");


    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ReturnStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
