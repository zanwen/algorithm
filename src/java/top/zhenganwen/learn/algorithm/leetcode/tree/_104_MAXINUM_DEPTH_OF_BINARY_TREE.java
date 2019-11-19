/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

/**
 * @author zhenganwen
 * @date 2019/11/19 9:05
 */
public class _104_MAXINUM_DEPTH_OF_BINARY_TREE {

    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        // 0ms 37.9m
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        /*
        1ms 38m
        LinkedList<TreeNode> q1 = new LinkedList(), q2 = new LinkedList();
        q1.offer(root);
        int height = 0;
        while(!q1.isEmpty()){
            while(!q1.isEmpty()){
                TreeNode node = q1.poll();
                if(node.left != null) q2.offer(node.left);
                if(node.right != null) q2.offer(node.right);
            }
            height++;
            LinkedList<TreeNode> tmp = q1;
            q1 = q2;
            q2 = tmp;
        }
        return height;
        */
    }
}