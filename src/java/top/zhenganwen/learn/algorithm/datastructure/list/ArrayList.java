/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.datastructure.list;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author zhenganwen
 * @date 2019/11/410:16
 */
public class ArrayList<E> extends AbstractList<E> {

    private Logger          logger           = LoggerFactory.getLogger(getClass());

    public static final int DEFAULT_CAPACITY = 10;

    /**
     * 数组容量
     */
    private int             capacity;

    /**
     * 容器数组
     */
    private E[]             elements;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[this.capacity];
    }

    @Override
    public void add(E e) {
        rangeCheckForAdd(size);
        ensureCapacity();
        elements[size++] = e;
    }

    /**
     * 确保容量足够, 不够则立即扩容
     */
    private void ensureCapacity() {
        if (size == capacity) {
            int newCapacity = capacity + (capacity >> 1);
            E[] newArray = (E[]) new Object[newCapacity];
            for (int i = 0; i < capacity; i++) {
                newArray[i] = elements[i];
            }
            logger.info("extends capacity: {} -> {}", capacity, newCapacity);
            elements = newArray;
            capacity = newCapacity;
        }
    }

    @Override
    public void insert(int index, E e) {
        rangeCheckForAdd(index);
        ensureCapacity();
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = e;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E target = get(index);
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return target;
    }

    @Override
    public E set(int index, E e) {
        rangeCheck(index);
        E old = get(index);
        elements[index] = e;
        return old;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(e, elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }
}
