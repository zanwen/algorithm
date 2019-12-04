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
 * @date 2019/11/25 13:46
 */
public class AVLTree<E> extends BBST<E> {

    public AVLTree() {

    }

    public AVLTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * just need once rebalance
     *
     * @param node
     */
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
     * <p>
     * maybe need O(logn) times rebalance. see red-black tree {@link RBTree}
     *
     * @param node
     */
    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                rebalance(node);
            }
        }
    }

    /**
     * see {@link this#rebalance)}
     * 平衡方案一：左旋右旋分开来做
     *
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
     * see {@link this#rebalance2}
     * 平衡方案二：从四种变换中抽离出通用的逻辑
     *
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

    @Override
    protected void afterRotate(Node<E> node, Node<E> child) {
        super.afterRotate(node, child);
        ((AVLNode) node).updateHeight();
        ((AVLNode) child).updateHeight();
    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        ((AVLNode) b).updateHeight();
        ((AVLNode) f).updateHeight();
        ((AVLNode) d).updateHeight();
    }

    private AVLNode cast(Node node) {
        return (AVLNode) node;
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

        @Override
        public String toString() {
            return element.toString() + "(" + (parent == null ? "null" : parent.element) + ")";
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        for (int i = 0; i < 50; i++) {
            tree.add(i);
        }
        BinaryTrees.println(tree);

    }
}
