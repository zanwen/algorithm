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
 * @date 2019/11/410:10
 */
public abstract class AbstractList<E> implements List<E> {

    /**
     * 集合中元素个数
     */
    protected int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean notEmpty() {
        return !isEmpty();
    }

    public void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format("index: %d, size: %s", index, size));
        }
    }

    public void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format("index: %d, size: %s", index, size));
        }
    }
}
