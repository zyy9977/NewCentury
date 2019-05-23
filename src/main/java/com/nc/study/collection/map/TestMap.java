package com.nc.study.collection.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyangyang
 * @date 2018/11/20 19:57
 */
public class TestMap {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        String k = map.put("4", "4"); // 返回被覆盖的值，如果没有返回null

        System.out.println(k);
    }
}
