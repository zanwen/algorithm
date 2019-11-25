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

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * @author zhenganwen
 * @date 2019/11/19 9:03
 */
public class _102_binary_tree_level_order_traversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if(root == null) return res;
        LinkedList<TreeNode> q1 = new LinkedList(), q2 = new LinkedList();
        q1.offer(root);
        while(!q1.isEmpty()){
            List<Integer> list = new ArrayList();
            while(!q1.isEmpty()){
                TreeNode node = q1.poll();
                list.add(node.val);
                if(node.left != null) q2.offer(node.left);
                if(node.right != null) q2.offer(node.right);
            }
            res.add(list);

            LinkedList<TreeNode> tmp = q1;
            q1 = q2;
            q2 = tmp;
        }
        return res;
    }
}