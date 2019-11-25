/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenganwen
 * @date 2019/11/19 17:12
 */
public class _590_n_ary_tree_post_order_traversal {

    /**
     * 执行用时 : 1 ms , 在所有 java 提交中击败了 100.00% 的用户 内存消耗 : 40.2 MB , 在所有 java 提交中击败了
     * 100.00% 的用户
     * 
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList();
        postorder(root, list);
        return list;
    }

    public void postorder(Node root, List<Integer> list){
        if(root == null) return;
        List<Node> children = root.children;
        if(children.size() == 0){
            list.add(root.val);
            return;
        }
        for(int i = 0 ; i < children.size() ; i++){
            postorder(children.get(i), list);
        }
        list.add(root.val);
    }

    /**
     * 执行用时 : 7 ms , 在所有 java 提交中击败了 27.03% 的用户 内存消耗 : 40 MB , 在所有 java 提交中击败了
     * 100.00% 的用户
     * 
     * @param root
     * @return
     */
    /*public static List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList();
        if (root == null)
            return res;

        Stack<Node> stack = new Stack();
        stack.push(root);
        Node lastVisit = null;
        while (!stack.isEmpty()) {
            Node node = stack.peek();
            if (isLeaf(node) || oneIsChildOfAnother(lastVisit, node)) {
                stack.pop();
                res.add(node.val);
                lastVisit = node;
            } else {
                List<Node> children = node.children;
                for (int i = children.size() - 1; i >= 0; i--)
                    stack.push(children.get(i));
            }
        }
        return res;
    }

    public static boolean isLeaf(Node node) {
        return node != null && (node.children == null || node.children.size() == 0);
    }

    public static boolean oneIsChildOfAnother(Node one, Node another) {
        if (one == null)
            return false;
        List<Node> children = another.children;
        for (int i = 0; i < children.size(); i++)
            if (one == children.get(i))
                return true;
        return false;
    }

    public static void main(String[] args) {
        Node node = new Node(1,
                Arrays.asList(new Node(3, Arrays.asList(new Node(5), new Node(6))), new Node(2), new Node(4)));
        System.out.println(postorder(node));
    }*/
}
