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
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * @author zhenganwen
 * @date 2019/11/19 9:00
 */
public class _144_BINARY_TREE_PRE_ORDER_TRAVERSAL {

    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList();
        if(root == null) return res;
        Stack<TreeNode> s = new Stack();
        s.push(root);
        while(!s.isEmpty()){
            TreeNode node = s.pop();
            res.add(node.val);
            if(node.right != null){
                s.push(node.right);
            }
            if(node.left != null){
                s.push(node.left);
            }
        }
        return res;
    }
}