/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * @author ZHENGANWEN
 * @date 2019/11/22 16:49
 */
public class _98_validate_bst {

    /**
     * </br>
     * 递归思路：左子树是bst，右子树也是bst，根节点元素大于左边最大的、小于右边最小的，该树也是bst </br>
     * 执行用时 : 1 ms , 在所有 java 提交中击败了 95.24% 的用户 </br>
     * 内存消耗 : 38.2 MB , 在所有 java 提交中击败了 40.90% 的用户
     * 
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isBST(root).isBST;
    }

    private BSTInfo isBST(TreeNode root){
        if(root == null) return new BSTInfo(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        BSTInfo left = isBST(root.left);
        BSTInfo right = isBST(root.right);
        if(!left.isBST || !right.isBST
                || (root.left != null && root.val <= left.maxVal)
                || (root.right != null && root.val >= right.minVal)){
            return new BSTInfo(false, 0, 0);
        }
        return new BSTInfo(true, Math.max(root.val, right.maxVal), Math.min(root.val, left.minVal));
    }

    class BSTInfo{
        boolean isBST;
        int maxVal;
        int minVal;
        public BSTInfo(boolean isBST, int maxVal, int minVal){
            this.isBST = isBST;
            this.maxVal = maxVal;
            this.minVal = minVal;
        }
    }

    /**
     * </br>
     * 中序遍历，断言当前访问节点的值大于上次访问节点的值 </br>
     * 执行用时 : 3 ms , 在所有 java 提交中击败了 37.74% 的用户 </br>
     * 内存消耗 : 36.5 MB , 在所有 java 提交中击败了 95.14% 的用户
     * 
     * @param root
     * @return
     */
   /* public boolean isValidBST(TreeNode root) {
        if(root == null) return true;

        Stack<TreeNode> stack = new Stack();
        TreeNode last = null;
        while(root != null){
            stack.push(root);
            root = root.left;
            if(root == null){
                while(root == null){
                    if(stack.isEmpty()) break;
                    root = stack.pop();
                    if(last != null && root.val <= last.val) return false;
                    last = root;
                    root = root.right;
                }
            }
        }
        return true;
    }*/
}