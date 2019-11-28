/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/balanced-binary-tree/submissions/
 * 
 * @author zhenganwen
 * @date 2019/11/26 9:39
 */
public class _110_balanced_binary_tree {

    /**
     * 执行用时 : 9 ms , 在所有 java 提交中击败了 5.41% 的用户 </br>
     * 内存消耗 : 36.4 MB , 在所有 java 提交中击败了 96.29% 的用户
     * 
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        Map<TreeNode, Integer> hMap = new HashMap();
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        hMap.put(root, 1);
        TreeNode last = null;
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();
            if(isLeaf(node) || oneIsChildOfAnother(last, node)){
                stack.pop();
                // visit
                int left = node.left == null ? 0 : hMap.get(node.left);
                int right = node.right == null ? 0 : hMap.get(node.right);
                if(Math.abs(left - right) > 1) return false;
                else hMap.put(node, 1 + Math.max(left, right));
                // record
                last = node;
            }else{
                if(node.right != null){
                    stack.push(node.right);
                    hMap.put(node.right, 1);
                }
                if(node.left != null){
                    stack.push(node.left);
                    hMap.put(node.left, 1);
                }
            }
        }
        return true;
    }

    private boolean isLeaf(TreeNode node){
        return node != null && node.left == null && node.right == null;
    }

    private boolean oneIsChildOfAnother(TreeNode one, TreeNode another){
        return one != null && (one == another.left || one == another.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(4);
        System.out.println(new _110_balanced_binary_tree().isBalanced(root));
    }

    /**
     * 执行用时 : 1 ms , 在所有 java 提交中击败了 100.00% 的用户 </br>
     * 内存消耗 : 37.6 MB , 在所有 java 提交中击败了 57.02% 的用户
     * 
     * @param root
     * @return
     */
/*    public boolean isBalanced(TreeNode root) {
        return traversal(root).balanced;
    }

    private class Info {
        int     height;
        boolean balanced;

        public Info(int height, boolean balanced) {
            this.height = height;
            this.balanced = balanced;
        }
    }

    private Info traversal(TreeNode root) {
        if (root == null)
            return new Info(0, true);
        Info left = traversal(root.left);
        Info right = traversal(root.right);
        boolean balanced = left.balanced && right.balanced;
        if (Math.abs(left.height - right.height) > 1) {
            balanced = false;
        }
        return new Info(1 + Math.max(left.height, right.height), balanced);
    }*/

}
