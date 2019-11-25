/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/range-sum-of-bst/submissions/
 * 
 * @author zhenganwen
 * @date 2019/11/23 15:44
 */
public class _938_range_sum_of_bst {

    /**
     * </br>目标就是将而bst中值在L到R直接的节点找出来并求和，因此只要会遍历二叉树即可
     *  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
     *         if(p == null || q == null) throw new RuntimeException("neither p nor q must not be null");
     *         while(p.val < root.val && q.val < root.val
     *              || (p.val > root.val && q.val > root.val)){
     *             root = root.val > p.val ? root.left : root.right;
     *         }
     *         return root;
     *     } public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
     *         if(p == null || q == null) throw new RuntimeException("neither p nor q must not be null");
     *         while(p.val < root.val && q.val < root.val
     *              || (p.val > root.val && q.val > root.val)){
     *             root = root.val > p.val ? root.left : root.right;
     *         }
     *         return root;

     * </br>执行用时 :
     * 1 ms
     * , 在所有 java 提交中击败了
     * 88.34%
     * 的用户
     * </br>内存消耗 :
     * 45 MB
     * , 在所有 java 提交中击败了
     * 91.06%
     * 的用户
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBST(TreeNode root, int L, int R) {
        int[] res = {0};
        inorder(root, L, R, res);
        return res[0];
    }

    public void inorder(TreeNode root, int L, int R, int[] res){
        if(root == null) return;
        inorder(root.left, L, R, res);
        if(root.val >= L && root.val <= R) res[0] += root.val;
        inorder(root.right, L, R, res);
    }

    /**
     * </br>
     * 非递归中序遍历，访问到L到R之间的节点就将其累加起来，注意L到R直接的节点值是升序的 </br>
     * 执行用时 : 19 ms , 在所有 java 提交中击败了 6.69% 的用户 </br>
     * 内存消耗 : 45.5 MB , 在所有 java 提交中击败了 89.43% 的用户
     * 
     * @param root
     * @param L
     * @param R
     * @return
     */
    /*public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null)
            return 0;
        int sum = 0;
        Stack<TreeNode> stack = new Stack();
        boolean include = false;
        while (root != null) {
            stack.push(root);
            root = root.left;
            if (root == null) {
                while (root == null) {
                    if (stack.isEmpty())
                        break;
                    root = stack.pop();

                    if (root.val >= L && root.val <= R)
                        sum += root.val;

                    root = root.right;
                }
            }
        }
        return sum;
    }*/
}
