/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/submissions/
 * @author zhenganwen
 * @date 2019/11/21 14:06
 */
public class _105_construct_binary_tree_from_preorder_and_inorder_traversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null ||
                inorder.length == 0 || inorder.length != preorder.length) return null;
        int end = inorder.length - 1;
        return build(inorder, 0, end, preorder, 0, end);
    }

    private TreeNode build(int[] in, int i, int j, int[] pre, int m, int n){
        if(i == j) return new TreeNode(in[i]);
        int val = pre[m];
        int index = index(in, i, j, val);
        if (index == -1) {
            throw new RuntimeException("invalid input");
        }
        TreeNode root = new TreeNode(val);
        int lefts = index - i, rights = j - index;
        root.left = lefts == 0 ? null : build(in, i, i + lefts - 1, pre, m + 1, m + lefts);
        root.right = rights == 0 ? null : build(in, i + lefts + 1, j, pre, m + lefts + 1, n);
        return root;
    }

    /**
     * </br>执行用时 :
     * 2 ms
     * , 在所有 java 提交中击败了
     * 98.26%
     * 的用户
     * </br>内存消耗 :
     * 39.4 MB
     * , 在所有 java 提交中击败了
     * 42.49%
     * 的用户
     * @param arr
     * @param begin
     * @param end
     * @param val
     * @return
     */
    private int index(int[] arr, int begin, int end, int val){
        //for(int i = begin; i <= end; i++) 题目假设节点值不重复
        for(int i = 0 ; i < arr.length ; i++)
            if(arr[i] == val) return i;
        return -1;
    }
}