/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.datastructure.tree;

import top.zhenganwen.learn.algorithm.commons.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

import static java.util.Objects.isNull;

/**
 * @author zhenganwen
 * @date 2019/11/6 17:48
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {

    private Node<E>       root;

    private int           size;

    private Comparator<E> comparator;

    public BinarySearchTree() {

    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        nonNullCheck(element);

        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        Node<E> parent = root, cur = root;
        int compare = 0;
        while (cur != null) {
            parent = cur;
            compare = compare(element, cur.element);
            cur = compare > 0 ? cur.right : compare < 0 ? cur.left : cur;
            if (cur == parent) {
                cur.element = element;
                return;
            }
        }
        Node<E> node = new Node<>(element, parent);
        if (compare > 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        size++;
    }

    private int compare(E insert, E current) {
        if (comparator != null) {
            return Objects.compare(insert, current, comparator);
        }
        return ((Comparable<E>) insert).compareTo(current);
    }

    private void nonNullCheck(E element) {
        if (isNull(element)) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element;
    }

    private static class Node<E> {
        E       element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        Node(E element, Node<E> parent) {
            this(element);
            this.parent = parent;
        }

        Node(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }

    public static void preOrderUnRecur(Node root) {
        emptyTreeCheck(root);
        Stack<Node> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            stringBuilder.append(node.element).append(" ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println(stringBuilder.toString());
    }

    private static void emptyTreeCheck(Node root) {
        if (root == null) {
            throw new IllegalArgumentException("empty tree");
        }
    }

    public static void inOrderUnRecur(Node root) {
        emptyTreeCheck(root);
        StringBuilder sb = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
            while (root == null) {
                if (stack.isEmpty()) {
                    break;
                } else {
                    Node node = stack.pop();
                    sb.append(node.element).append(" ");
                    root = node.right;
                }
            }
        }
        System.out.println(sb.toString());
    }

    private static void postOrderUnRecur(Node root) {
        emptyTreeCheck(root);
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node lastAccess = null;
        while (!stack.isEmpty()) {
            Node node = stack.peek();
            // 当来到的节点node是叶子节点或上次访问的节点是其子节点时,需要进行访问
            if (isLeaf(node) || oneIsChildOfAnother(lastAccess, node)) {
                stack.pop();
                stringBuilder.append(node.element).append(" ");
                lastAccess = node;
            } else {
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        System.out.println(stringBuilder.toString());
    }

    public static void levelOrder(Node root) {
        emptyTreeCheck(root);
        StringBuilder stringBuilder = new StringBuilder();
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            stringBuilder.append(node.element).append(" ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        System.out.println(stringBuilder.toString());
    }

    private static boolean oneIsChildOfAnother(Node one, Node another) {
        return one != null && (one == another.left || one == another.right);
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int heightUnRecur(Node root) {
        if (root == null) {
            return 0;
        }
        Stack<Node> s1 = new Stack<>(), s2 = new Stack<>();
        int height = 0;
        s1.push(root);
        while (!s1.isEmpty()) {
            while (!s1.isEmpty()) {
                Node node = s1.pop();
                if (node.left != null) {
                    s2.push(node.left);
                }
                if (node.right != null) {
                    s2.push(node.right);
                }
            }
            height++;
            Stack tmp = s1;
            s1 = s2;
            s2 = tmp;
        }
        return height;
    }

    public static boolean isCompleteBinaryTreeUnRecur(Node root) {
        if (root == null) {
            return true;
        }
        boolean leafMode = false;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.pollFirst();
            if (leafMode) {
                if (!isLeaf(node)) {
                    return false;
                }
                continue;
            }
            if (hasTwoChild(node)) {
                queue.addLast(node.left);
                queue.addLast(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {
                leafMode = true;
                if (node.left != null) {
                    queue.addLast(node.left);
                }
            }
        }
        return true;
    }

    private static boolean hasTwoChild(Node node) {
        return node != null && node.left != null && node.right != null;
    }

    public static void main(String[] args) {
        /*int[] arr = { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1 };
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(Collections.reverseOrder(Integer::compareTo));
        for (int i : arr) {
            tree.add(i);
        }
        BinaryTrees.print(tree);*/

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.left.left = new Node(8);
        root.left.right.left = new Node(9);
        root.left.right.left.left = new Node(10);

//        preOrderUnRecur(root);
//        inOrderUnRecur(root);
//        postOrderUnRecur(root);
//        System.out.println(height(root));
//        System.out.println(heightUnRecur(root));
//        System.out.println(isCompleteBinaryTreeUnRecur(root));
        levelOrder(root);

    }

}
