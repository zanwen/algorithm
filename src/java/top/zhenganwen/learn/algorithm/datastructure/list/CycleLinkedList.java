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
public class CycleLinkedList<E> extends AbstractList<E> {

    private Node<E> head;

    private Node<E> tail;

    public CycleLinkedList() {
        head = new Node<>(null, null);
        tail = null;
    }

    @Override
    public E remove(int index) {
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node<E> prev = node(index - 1);
        E value = prev.next.val;
        prev.next = prev.next.next;
        size--;
        return value;
    }

    private E removeLast() {
        emptyCheck();
        E value = tail.val;
        if (size == 1) {
            head.next = tail = null;
        } else {
            Node<E> newTail = node(size - 2);
            newTail.next = head.next;
            tail = newTail;
        }
        size--;
        return value;
    }

    private E removeFirst() {
        emptyCheck();
        E value = head.next.val;
        head.next = head.next.next;
        if (size == 1) {
            tail = null;
        } else {
            tail.next = head.next;
        }
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
        if (index == size - 1) {
            return tail;
        }
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
            addFirst(e);
            return;
        }
        addLast(e);
    }

    private void addLast(E e) {
        Node<E> newTail = new Node<>(e, head.next);
        tail.next = newTail;
        tail = newTail;
        size++;
    }

    @Override
    public void insert(int index, E e) {
        if (size == 0) {
            addFirst(e);
            return;
        }
        if (index == size) {
            addLast(e);
            return;
        }
        Node<E> prev = node(index - 1);
        Node<E> node = new Node<>(e, prev.next);
        prev.next = node;
        size++;
    }

    private void addFirst(E e) {
        Node<E> first = new Node<>(e, head.next);
        head.next = first;
        tail = size == 0 ? first : tail;
        tail.next = first;
        size++;
        return;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<E> node = head.next;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(node.val).append("_").append(node.next.val);
            node = node.next;
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        CycleLinkedList linkedList = new CycleLinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        // 1,2,3,4,5
        System.out.println(linkedList);

        linkedList.insert(2, 7);
        linkedList.insert(6, 6);
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
