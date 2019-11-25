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
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/submissions/
 * 
 * @author zhenganwen
 * @date 2019/11/19 16:49
 */
public class _589_n_ary_tree_preorder_traversal {

    /**
     * 执行用时 : 4 ms , 在所有 java 提交中击败了 40.99% 的用户 内存消耗 : 40.1 MB , 在所有 java 提交中击败了
     * 100.00% 的用户
     * 
     * @param root
     * @return
     */
/*    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList();
        if (root == null)
            return res;

        Stack<Node> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);
            List<Node> children = node.children;
            if (children != null && children.size() > 0) {
                for (int i = children.size() - 1; i >= 0; i--) {
                    Node child = children.get(i);
                    if (child != null)
                        stack.push(child);
                }
            }
        }
        return res;
    }*/

    /**
     * 执行用时 : 1 ms , 在所有 java 提交中击败了 100.00% 的用户 内存消耗 : 40.1 MB , 在所有 java 提交中击败了
     * 100.00% 的用户
     * 
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList();
        preOrder(root, list);
        return list;
    }

    public void preOrder(Node root, List<Integer> list){
        if(root == null) return;
        list.add(root.val);
        for(int i = 0 ; i < root.children.size() ; i++){
            preOrder(root.children.get(i), list);
        }
    }

}
