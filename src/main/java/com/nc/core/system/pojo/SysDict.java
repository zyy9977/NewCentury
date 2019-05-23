package com.nc.core.system.pojo;

import java.io.Serializable;

/**
 * @author zhangyangyang
 * @date 2019/3/4 14:08
 */
public class SysDict implements Serializable{
    private String dictItem;
    private String subItem;
    private String subItemName;
    private String itemName;

    public String getDictItem() {
        return dictItem;
    }

    public void setDictItem(String dictItem) {
        this.dictItem = dictItem;
    }

    public String getSubItem() {
        return subItem;
    }

    public void setSubItem(String subItem) {
        this.subItem = subItem;
    }

    public String getSubItemName() {
        return subItemName;
    }

    public void setSubItemName(String subItemName) {
        this.subItemName = subItemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
