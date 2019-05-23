package com.nc.core.system.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangyangyang
 * @date 2018/9/18 16:27
 */
public class Sys_User implements Serializable {

    private int uid;
    private String userName;
    private String password;
    private String chineseName;
    private String remark;

    private List<Sys_Role> roleList;
    private List<Sys_Menu> menuList;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Sys_Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Sys_Role> roleList) {
        this.roleList = roleList;
    }

    public List<Sys_Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Sys_Menu> menuList) {
        this.menuList = menuList;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }
}
