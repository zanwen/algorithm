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
 * @date 2019/11/411:44
 */
public class LinkedList<E> extends AbstractList<E> {

    private Node<E> head;

    public LinkedList() {
        head = new Node<>(null, null);
    }

    @Override
    public E remove(int index) {
        emptyCheck();
        E value;
        if (index == 0) {
            value = head.next.val;
            head.next = head.next.next;
            size--;
            return value;
        }
        Node<E> prev = node(index - 1);
        value = prev.next.val;
        prev.next = prev.next.next;
        size--;
        return value;
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IllegalStateException("empty list");
        }
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = head.next;
        while (index-- > 0) {
            node = node.next;
        }
        return node;
    }

    @Override
    public E set(int index, E e) {
        Node<E> node = node(index);
        E value = node.val;
        node.val = e;
        return value;
    }

    @Override
    public E get(int index) {
        return node(index).val;
    }

    @Override
    public int indexOf(E e) {
        Node<E> node = head.next;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(node.val, e)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<E> {
        E       val;
        Node<E> next;

        public Node() {
        }

        public Node(E val, Node<E> next) {
            this.val = val;
            this.next = next;
        }
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void add(E e) {
        if (size == 0) {
            head.next = new Node<>(e, null);
            size++;
            return;
        }
        Node<E> oldTail = node(size - 1);
        oldTail.next = new Node<>(e, null);
        size++;
    }

    @Override
    public void insert(int index, E e) {
        if (size == 0) {
            head.next = new Node<>(e, null);
            size++;
            return;
        }
        Node<E> prev = node(index - 1);
        Node<E> node = new Node<>(e, prev.next);
        prev.next = node;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<E> node = head.next;
        for (int i = 0; i < size; i++) {
            builder.append(i == 0 ? node.val : "," + node.val);
            node = node.next;
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        // 1,2,3,4,5
        System.out.println(linkedList);

        linkedList.insert(2, 7);
        linkedList.insert(6,6);
        // 1 2 7 3 4 5 6
        System.out.println(linkedList);

        linkedList.remove(0);
        linkedList.remove(5);
        linkedList.remove(1);
        // 2 3 4 5
        System.out.println(linkedList);

        linkedList.set(2, 1);
        // 2 3 1 5
        System.out.println(linkedList);

        // 5
        System.out.println(linkedList.get(3));
    }

}
