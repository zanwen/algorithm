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
import java.util.Optional;

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

    /**
     * adjustment after bst's insertion.
     * </hr>
     * the node must be leaf node, and we can regard it as insert a element into a 2-3-4 b-tree.</br>
     * the 2-3-4 b-tree's leaf node must but only have one black node. </br>
     * so the b-tree's leaf node can have four situations below:
     * <ul>
     *     <li>one black node with two red children. R<-B->R </li>
     *     <li>one black node with one red left child. R<-B- </li>
     *     <li>one black node with one red right child. -B->R </li>
     *     <li>one black node. -B- </li>
     * </ul>
     *
     * 1. the insert node is added into the left of right of the black node.
     *
     *          B-      => R<-B- / -B->R
     *          <-B-    => R<-B->R
     *          B->R    => R<-B->R
     *
     *         insert into directly with bst's insertion logic
     *
     * 2. the insert node is added into the left of right of the red node. after insertion, the overflow not occurs
     *
     *               R<-B-    =>      R<-B        R<-B
     *                                 \         /
     *                                  R       R
     *
     *               -B->R    =>      -B->R       -B->R
     *                                  /              \
     *                                 R                R
     *
     *          after insertion of bst, we need rotate to let the mid node become the black node
     *
     * 3. the insert node is added into the left of right of the red node. and then, the overflow occurs
     *
     *              R<-B->R  =>      R<-B->R         R<-B->R         R<-B->R          R<-B->R
     *                             /                        \         \                   /
     *                            R                          R         R                 R
     *
     *          let the left and right become two independent b-tree node(color it to black), and then
     *          color itself to red to become a insertion node added its parent b-tree node
     * @param node
     */
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
            } else {
                // LR
                darken(node);
                redden(grand);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            if (node.isLeftChildOf(parent)) {
                // RL
                darken(node);
                redden(grand);
                rotateRight(parent);
            } else {
                // RR
                redden(grand);
                darken(parent);
            }
            rotateLeft(grand);
        }
    }

    /**
     * 首先，真正被删除的bst节点肯定存在于该红黑树等价的4阶B树的叶子节点中：
     *
     *      a. R_1 <- B_2 -> R_3
     *
     *      b. R_1 <- B_2
     *
     *      c. B_2 -> R_3
     *
     *      d. B_2
     *
     * 1. 如果被删除的节点是红节点，如上例 a,b,c 的 R_1 和 R_3，那么在bst的删除逻辑之后不需要额外的修复操作
     *
     * 2. 如果被删除的节点是黑色节点，如上例 b,c 中的 B_2
     *
     *      2.1 首先不要囊括 a 中的 B_2，这种情况我们会删除其后继节点 R_3
     *
     *      2.2 如果删除 b，c 中的 B_2， bst的删除逻辑是 用其孩子节点 R_1/R_3 替换它，这时我们为了保证等价性
     *      （B树节点中必须有且仅有一个黑色节点），需要将该红色孩子节点染成黑色
     *
     * 3. 如果被删除的节点没有红色孩子节点（即替换节点为null）
     *
     *      3.1 如果被删除节点的兄弟节点是黑色节点
     *
     *          3.1.1 如果【兄弟节点有红色孩子节点可以借】，则通过旋转操作修复红黑树
     *
     *                如果兄弟和其红色孩子节点的相对方位是 LL 或 RR，则对父节点进行 右旋 或 左旋，
     *                并将旋转后的中间节点【继承父节点的颜色】、【中间节点的两个孩子染成黑色】
     *
     *                e.g: delete B_4
     *
     *                          R_3                        R_2
     *                         /   \     =>               /   \
     *                R_1 <- B_2   B_4                 R_1     B_3
     *
     *                如果兄弟和其红色孩子节点的相对方位是 LR 或 RL，则先将其转变为 LL 或 RR 的情况后
     *                再复用上述的处理逻辑
     *
     *                e.g: delete B_5
     *
     *                     R_4                           R_4                      R_3
     *                   /     \        =>             /     \      =>          /     \
     *                B_2->R_3  B_5             B_2->R_3     B_5              B_2     B_4
     *
     *          3.1.2 如果兄弟节点没有红色孩子可以借，则考虑4阶B树的【下溢】，等价修复红黑树
     *
     *                【将父节点拉下来】与当前节点和兄弟节点合并成一个新的4阶B树节点（实际做法是将父节点染黑，兄弟节点染红）
     *                考虑【下溢】有向上传播性，我们将父节点作为删除后节点递归执行修复逻辑
     *
     *                e.g: delete B_8
     *                
     *                           B_5                            B_5                           
     *                         /    \                         /    \                             
     *                     B_3       B_7        =>        B_3       \        =>           B_3   <-  B_5    
     *                   /    \     /   \               /    \       \                  /    \       \   
     *                B_2    B_4   B_6  B_8          B_2    B_4  R_6<-B_7            B_2    B_4  R_6<-B_7
     *
     *                        B_8的兄弟节点B_6没有红色孩子节点          B_7的兄弟节点B_3没有红色孩子节点              下溢到了根节点，终止
     *         
     *      3.2 如果被删除节点的兄弟节点是红色节点
     *          
     *          根据红黑色的性质，等价的4阶B树中，被删除节点和兄弟节点并不处于同一层中
     *          （兄弟节点和父节点位于一个B树节点中，被删除节点位于该B树节点的下一层的B树节点中）
     *
     *          那么兄弟节点肯定有两个黑色孩子节点，与被删除节点位于同一层，可以通过旋转转换成 3.1
     *
     *
     *          e.g: delete B_7
     *
     *              R_4 <- B_6                  B_4 -> R_6
     *            /    \      \      =>       /      /    \
     *          B_3    B_5    B_7           B_3    B_5    B_7
     *
     *               通过对R_6右旋，B_7的兄弟节点由红色的R_4转换成了黑色的B_5，此后可复用 3.1 的逻辑
     *
     *
     *
     * @param node
     * @param replacement
     */
    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        // 1. 如果被删除节点是红色节点
        if (isRed(node)) {
            return;
        }
        // 2. 如果替代被删除节点的是红色节点
        if (isRed(replacement)) {
            darken(replacement);
            return;
        }
        if (node.parent == null) {  // 3.1.2 中的下溢传播到根节点终止
            return;
        }

        Node<E> parent = node.parent;
        boolean left = parent.left == null || parent.left == node; // 当前被删除节点是否是左子节点 或 上溢传播至的节点是否是左子节点
        RBNode<E> sibling = (RBNode<E>) (left ? parent.right : parent.left);

        // 3.2 如果兄弟节点是红色节点，则旋转父节点，转变为 3.1
        if (isRed(sibling)) {
            if (left) rotateRight(parent);
            else rotateLeft(parent);
            afterRemove(node, null);
        // 3.1 兄弟节点是黑色节点
        } else if (hasRedChild(sibling)) {
            // 3.1.1 兄弟节点有红色孩子可以借，将旋转后的中间节点继承父节点颜色，两边节点染黑
            darken(parent); // 父节点不会成为中间节点，直接提前染黑
            if (sibling.isLeftChildOf(parent)) {
                if (isRed(sibling.left)) {
                    // LL 兄弟节点将成为中间节点
                    if (isRed(parent)) {
                        redden(sibling);
                    }
                    darken(sibling.left);
                } else {
                    // LR 兄弟节点的孩子将成为中间节点
                    if (isRed(parent)) {
                        redden(sibling.right);
                    }
                    darken(sibling);
                    rotateLeft(sibling); // 调整 LR 为 LL
                }
                // 到这里肯定是 LL
                rotateRight(parent);
            } else {
                // 与上述对称
                if (isRed(sibling.left)) {
                    // RL
                    if (isRed(parent)) {
                        redden(sibling.left);
                    }
                    darken(sibling);
                    rotateRight(sibling);
                } else {
                    // RR
                    if (isRed(parent)) {
                        redden(sibling);
                    }
                    darken(sibling.right);
                }
                rotateLeft(parent);
            }
        } else {
            // 3.1.2 兄弟节点没有红色孩子可以借，父节点染黑、兄弟节点染红，下溢传播（如果拉下来的父节点是黑色）
            boolean parentColor = ((RBNode<E>) parent).color;
            darken(parent);
            redden(sibling);
            if (parentColor == BLACK) {
                afterRemove(parent, null);
            }
        }
    }

    private boolean hasRedChild(RBNode<E> rbNode) {
        return rbNode != null && (isRed(rbNode.left) || isRed(rbNode.right));
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

    /**
     * if the {@code node} is null or its color is black, it will return false
     * @param node
     * @return
     */
    private boolean isRed(Node<E> node) {
        return node != null && ((RBNode<E>) node).color == RED;
    }

    private boolean isBlack(Node<E> node) {
        // node is leaf's children or non-null black node
        return node == null || ((RBNode<E>) node).color == BLACK;
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
            StringBuilder sb = new StringBuilder();
            Optional.ofNullable(left).ifPresent(p -> sb.append(p.element).append("-"));
            if (color == RED) {
                sb.append("R_");
            }
            sb.append(element);
            Optional.ofNullable(right).ifPresent(p -> sb.append("-").append(p.element));
            Optional.ofNullable(parent).ifPresent(p -> sb.append("(").append(p.element).append(")"));
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                89, 90, 40, 21, 81, 58, 79, 85, 98, 12, 15, 91, 96, 69, 18, 66, 47, 43, 82
        };
        RBTree<Integer> rbt = new RBTree<>();
        for (Integer i : arr) {
            rbt.add(i);
//            System.out.println("add 【" + i + "】");
//            BinaryTrees.println(rbt);
//            System.out.println("=================================================");
        }
        BinaryTrees.println(rbt);
        System.out.println("=================================================");

        for (Integer i : arr) {
            rbt.remove(i);
            System.out.println("remove 【" + i + "】");
            BinaryTrees.println(rbt);
            System.out.println("=================================================");
        }
    }
}
