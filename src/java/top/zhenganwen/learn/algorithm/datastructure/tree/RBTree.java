/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.datastructure.tree;

import top.zhenganwen.learn.algorithm.commons.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author zhenganwen
 * @date 2019/11/28/028 15:52
 */
public class RBTree<E> extends BBST<E> {

    private static boolean RED   = false;
    private static boolean BLACK = true;

    public RBTree() {

    }

    public RBTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    protected void afterAdd(Node<E> node) {
        // the insert node is root node or node overflows to top
        if (node.parent == null) {
            darken(node);
            return;
        }
        // the node is leaf node
        RBNode<E> parent = (RBNode<E>) node.parent;
        // 1. black parent
        if (parent.color == BLACK) {
            // redden it -> default red color
            return;
        }
        // 2. red parent, and grand must exist
        RBNode<E> uncle = sibling(parent);
        RBNode<E> grand = (RBNode<E>) parent.parent;
        if (isRed(uncle)) {
            // 2.1 overflow
            darken(parent);
            darken(uncle);
            redden(grand);
            afterAdd(grand);
            return;
        }
        // 2.2 uncle is null or black
        if (parent.isLeftChildOf(grand)) {
            if (node.isLeftChildOf(parent)) {
                // LL
                darken(parent);
                redden(grand);
                rotateRight(grand);
            } else {
                // LR
                darken(node);
                redden(grand);
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (node.isLeftChildOf(parent)) {
                // RL
                darken(node);
                redden(grand);
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                // RR
                redden(grand);
                darken(parent);
                rotateLeft(grand);
            }
        }
    }

    private RBNode<E> color(Node<E> node, boolean color) {
        RBNode<E> n = (RBNode<E>) node;
        n.color = color;
        return n;
    }

    private RBNode redden(Node<E> node) {
        return color(node, RED);
    }

    private RBNode darken(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean isRed(Node<E> node) {
        return node != null && ((RBNode<E>) node).color == RED;
    }

    private RBNode<E> sibling(Node<E> node) {
        if (node.parent == null) {
            return null;
        }
        if (node.isLeftChildOf(node.parent)) {
            return (RBNode<E>) node.parent.right;
        } else {
            return (RBNode<E>) node.parent.left;
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private class RBNode<E> extends Node<E> {
        boolean color = RED;

        RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String s = "";
            if (color == RED) {
                s += "R_";
            }
            return s + element + "(" + (parent == null ? "null" : parent.element) + ")";
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                89, 90, 40, 21, 81, 58, 79, 85, 98, 12, 15, 91, 96, 69, 18, 66, 47, 43, 82
        };
        RBTree<Integer> rbt = new RBTree<>();
        for (Integer i : arr) {
            System.out.println("【" + i + "】");
            rbt.add(i);
            BinaryTrees.println(rbt);
            System.out.println("=================================================");
        }
    }
}
