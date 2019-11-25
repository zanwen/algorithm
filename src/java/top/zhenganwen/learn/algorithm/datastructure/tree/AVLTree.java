/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.datastructure.tree;

import top.zhenganwen.learn.algorithm.commons.printer.BinaryTrees;

/**
 * @author zhenganwen
 * @date 2019/11/25 13:46
 */
public class AVLTree<E> extends BinarySearchTree<E> {

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                rebalance(node);
                break;
            }
        }
    }

    /**
     * remove the {@code node}, maybe cause the LL or RR situation generating,
     * this depends on the height of right child's left height when remove left child's node
     * and the height of left child's right height when remove right child's node.
     * what's more, this time rebalance maybe cause the ancestor's unbalance.
     * @param node
     */
    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                rebalance(node);
            }
        }
    }

    /**
     * 平衡方案一：左旋右旋分开来做
     * @param node
     */
    private void rebalance2(Node<E> node) {
        AVLNode grand = (AVLNode) node;
        AVLNode parent = getTallerChild(grand);
        AVLNode child = getTallerChild(parent);
        if (parent == grand.left) {
            if (child == parent.left) {
                // LL rotate right
                rotateRight(grand);
            } else {
                // LR rotate left first and then rotate right
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (child == parent.right) {
                // RR rotate left
                rotateLeft(grand);
            } else {
                // RL rotate right first and then rotate left
                rotateRight(parent);
                rotateLeft(grand);
            }
        }
    }

    /**
     * 平衡方案二：从四种变换中抽离出通用的逻辑
     * @param node
     */
    private void rebalance(Node<E> node) {
        AVLNode grand = (AVLNode) node;
        AVLNode parent = getTallerChild(grand);
        AVLNode child = getTallerChild(parent);
        if (parent == grand.left) {
            if (child == parent.left) {
                /*
                  LL
                           _______f______
                          |             |
                      ____d____         g
                     |        |
                    _b_       e
                   |  |
                   a  c

                   f -> grand, d -> parent, b -> child
                 */
                rotate(grand,
                        cast(child.left), child, cast(child.right),
                        parent,
                        cast(parent.right), grand, cast(grand.right));
            } else {
                /*
                  LR
                       ______f_____
                      |           |
                   ___b___        g
                  |      |
                  a     _d_
                       |  |
                       c  e

                  f -> grand, b -> parent, d -> child
                 */
                rotate(grand,
                        cast(parent.left), parent, cast(child.left),
                        child,
                        cast(child.right), grand, cast(grand.right));
            }
        } else {
            if (child == parent.right) {
                /*
                  RR
                   ____b____
                  |        |
                  a    ____d____
                      |        |
                      c       _f_
                             |  |
                             e  g

                  b -> grand, d -> parent, f -> child
                 */
                rotate(grand,
                        cast(grand.left), grand, cast(parent.left),
                        parent,
                        cast(child.left), child, cast(child.right));

            } else {
                /*
                  RL
                   ______b______
                  |            |
                  a         ___f___
                           |      |
                          _d_     g
                         |  |
                         c  e

                  b -> grand, f -> parent, d -> child
                 */
                rotate(grand,
                        cast(grand.left), grand, cast(child.left),
                        child,
                        cast(child.right), parent, cast(parent.right));
            }
        }
    }

    private AVLNode cast(Node node) {
        return (AVLNode) node;
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
    private void rotate(
            AVLNode r,
            AVLNode a,AVLNode b, AVLNode c,
            AVLNode d,
            AVLNode e, AVLNode f, AVLNode g
    ) {
        // d -> new root of the child tree
        d.parent = r.parent;
        if (r.parent == null) root = d;
        else if (r.isLeftChildOf(r.parent)) r.parent.left = d;
        else  r.parent.right = d;

        // a-b-c
        b.left = a;
        b.right = c;
        if (a != null) a.parent = b;
        if (c != null) c.parent = b;
        b.updateHeight();

        // e-f-g
        f.left = e;
        f.right = g;
        if (e != null) e.parent = f;
        if (g != null) g.parent = f;
        f.updateHeight();

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        d.updateHeight();
    }

    private void rotateLeft(AVLNode node) {
        AVLNode child = (AVLNode) node.right;
        // rotate left
        node.right = child.left;
        child.left = node;
        afterRotate(node, child);
    }

    private void afterRotate(AVLNode node, AVLNode child) {
        // link parent
        child.parent = node.parent;
        if (node.parent == null)
            root = child;
        else if (node.isLeftChildOf(node.parent))
            node.parent.left = child;
        else
            node.parent.right = child;
        node.parent = child;
        if (node.right != null)
            node.right.parent = node;
        // update height
        node.updateHeight();
        child.updateHeight();
    }

    private void rotateRight(AVLNode node) {
        AVLNode child = (AVLNode) node.left;
        // rotate right
        node.left = child.right;
        child.right = node;
        afterRotate(node, child);
    }

    private AVLNode getTallerChild(AVLNode node) {
        int r = node.getRightHeight();
        int l = node.getLeftHeight();
        return (AVLNode) (r > l ? node.right : node.left);
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode) node).updateHeight();
    }

    protected boolean isBalanced(Node<E> node) {
        return ((AVLNode) node).isBalanced();
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode(element, parent);
    }

    protected static class AVLNode extends Node {
        int height = 1;

        AVLNode(Object element, Node parent) {
            super(element, parent);
        }

        void updateHeight() {
            int r = getRightHeight();
            int l = getLeftHeight();
            height = 1 + Math.max(r, l);
        }

        int getLeftHeight() {
            return left == null ? 0 : ((AVLNode) left).height;
        }

        int getRightHeight() {
            return right == null ? 0 : ((AVLNode) right).height;
        }

        int balanceFactor() {
            int r = getRightHeight();
            int l = getLeftHeight();
            return Math.abs(r - l);
        }

        boolean isBalanced() {
            return balanceFactor() <= 1;
        }

        boolean isLeftChildOf(Node node) {
            return this == node.left;
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        for (int i = 0; i < 100; i++) {
            tree.add(i);
        }
        BinaryTrees.println(tree);
        for (int i = 0; i < 50; i++) {
            tree.remove(i);
        }
        BinaryTrees.println(tree);

    }
}
