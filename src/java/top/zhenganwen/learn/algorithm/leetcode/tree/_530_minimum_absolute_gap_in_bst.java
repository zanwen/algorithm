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
 * @date 2019/11/22 17:44
 */
public class _530_minimum_absolute_gap_in_bst {

    /**
     * </br>
     * 递归思路：如果知道了左字数的最小差值和右子树的最小差值，那么再根据当前节点和其前驱、后继节点的差值，去四者中最小的即为答案 </br>
     * 执行用时 : 1 ms , 在所有 java 提交中击败了 100.00% 的用户 </br>
     * 内存消耗 : 38.2 MB , 在所有 java 提交中击败了 97.81% 的用户
     * 
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        if(root == null) return 0;
        return minGap(root);
    }

    public int minGap(TreeNode root){
        int minGap = Integer.MAX_VALUE;
        if(root == null) return minGap;
        int leftMinGap = minGap(root.left);
        int rightMinGap = minGap(root.right);
        TreeNode p;
        if(root.left != null){
            p = root.left;
            while(p.right != null){
                p = p.right;
            }
            minGap = Math.min(minGap, root.val - p.val);
        }
        if(root.right != null){
            p = root.right;
            while(p.left != null){
                p = p.left;
            }
            minGap = Math.min(minGap, p.val - root.val);
        }
        return Math.min(minGap, Math.min(leftMinGap, rightMinGap));
    }

    /**
     * bst的特性是中序遍历是升序的，因此所求的两个节点一定是中序遍历中相邻的两个节点
     * </br>
     * 执行用时 : 5 ms , 在所有 java 提交中击败了 18.29% 的用户 </br>
     * 内存消耗 : 38.8 MB , 在所有 java 提交中击败了 89.47% 的用户
     * 
     * @param root
     * @return
     */
   /* public int getMinimumDifference(TreeNode root) {
        if (root == null)
            return 0;
        Stack<TreeNode> stack = new Stack();
        TreeNode last = null;
        int minGap = Integer.MAX_VALUE;
        while (root != null) {
            stack.push(root);
            root = root.left;
            if (root == null) {
                while (root == null) {
                    if (stack.isEmpty())
                        break;
                    root = stack.pop();
                    if (last != null)
                        minGap = Math.min(minGap, root.val - last.val);
                    last = root;
                    root = root.right;
                }
            }
        }
        return minGap;
    }*/
}
