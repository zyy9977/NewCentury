package com.nc.core.system.pojo;

import java.io.Serializable;

/**
 * @author zhangyangyang
 * @date 2019/1/4 15:48
 */
public class Sys_Role implements Serializable {
    private int id;
    private String role;
    private String roleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
