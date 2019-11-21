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
 * @author zhenganwen
 * @date 2019/11/19 8:58
 */
public class TreeNode {
    public int      val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "val=" + val + '}';
    }

    public static void levelOrderTraversal(TreeNode root) {
        if (root == null)
            return;
        StringBuilder stringBuilder = new StringBuilder();
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            stringBuilder.append(node.left == null ? "null" : node.left.val).append("_").append(node.val).append("_")
                    .append(node.right == null ? "null" : node.right.val);
            if (node.left != null)
                q.offer(node.left);
            if (node.right != null)
                q.offer(node.right);
        }
    }
}
