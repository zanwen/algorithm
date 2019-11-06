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
public class CycleDoublyLinkedList<E> extends AbstractList<E> {

    private Node<E> head;

    public CycleDoublyLinkedList() {
        head = new Node<>(null, null, null);
    }

    @Override
    public E remove(int index) {
        return index == 0 ? removeFirst() : index == size - 1 ? removeLast() : removeMiddleNode(index);
    }

    private E removeLast() {
        emptyCheck();
        Node<E> first = head.next, tail = first.prev;
        E value = tail.val;
        first.prev = tail.prev;
        tail.prev.next = first;
        size--;
        return value;
    }

    private E removeFirst() {
        emptyCheck();
        Node<E> first = head.next, tail = first.prev;
        E value = first.val;
        head.next = first.next;
        head.next.prev = tail;
        tail.next = head.next;
        size--;
        return value;
    }

    private void emptyCheck() {
        if (isEmpty()) {
            throw new IllegalStateException("empty list");
        }
    }

    private E removeMiddleNode(int index) {
        Node<E> target = node(index);
        E value = target.val;
        target.prev.next = target.next;
        target.next.prev = target.prev;
        size--;
        return value;
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        boolean leftIndex = index < (size >> 1);
        int reduction = leftIndex ? index : size - 1 - index;
        Node<E> p = leftIndex ? head.next : head.next.prev;
        if (leftIndex) {
            while (reduction-- > 0) {
                p = p.next;
            }
        } else {
            while (reduction-- > 0) {
                p = p.prev;
            }
        }
        return p;
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
        Node<E> prev;

        public Node(E val) {
            this.val = val;
        }

        public Node() {

        }

        public Node(Node<E> prev, E val, Node<E> next) {
            this.prev = prev;
            this.val = val;
            this.next = next;
        }
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void add(E e) {
        if (size == 0) {
            Node<E> first = new Node<>(e);
            head.next = first.next = first.prev = first;
        } else {
            Node<E> first = head.next, tail = first.prev;
            Node<E> node = new Node<>(tail, e, first);
            tail.next = first.prev = node;
        }
        size++;
    }

    @Override
    public void insert(int index, E e) {
        if (index == 0) {
            Node<E> first = head.next, tail = first.prev;
            Node<E> node = new Node<>(tail, e, first);
            first.prev = tail.next = node;
            size++;
        } else if (index == size) {
            add(e);
            return;
        }
        Node<E> next = node(index);
        Node<E> node = new Node<>(next.prev, e, next);
        node.prev.next = node.next.prev = node;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<E> node = head.next;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(node.prev.val).append("_").append(node.val).append("_").append(node.next.val);
            node = node.next;
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        CycleDoublyLinkedList linkedList = new CycleDoublyLinkedList();
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
