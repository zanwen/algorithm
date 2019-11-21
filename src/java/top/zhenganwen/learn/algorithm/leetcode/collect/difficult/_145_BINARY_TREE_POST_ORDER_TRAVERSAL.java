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
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * @author zhenganwen
 * @date 2019/11/19 9:02
 */
public class _145_BINARY_TREE_POST_ORDER_TRAVERSAL {

    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        TreeNode lastAccess = null;
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();
            if(isLeaf(node) || oneIsChildOfAnother(lastAccess, node)){
                stack.pop();
                res.add(node.val);
                lastAccess = node;
            }else{
                if(node.right != null){
                    stack.push(node.right);
                }
                if(node.left != null){
                    stack.push(node.left);
                }
            }
        }
        return res;
    }

    public static boolean isLeaf(TreeNode node){
        return node != null && node.left == null && node.right == null;
    }

    public static boolean oneIsChildOfAnother(TreeNode one, TreeNode another){
        return one != null && (one == another.left || one == another.right);
    }
}