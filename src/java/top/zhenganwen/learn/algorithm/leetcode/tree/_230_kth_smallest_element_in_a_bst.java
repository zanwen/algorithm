/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/submissions/
 * @author zhenganwen
 * @date 2019/11/23 16:28
 */
public class _230_kth_smallest_element_in_a_bst {

    /**
     * </br>
     * 要求bst中第k小的数，取中序遍历中第k个访问的元素即可 </br>
     * 执行用时 : 1 ms , 在所有 java 提交中击败了 93.22% 的用户 </br>
     * 内存消耗 : 37.2 MB , 在所有 java 提交中击败了 94.16% 的用户
     * 
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        int[] info = { 0, 0 };
        inorder(root, info, k);
        return info[1];
    }

    // the info has two elements, the first represents how many nodes
    // has been visited before, and the second represents the k-th node's value
    private void inorder(TreeNode root, int[] info, int k) {
        if (root == null)
            return;
        inorder(root.left, info, k);
        if (++info[0] == k)
            info[1] = root.val;
        inorder(root.right, info, k);
    }
}
