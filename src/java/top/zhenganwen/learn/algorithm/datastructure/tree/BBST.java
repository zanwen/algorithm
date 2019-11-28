/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.datastructure.tree;

import java.util.Comparator;

/**
 * @author zhenganwen
 * @date 2019/11/28/028 15:53
 */
public class BBST<E> extends BinarySearchTree<E> {

    public BBST() {

    }

    public BBST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    protected void rotateLeft(Node<E> node) {
        Node<E> child = node.right;
        // rotate left
        node.right = child.left;
        child.left = node;
        afterRotate(node, child);
    }

    protected void afterRotate(Node<E> node, Node<E> child) {
        // link parent
        child.parent = node.parent;
        if (node.parent == null)
            root = child;
        else if (node.isLeftChildOf(node.parent))
            node.parent.left = child;
        else
            node.parent.right = child;
        node.parent = child;
        if (node == child.right && node.left != null)
            node.left.parent = node;
        if (node == child.left && node.right != null)
            node.right.parent = node;
    }

    protected void rotateRight(Node<E> node) {
        Node<E> child = node.left;
        // rotate right
        node.left = child.right;
        child.right = node;
        afterRotate(node, child);
    }

    /**
     *
     * LL
     *
     * inorder traversal: a b c d e f g
     *                     |
     *              _______f______
     *             |             |
     *         ____d____         g                  ____d____
     *        |        |              ===>         |        |
     *       _b_       e                          _b_      _f_
     *      |  |                                 |  |     |  |
     *      a  c                                 a  c     e  g
     *
     *
     * RR
     *
     * inorder traversal: a b c d e f g
     *            |
     *        ____b____
     *       |        |
     *       a    ____d____                        ____d____
     *           |        |          ===>         |        |
     *           c       _f_                     _b_      _f_
     *                  |  |                    |  |     |  |
     *                  e  g                    a  c     e  g
     *
     * LR
     *
     * inorder traversal: a b c d e f g
     *                  |
     *            ______f_____
     *           |           |
     *        ___b___        g                  ____d____
     *       |      |             ===>         |        |
     *       a     _d_                        _b_      _f_
     *            |  |                       |  |     |  |
     *            c  e                       a  c     e  g
     *
     *
     * RL
     *
     * inorder traversal: a b c d e f g
     *             |
     *       ______b______
     *      |            |
     *      a         ___f___                  ____d____
     *               |      |    ===>         |        |
     *              _d_     g                _b_      _f_
     *             |  |                     |  |     |  |
     *             c  e                     a  c     e  g
     *
     *
     * @param r the root node of the child tree
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @param g
     */
    protected void rotate(
            Node<E> r,
            Node<E> a, Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g
    ) {
        // d -> new root of the child tree
        d.parent = r.parent;
        if (r.parent == null)
            root = d;
        else if (r.isLeftChildOf(r.parent))
            r.parent.left = d;
        else
            r.parent.right = d;

        // a-b-c
        b.left = a;
        b.right = c;
        if (a != null)
            a.parent = b;
        if (c != null)
            c.parent = b;


        // e-f-g
        f.left = e;
        f.right = g;
        if (e != null)
            e.parent = f;
        if (g != null)
            g.parent = f;


        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }
}
