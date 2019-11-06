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
public class CycleArrayList<E> extends AbstractList<E> {

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

    /**
     * point at first element's index
     */
    private int             first;

    /**
     * point at last element's index
     */
    private int             last;

    public CycleArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public CycleArrayList(int capacity) {
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[this.capacity];
        first = last = -1;
    }

    @Override
    public void add(E e) {
        rangeCheckForAdd(size);
        ensureCapacity();
        first = first == -1 ? 0 : first;
        elements[mapIndex(++last)] = e;
        size++;
    }

    /**
     * 确保容量足够, 不够则立即扩容
     */
    private void ensureCapacity() {
        if (size == capacity) {
            int newCapacity = capacity + (capacity >> 1);
            E[] newArray = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = elements[mapIndex(i)];
            }
            logger.info("extends capacity: {} -> {}", capacity, newCapacity);
            elements = newArray;
            first = 0;
            last = size - 1;
            capacity = newCapacity;
        }
    }

    @Override
    public void insert(int index, E e) {
        rangeCheckForAdd(index);
        ensureCapacity();
        if (index == 0) {
            first = mapIndex(-1);
            elements[first] = e;
        } else {
            boolean leftIndex = index < (size >> 1);
            if (leftIndex) {
                // shift left
                for (int i = -1; i < index; i++) {
                    elements[mapIndex(i)] = elements[mapIndex(i + 1)];
                }
                first = mapIndex(-1);
            } else {
                // shift right
                for (int i = size; i > index; i--) {
                    elements[mapIndex(i)] = elements[mapIndex(i - 1)];
                }
                last = mapIndex(size);
            }
            elements[mapIndex(index)] = e;
        }
        size++;
    }

    /**
     * map negative index and exceeding index to valid index in cycle array
     * 
     * @param index
     * @return
     */
    private int mapIndex(int index) {
        return (first + index + capacity) % capacity;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E target = get(index);
        boolean leftIndex = index < (size >> 1);
        if (leftIndex) {
            for (int i = index; i > 0; i--) {
                elements[mapIndex(i)] = elements[mapIndex(i - 1)];
            }
            first = mapIndex(1);
        } else {
            for (int i = index; i < size; i++) {
                elements[mapIndex(i)] = elements[mapIndex(i + 1)];
            }
            last = mapIndex(size - 2);
        }
        size--;
        return target;
    }

    @Override
    public E set(int index, E e) {
        rangeCheck(index);
        E old = get(index);
        elements[mapIndex(index)] = e;
        return old;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elements[mapIndex(index)];
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(e, get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            builder.append(i > 0 ? ", " : "");
            builder.append(get(i));
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        CycleArrayList list = new CycleArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // 1 2 3 4
        System.out.println(list);

        list.insert(0, 5);
        // 5 1 2 3 4
        System.out.println(list);
        list.insert(4, 6);
        // 5 1 2 3 6 4
        System.out.println(list);

        list.remove(2);
        // 5 1 3 6 4
        System.out.println(list);

        list.insert(2, 1);
        // 5 1 1 3 6 4
        System.out.println(list);
        list.insert(5, 8);
        // 5 1 1 3 6 8 4
        System.out.println(list);

        list.insert(4, 9);
        // 5 1 1 3 9 6 8 4
        System.out.println(list);

        list.remove(7);
        // 5 1 1 3 9 6 8
        System.out.println(list);

        list.insert(0, 0);
        // 0 5 1 1 3 9 6 8
        System.out.println(list);
    }
}
