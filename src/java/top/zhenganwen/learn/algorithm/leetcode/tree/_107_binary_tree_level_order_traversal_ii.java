/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/submissions/
 * @author zhenganwen
 * @date 2019/11/19 16:01
 */
public class _107_binary_tree_level_order_traversal_ii {

    /**
     * 执行用时 : 2 ms , 在所有 java 提交中击败了 70.05% 的用户 内存消耗 : 36.4 MB , 在所有 java 提交中击败了
     * 40.70% 的用户
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> res = new ArrayList();
        if (root == null)
            return res;

        Stack<List<Integer>> stack = new Stack();
        LinkedList<TreeNode> q = new LinkedList();
        q.offer(root);
        q.offer(null);
        while (!q.isEmpty()) {
            List<Integer> list = new ArrayList();
            while (!q.isEmpty() && q.peek() != null) {
                TreeNode node = q.poll();
                list.add(node.val);
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
            stack.push(list);
            if (!q.isEmpty()) {
                q.poll();
                if (!q.isEmpty())
                    q.offer(null);
            }
        }

        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }

        return res;
    }

}
