package com.nc.study.collection;

/**
 * @author zhangyangyang
 * @date 2018/11/19 19:14
 */
public interface IList<T> {

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 长度
     * @return
     */
    int length();

    /**
     * 根据索引获取值
     * @param i
     * @return
     */
    T get(int i);

    /**
     * 设置第i个元素为 t
     * @param i
     * @param t
     */
    void set(int i, T t);

    /**
     * 在线性表最后添加元素
     * @param t
     */
    void append(T t);

    /**
     * 删除第i个元素
     * @param i
     * @return
     */
    T remove(int i);

    /**
     * 删除所有元素
     */
    void removeAll();

    /**
     * 查询首次出现关键字为key的元素
     * @param key
     * @return
     */
    T search(T key);

    void insert(int i,T x);
    /**
     * 升序添加
     * @param x
     */
    void insert(T x);
    /**
     * 升序删除
     * @param x
     */
    void remove(T x);
}
