/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/submissions/
 * 
 * @author zhenganwen
 * @date 2019/11/23 16:18
 */
public class _235_lowest_common_ancestor_of_a_bst {

    /**
     * </br>
     * 最低公共祖先就是不断地向左或向右移动root，直到root不是比两者都大或都小为止 执行用时 : 7 ms , 在所有 java 提交中击败了
     * 98.51% 的用户 </br>
     * 内存消耗 : 36.3 MB , 在所有 java 提交中击败了 82.30% 的用户
     * 
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p == null || q == null)
            throw new RuntimeException("neither p nor q must not be null");
        while (p.val < root.val && q.val < root.val || (p.val > root.val && q.val > root.val)) {
            root = root.val > p.val ? root.left : root.right;
        }
        return root;
    }
}
