/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.collect.difficult;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import top.zhenganwen.learn.algorithm.leetcode.tree.TreeNode;

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * @author zhenganwen
 * @date 2019/11/19 9:01
 */
public class _94_BINARY_TREE_IN_ORDER_TRAVERSAL {

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack();
        while(root != null){
            // push left node until root reach the most left
            stack.push(root);
            root = root.left;
            while(root == null){
                if(!stack.isEmpty()){
                    TreeNode node = stack.pop();
                    res.add(node.val);
                    root = node.right;
                }else {
                    break;
                }
            }
        }
        return res;
    }
}