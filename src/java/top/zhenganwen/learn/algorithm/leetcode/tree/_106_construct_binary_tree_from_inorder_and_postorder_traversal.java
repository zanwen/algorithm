/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * @author zhenganwen
 * @date 2019/11/21 12:35
 */
public class _106_construct_binary_tree_from_inorder_and_postorder_traversal {

    /**
     * </br>
     * 递归解法：左右子树的构造过程可以递归调用 </br>
     * 注意边界问题：处理数组索引时以目标范围的首索引作为参照，以偏移量+首索引进行定位；将边界情况和通用情况隔离分析</br>
     * </hr>
     * 执行用时 : 2 ms , 在所有 java 提交中击败了 97.96% 的用户 </br>
     * 内存消耗 : 38.5 MB , 在所有 java 提交中击败了 62.60% 的用户 </br>
     * 执行用时 : 2 ms , 在所有 java 提交中击败了 97.96% 的用户 </br>
     * 内存消耗 : 38.3 MB , 在所有 java 提交中击败了 64.89% 的用户
     * 
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null ||
                inorder.length == 0 || postorder.length == 0
                || inorder.length != postorder.length) return null;

        int end = inorder.length - 1;
        return build(inorder, 0, end, postorder, 0, end);
    }

    private TreeNode build(int[] inorder, int i, int j, int[] postorder, int m, int n){
        if(i == j) return new TreeNode(inorder[i]);
        int val = postorder[n];
        int index = index(inorder, i, j, val);
        if(index == -1) throw new RuntimeException("invalid input");

        TreeNode root = new TreeNode(val);
        int lefts = index - i, rights = j - index;
        root.left = lefts == 0 ? null : build(inorder, i, i + lefts - 1, postorder, m, m + lefts - 1);
        root.right = rights == 0 ? null : build(inorder, i + lefts + 1, j, postorder, m + lefts, n - 1);
        return root;
    }

    private int index(int[] arr, int begin, int end, int val){
        //for(int i = begin; i <= end; i++) 题目假设节点值不重复
        for(int i = 0 ; i < arr.length ; i++)
            if(arr[i] == val) return i;
        return -1;
    }
}