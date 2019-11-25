/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

/**
 * @author zhenganwen
 * @date 2019/11/22 21:18
 */
public class _108_convert_sorted_array_to_bst {

    /**
     * </br>
     * 递归思路：既然能够由一个有序数组重建一棵BBST，那么就能由该数组的子序列重建该BBST的左子树和右子树 </br>
     * 因为BBST的每一个节点都是一个子BBST </br>
     * 执行用时 : 0 ms , 在所有 java 提交中击败了 100.00% 的用户 </br>
     * 内存消耗 : 37.2 MB , 在所有 java 提交中击败了 97.44% 的用户
     * 
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] arr, int begin, int end) {
        if (begin == end) {
            return new TreeNode(arr[end]);
        }
        int mid = begin + ((end - begin) >> 1);
        TreeNode root = new TreeNode(arr[mid]);
        if (mid > begin)
            root.left = sortedArrayToBST(arr, begin, mid - 1);
        if (mid < end)
            root.right = sortedArrayToBST(arr, mid + 1, end);
        return root;
    }
}
