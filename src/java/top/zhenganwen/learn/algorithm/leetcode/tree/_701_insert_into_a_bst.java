/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/submissions/
 * 
 * @author zhenganwen
 * @date 2019/11/22 16:48
 */
public class _701_insert_into_a_bst {

    /**
     * 执行用时 : 0 ms , 在所有 java 提交中击败了 100.00% 的用户 </br>
     * 内存消耗 : 38.6 MB , 在所有 java 提交中击败了 96.67% 的用户
     * 
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return null;
        TreeNode last = null, r = root;
        while (root != null) {
            if (root.val == val)
                return r;
            last = root;
            if (val > root.val)
                root = root.right;
            else
                root = root.left;
        }
        TreeNode node = new TreeNode(val);
        if (val > last.val)
            last.right = node;
        else
            last.left = node;
        return r;
    }
}
