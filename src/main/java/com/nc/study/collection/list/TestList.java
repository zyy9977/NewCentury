package com.nc.study.collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author zhangyangyang
 * @date 2018/11/19 19:30
 */
public class TestList {

    // psvm

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        list.add(1, "6");

        list.remove(3);
        list.get(1);

        list.indexOf("4"); // 返回4的索引

        list.subList(2, 3); // 返回1到2之间的元素

        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
    }
}
