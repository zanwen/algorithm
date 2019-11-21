/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/maximum-width-of-binary-tree/submissions/
 * 
 * @author zhenganwen
 * @date 2019/11/19 16:18
 */
public class _662_MAXIMUM_WIDTH_OF_BINARY_TREE {

    /**
     * 执行用时 : 26 ms , 在所有 java 提交中击败了 10.53% 的用户 内存消耗 : 49 MB , 在所有 java 提交中击败了
     * 6.25% 的用户
     * 
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        int res = 1;
        LinkedList<TreeNode> q1 = new LinkedList(), q2 = new LinkedList();
        q1.offer(root);
        while (!q1.isEmpty()) {
            while (!q1.isEmpty()) {
                TreeNode node = q1.poll();
                q2.offer(node == null ? null : node.left);
                q2.offer(node == null ? null : node.right);
            }
            while (!q2.isEmpty() && q2.getFirst() == null)
                q2.pollFirst();
            while (!q2.isEmpty() && q2.getLast() == null)
                q2.pollLast();
            res = Math.max(res, q2.size());

            LinkedList<TreeNode> tmp = q1;
            q1 = q2;
            q2 = tmp;
        }

        return res;
    }
}
