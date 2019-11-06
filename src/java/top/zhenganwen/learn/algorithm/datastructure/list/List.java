/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.datastructure.list;

/**
 * @author zhenganwen
 * @date 2019/11/410:05
 */
public interface List<E> {

    /**
     * 向尾部增加元素
     * 
     * @param e
     */
    void add(E e);

    /**
     * 向索引处插入元素
     *
     * @param index
     * @param e
     */
    void insert(int index, E e);

    /**
     * 移除索引上的元素
     * 
     * @param index
     * @return
     */
    E remove(int index);

    /**
     * 覆盖某索引上的元素
     *
     * @param index
     * @param e
     * @return
     */
    E set(int index, E e);

    /**
     * 获取某索引上的元素
     * 
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 返回元素在集合中的索引
     * 
     * @param e
     * @return
     */
    int indexOf(E e);

    /**
     * 集合元素个数
     * 
     * @return
     */
    int size();
}
